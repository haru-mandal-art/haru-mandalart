package com.coldblue.data.mapper

import com.coldblue.database.entity.CurrentGroupEntity
import com.coldblue.database.entity.TodoEntity
import com.coldblue.database.entity.TodoGroupEntity
import com.coldblue.database.entity.TodoWithGroupName
import com.coldblue.model.CurrentGroup
import com.coldblue.model.Todo

object TodoEntityMapper {

    fun asEntity(domain: Todo): TodoEntity {
        return TodoEntity(
            title = domain.title,
            content = domain.content,
            isDone = domain.isDone,
            time = domain.time,
            date = domain.date,
            todoGroupId = domain.todoGroupId,
            originId = domain.originId,
            isSync = domain.isSync,
            isDel = domain.isDel,
            updateTime = domain.updateTime,
            id = domain.id,
        )
    }

    fun asEntity(domain: List<Todo>): List<TodoEntity> {
        return domain.map { todo ->
            todo.asEntity()
        }
    }

    fun asDomain(entity: TodoWithGroupName): Todo {
        return Todo(
            title = entity.todo.title,
            content = entity.todo.content,
            isDone = entity.todo.isDone,
            time = entity.todo.time,
            date = entity.todo.date,
            todoGroupId = entity.todo.todoGroupId,
            originId = entity.todo.originId,
            isSync = entity.todo.isSync,
            isDel = entity.todo.isDel,
            updateTime = entity.todo.updateTime,
            groupName = entity.groupName,
            id = entity.todo.id,
            )
    }

    fun asDomain(entity: List<TodoWithGroupName>): List<Todo> {
        return entity.map { todoEntity ->
            todoEntity.asDomain()
        }
    }
}

fun List<Todo>.asEntity(): List<TodoEntity> {
    return TodoEntityMapper.asEntity(this)
}

fun Todo.asEntity(): TodoEntity {
    return TodoEntityMapper.asEntity(this)
}

fun List<TodoWithGroupName>.asDomain(): List<Todo> {
    return TodoEntityMapper.asDomain(this)
}

fun TodoWithGroupName.asDomain(): Todo {
    return TodoEntityMapper.asDomain(this)
}