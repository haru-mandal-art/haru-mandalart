package com.coldblue.domain.user

import com.coldblue.data.repository.UserRepository
import javax.inject.Inject

class UpdateMandaInitStateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(state: Boolean){
        userRepository.updateInit(state)
    }
}