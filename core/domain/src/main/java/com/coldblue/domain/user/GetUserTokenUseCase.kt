package com.coldblue.domain.user

import com.coldblue.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(): Flow<String> =
        userRepository.token
}