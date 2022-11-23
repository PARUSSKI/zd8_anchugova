package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.zd8.Task

@TypeConverters(TaskTypeConverters::class)
@Database(entities = [Task::class], version = 1,exportSchema = false)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}