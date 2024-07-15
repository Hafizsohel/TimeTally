package com.example.timetally.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.Data.Employee
import com.example.timetally.R

class AbsentEmployeeAdapter : RecyclerView.Adapter<AbsentEmployeeAdapter.AbsentEmployeeViewHolder>() {
    private var absentEmployees = mutableListOf<Employee>()

    inner class AbsentEmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsentEmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return AbsentEmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AbsentEmployeeViewHolder, position: Int) {
        val currentEmployee = absentEmployees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
    }

    override fun getItemCount(): Int = absentEmployees.size

    fun setAbsentEmployees(employees: List<Employee>) {
        this.absentEmployees = employees.toMutableList()
        notifyDataSetChanged()
    }

    fun getEmployees(): List<Employee> {
        return absentEmployees
    }
}
