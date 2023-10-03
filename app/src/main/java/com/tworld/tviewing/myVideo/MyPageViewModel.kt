package com.tworld.tviewing.myVideo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MyPageViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = MyPageRepository(application)
    private val mypageEntity = repository.getAll()

    fun getAll(): LiveData<List<MyPageEntity>> {
        return this.mypageEntity
    }

    fun insert(mypage: MyPageEntity) {
        repository.insert(mypage)
    }

    fun delete(mypage: MyPageEntity) {
        repository.delete(mypage)
    }
}