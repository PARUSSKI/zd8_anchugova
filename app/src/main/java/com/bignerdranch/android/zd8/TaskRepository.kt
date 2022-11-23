package com.bignerdranch.android.zd8


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.TaskDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "taskdatabase"
class TaskRepository private constructor(context: Context) {

    private val database : TaskDatabase =
        Room.databaseBuilder(context.applicationContext,
            TaskDatabase::class.java,
            DATABASE_NAME
        ).build()

    private val taskDao = database.taskDao()
    fun getTasks(): LiveData<List<Task>> = taskDao.getTasks()
    fun getTask(title:String): LiveData<List<Task?>> =taskDao.getTask(title)

    companion object {
        private var INSTANCE: TaskRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
        }

        fun get(): TaskRepository {
            return INSTANCE ?:
            throw
            IllegalStateException("TaskRepository must be initialized")
        }
    }


}