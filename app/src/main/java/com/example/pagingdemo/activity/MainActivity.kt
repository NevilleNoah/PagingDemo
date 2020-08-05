package com.example.pagingdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.pagingdemo.R
import com.example.pagingdemo.activity.viewmodel.MainViewModel
import com.example.pagingdemo.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = UserAdapter()
        recyclerview.adapter = adapter

        lifecycleScope.launch {
            // 插入所有模拟数据
            viewModel.insertAll(application)
            // 更新适配器数据
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.allUsers.collectLatest {
                adapter.submitData(it)
            }
        }

        // 绑定删除数据事件
        delete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.deleteAll()
            }
        }
    }


}