package com.bignerdranch.android.zd8

import android.app.Application

class TaskApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)    }
}