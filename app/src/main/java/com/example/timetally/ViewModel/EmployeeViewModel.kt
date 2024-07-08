package com.example.timetally.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.timetally.DAO.EmployeeDao
import com.example.timetally.DAO.EmployeeDatabase
import kotlinx.coroutines.launch
import com.example.timetally.Data.Employee
import com.example.timetally.Repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers

/*
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
*/
/*
class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmployeeRepository
    val allEmployees: LiveData<List<Employee>>
    private val _presentEmployees = MutableLiveData<MutableList<Employee>>(mutableListOf())
    val presentEmployees: MutableLiveData<MutableList<Employee>> get() = _presentEmployees

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        allEmployees = repository.getAllEmployees
    }

    fun addPresentEmployee(employee: Employee) {
        val currentList = _presentEmployees.value ?: mutableListOf()
        currentList.add(employee)
        _presentEmployees.value = currentList
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEmployee(employee)
        }
    }
}

*/


import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EmployeeRepository
    private val employeeDao: EmployeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
    val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    val presentEmployees: LiveData<List<Employee>> = employeeDao.getPresentEmployees()
    init {
        repository = EmployeeRepository(employeeDao)
    }
    fun getEmployeeDao(): EmployeeDao {
        return employeeDao
    }
    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEmployee(employee)
        }
    }
    fun addPresentEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePresence(employee.id, true)
        }
    }
}