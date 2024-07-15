package com.example.timetally.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.timetally.DAO.EmployeeDao
import com.example.timetally.DAO.EmployeeDatabase
import com.example.timetally.Data.Employee
import com.example.timetally.Repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EmployeeRepository
    private val employeeDao: EmployeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
    val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    val presentEmployees: LiveData<List<Employee>> = employeeDao.getPresentEmployees()

    private val _presentEmployees = MutableLiveData<List<Employee>>()
    private val _absentEmployees = MutableLiveData<List<Employee>>()
    val absentEmployees: LiveData<List<Employee>> get() = _absentEmployees

    init {
        repository = EmployeeRepository(employeeDao)
        allEmployees.observeForever { employees ->
            _presentEmployees.value = employees.filter { it.isPresence }
            _absentEmployees.value = employees.filter { !it.isPresence }
        }
    }
    fun getEmployeeDao(): EmployeeDao {
        return employeeDao
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEmployee(employee)
        }
    }

    fun updateEmployeePresence(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            employeeDao.updateEmployee(employee)
            if (employee.isPresence) {
                addEmployee(employee)
            }
        }
    }
}

