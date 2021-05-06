package com.mertrizakaradeniz.todo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertrizakaradeniz.todo.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: Todo)

    @Query("SELECT * FROM todo ORDER BY todoTitle ASC")
    fun gelAllToDos(): Flow<List<Todo>>

}