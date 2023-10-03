package com.tworld.tviewing.myVideo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyPageDao {

    @Query("SELECT * FROM mypage")
    fun getAll(): LiveData<List<MyPageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mypage: MyPageEntity)

    @Update
    fun update(mypage: MyPageEntity)

    @Delete
    fun delete(mypage: MyPageEntity)

}