package com.coldblue.domain.todogroup

import com.coldblue.data.repository.TodoGroupRepository
import javax.inject.Inject

class DeleteTodoGroupUseCase @Inject constructor(
    private val todoGroupRepository: TodoGroupRepository
) {
    suspend operator fun invoke(todoGroupId: Int) = todoGroupRepository.delTodoGroup(todoGroupId)
}