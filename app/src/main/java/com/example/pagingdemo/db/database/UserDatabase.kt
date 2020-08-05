package com.example.pagingdemo.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pagingdemo.db.dao.UserDao
import com.example.pagingdemo.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        /**
         * 提供互斥的静态方法用于获取数据库实例
         * 如果实例没创建，则创建；已经创建了，直接返回实例
         * 减少了创建实例所花费的开销
         */
        @Synchronized
        fun get(context: Context): UserDatabase {

            if (instance == null) {

                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "UserDatabase"
                ).build()
            }
            return instance!!
        }


    }
}



/*
private val USER_DATA = arrayListOf(
    "Abbaye de Belloc", "Abbaye du Mont des Cats")*/
