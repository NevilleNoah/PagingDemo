package com.example.pagingdemo.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingdemo.adapter.viewholder.UserViewHolder
import com.example.pagingdemo.db.entity.User

/**
 * 继承PagingDataAdapter
 */
class UserAdapter : PagingDataAdapter<User, UserViewHolder>(diffCallback) { // diffCallback用内部定义的变量
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // 数据绑定：传入条目的位置信息，将数据绑定到这个位置的条目上
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // 布局创建：传入父布局，在父布局中创建布局
        return UserViewHolder(parent)
    }

    companion object {
        // 同异判断：分别用于判断条目是否更新和内容是否更新
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem == newItem
            }

        }
    }
}