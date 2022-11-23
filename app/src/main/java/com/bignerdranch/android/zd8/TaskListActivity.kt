package com.bignerdranch.android.zd8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class TaskListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)


    }



}