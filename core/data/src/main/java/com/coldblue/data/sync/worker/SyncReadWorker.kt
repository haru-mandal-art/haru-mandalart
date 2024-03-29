package com.coldblue.data.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.coldblue.data.repository.manda.MandaDetailRepository
import com.coldblue.data.repository.manda.MandaKeyRepository
import com.coldblue.data.repository.todo.CurrentGroupRepository
import com.coldblue.data.repository.todo.TodoGroupRepository
import com.coldblue.data.repository.todo.TodoRepository
import com.coldblue.data.repository.user.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext


@HiltWorker
class SyncReadWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val todoRepository: TodoRepository,
    @Assisted private val todoGroupRepository: TodoGroupRepository,
    @Assisted private val currentGroupRepository: CurrentGroupRepository,
    @Assisted private val mandaKeyRepository: MandaKeyRepository,
    @Assisted private val mandaDetailRepository: MandaDetailRepository,
    @Assisted private val userRepository: UserRepository,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            userRepository.refresh()
            val todoGroupResult = async { todoGroupRepository.syncRead() }.await()
            val syncedSucceed = awaitAll(
                async { todoRepository.syncRead() },
                async { currentGroupRepository.syncRead() },
                async { mandaKeyRepository.syncRead() },
                async { mandaDetailRepository.syncRead() },
                ).all { it }
            if (syncedSucceed && todoGroupResult) {
                Result.success()
            } else {

                Result.retry()
            }
        } catch (e: Exception) {

            Result.failure()
        }
    }
}