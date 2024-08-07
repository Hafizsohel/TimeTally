package com.example.timetally.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "datewise_table",
    foreignKeys = [
        ForeignKey(
            entity = Employee::class,
            parentColumns = ["id"],
            childColumns = ["employee_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Datewise(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val employee_id: Long,
    val date: String,
    val isPresence: Boolean
)
