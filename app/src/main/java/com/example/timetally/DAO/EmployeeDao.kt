package com.example.timetally.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.timetally.Data.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employee: Employee)

    @Update
    suspend fun update(employee: Employee)

    @Query("SELECT * FROM employee_table ORDER BY id ASC")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee_table WHERE isPresent = 1 ORDER BY id ASC")
    fun getPresentEmployees(): LiveData<List<Employee>>


    @Query("UPDATE employee_table SET isPresent = :isPresent WHERE id = :employeeId")
    suspend fun updatePresence(employeeId: Long, isPresent: Boolean)

}
