package com.mertrizakaradeniz.todo.repository

import com.mertrizakaradeniz.todo.db.ToDoDao
import com.mertrizakaradeniz.todo.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: ToDoDao) {

    suspend fun insertTodo(todo: Todo) = dao.insertToDo(todo)
    fun getAllTodos() = dao.gelAllToDos()

}