package com.tworld.tviewing.myVideo

import android.app.Application
import androidx.lifecycle.LiveData

class MyPageRepository (application: Application) {

    private val mypageDatabase = MyPageDatabase.getInstance(application)!!
    private val mypageDao: MyPageDao = mypageDatabase.contactDao()
    private val mypageEntity: LiveData<List<MyPageEntity>> = mypageDao.getAll()

    fun getAll(): LiveData<List<MyPageEntity>> {
        return mypageEntity
    }

    fun insert(mypage: MyPageEntity) {
        try {
            val thread = Thread(Runnable {
                mypageDao.insert(mypage)
            })
            thread.start()
        } catch (e: Exception) {
        }
    }

    fun delete(mypage: MyPageEntity) {
        try {
            val thread = Thread(Runnable {
                mypageDao.delete(mypage)
            })
            thread.start()
        } catch (e: Exception) {
        }
    }
}