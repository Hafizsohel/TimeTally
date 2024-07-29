package com.example.timetally.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.DAO.EmployeeDao
import com.example.timetally.Data.Employee
import com.example.timetally.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "EmployeeAdapter"

class EmployeeAdapter(
    private val employeeDao: EmployeeDao,
    private val onEmployeePresenceChecked: (Employee, Boolean) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
    private var employees = mutableListOf<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
        val checkPresence: CheckBox = itemView.findViewById(R.id.checkPresence)
        val checkAbsent:CheckBox=itemView.findViewById(R.id.checkAbsent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name

        holder.checkPresence.setOnCheckedChangeListener(null)
        holder.checkPresence.isChecked = currentEmployee.isPresence

        holder.checkPresence.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != currentEmployee.isPresence) {
                currentEmployee.isPresence = isChecked
                onEmployeePresenceChecked(currentEmployee,isChecked)

           /* if (isChecked != currentEmployee.isPresence) {
                currentEmployee.isPresence = isChecked
                val currentDate = getCurrentDate()
                currentEmployee.date = currentDate
                onEmployeePresenceChecked(currentEmployee, isChecked)*/

                // Update the database in the background thread
                CoroutineScope(Dispatchers.IO).launch {
                    employeeDao.updateEmployee(currentEmployee)
                }

                Log.d(TAG, "onBindViewHolder: Employee ${currentEmployee.name} is present: $isChecked")
            }
        }
    }

    override fun getItemCount(): Int = employees.size

    fun setEmployees(employees: List<Employee>) {
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }
   /* private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }*/


}

