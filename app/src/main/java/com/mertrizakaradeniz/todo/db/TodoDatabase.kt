package com.mertrizakaradeniz.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertrizakaradeniz.todo.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): ToDoDao

}