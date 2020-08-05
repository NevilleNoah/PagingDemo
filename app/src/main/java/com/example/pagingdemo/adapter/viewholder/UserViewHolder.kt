package com.example.pagingdemo.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.db.entity.User
import kotlinx.android.synthetic.main.item.view.*

class UserViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
) {

    // 控件
    private val idView = itemView.item_id
    private val nameView = itemView.item_name

    // 数据
    private var user: User? = null

    // 用于数据与控件的绑定
    fun bindTo(user : User?) {
        this.user = user
        idView.text = user?.id.toString()
        nameView.text = user?.name
    }
}