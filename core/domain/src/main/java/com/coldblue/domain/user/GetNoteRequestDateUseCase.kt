package com.coldblue.domain.user

import com.coldblue.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteRequestDateUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(): Flow<String> =
        userRepository.noteRequestDate
}