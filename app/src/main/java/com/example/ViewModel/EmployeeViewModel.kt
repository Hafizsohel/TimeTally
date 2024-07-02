package com.example.ViewModel


import android.app.Application
import androidx.lifecycle.*
import com.example.timetally.DAO.EmployeeDatabase
import kotlinx.coroutines.launch
import com.example.timetally.Data.Employee
import com.example.timetally.Repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
     val allEmployees: LiveData<List<Employee>>
    private val repository: EmployeeRepository

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        allEmployees = repository.getAllEmployees
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEmployee(employee)
        }
    }
}



