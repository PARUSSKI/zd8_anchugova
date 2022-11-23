package com.bignerdranch.android.zd8

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity
data class Task (@PrimaryKey var title:String = "",var description:String ="", var date: String = "",var time:String ="" ) {
}

