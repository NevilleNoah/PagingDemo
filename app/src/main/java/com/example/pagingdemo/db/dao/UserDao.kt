package com.example.pagingdemo.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pagingdemo.db.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun allUsers(): PagingSource<Int, User>

    @Insert
    fun insert(users: List<User>)

    @Query("DELETE FROM User")
    fun deleteAll()
}