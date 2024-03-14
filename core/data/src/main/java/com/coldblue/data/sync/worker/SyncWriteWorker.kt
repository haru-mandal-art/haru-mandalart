package com.coldblue.data.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.coldblue.data.repository.todo.TodoRepository
import com.orhanobut.logger.Logger
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWriteWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val todoRepository: TodoRepository

) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val writeSucceed = awaitAll(
                async { todoRepository.syncWrite() },
            ).all { it }

            if (writeSucceed) {
                Logger.d("쓰기 성공")
                Result.success()
            } else {
                Logger.d("다시")
                Result.retry()
            }
        } catch (e: Exception) {
            Logger.d("실패")
            Result.failure()
        }
    }
}