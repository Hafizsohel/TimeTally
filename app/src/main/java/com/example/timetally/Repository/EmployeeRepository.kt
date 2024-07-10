package com.example.timetally.Repository


import androidx.lifecycle.LiveData
import com.example.timetally.DAO.EmployeeDao
import com.example.timetally.Data.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {

   // val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
   // val presentEmployees: LiveData<List<Employee>> = employeeDao.getPresentEmployees()

    suspend fun insertEmployee(employee: Employee) {
        employeeDao.insert(employee)
    }

    suspend fun updatePresence(employeeId: Long, isPresent: Boolean) {
        employeeDao.updatePresence(employeeId, isPresent)
    }
}
