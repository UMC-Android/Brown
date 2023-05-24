package com.example.umc5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoData::class],version=1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao():MemoDao

    companion object{
        private var instance: MemoDatabase? = null

        @Synchronized
        fun getInstance(context: Context):MemoDatabase?{
            if(instance == null){
                synchronized(MemoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo-database"
                    ).build()
                }
            }
            return instance
        }
    }
}