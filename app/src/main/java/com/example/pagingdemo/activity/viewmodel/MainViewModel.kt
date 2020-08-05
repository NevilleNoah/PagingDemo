package com.example.pagingdemo.activity.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingdemo.db.database.UserDatabase
import com.example.pagingdemo.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val dao = UserDatabase.get(app).userDao()

    // 模拟数据，用于填充数据库
    private val USER_DATA = arrayListOf<User>(
        User(1, "Name1"),
        User(2, "Name2"),
        User(3, "Name3"),
        User(4, "Name4"),
        User(5, "Name5")
    )

    /**
     * 通过Pager的构造方法中的config参数配置分页的参数
     * 通过Pager的方法体传入数据
     */
    val allUsers = Pager(
        PagingConfig(
            pageSize = 5,
            enablePlaceholders = true
        )
    ) {
        dao.allUsers()
    }.flow

    // 删除所有数据
    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }

    // 填充模拟数据
    suspend fun insertAll(context: Context) {
        withContext(Dispatchers.IO) {
            UserDatabase.get(context.applicationContext).userDao()
                .insert(
                    USER_DATA.map { User(id = 0, name = it.name) }
                )
        }
    }


}