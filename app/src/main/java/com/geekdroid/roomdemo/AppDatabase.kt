package com.geekdroid.roomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object{
        fun getInstance(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context, AppDatabase::class.java,"school_db"
            ).build()
        }
    }

    abstract fun getStudentDao(): StudentDao
}