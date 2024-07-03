/*
package com.example.timetally.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    */
/*@PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var isPresent: Boolean = false*//*


    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_present") var isPresent: Boolean
)*/

// File: com.example.timetally.Data.Employee.kt
package com.example.timetally.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    var isPresent: Boolean = false
)
