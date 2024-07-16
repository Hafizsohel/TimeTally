package com.example.timetally.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    var isPresence: Boolean = false,
   // var date: String? = null
)
