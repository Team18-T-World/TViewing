package com.tworld.tviewing.myVideo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyPageEntity::class], version = 1)
abstract class MyPageDatabase: RoomDatabase() {
    abstract fun contactDao(): MyPageDao

    companion object {
        private var INSTANCE: MyPageDatabase? = null

        fun getInstance(context: Context): MyPageDatabase? {
            if (INSTANCE == null) {
                synchronized(MyPageDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MyPageDatabase::class.java, "mypage")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}