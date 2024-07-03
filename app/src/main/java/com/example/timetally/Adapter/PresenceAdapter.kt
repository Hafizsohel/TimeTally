package com.example.timetally.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.Data.Employee
import com.example.timetally.R

/*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.Data.Employee
import com.example.timetally.R

class PresenceAdapter : RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder>() {

    private var presentEmployees = mutableListOf<Employee>()

    inner class PresenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresenceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list, parent, false)
        return PresenceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PresenceViewHolder, position: Int) {
        val currentEmployee = presentEmployees[position]
        holder.employeeName.text = currentEmployee.name
    }

    override fun getItemCount(): Int = presentEmployees.size

    fun addEmployee(employee: Employee) {
        presentEmployees.add(employee)
        notifyItemInserted(presentEmployees.size - 1)
    }
}
*/


class PresenceAdapter : RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder>() {

    private var presentEmployees = mutableListOf<Employee>()

    inner class PresenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresenceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return PresenceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PresenceViewHolder, position: Int) {
        val currentEmployee = presentEmployees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
    }

    override fun getItemCount(): Int = presentEmployees.size

    fun setEmployees(employees: List<Employee>) {
        this.presentEmployees = employees.toMutableList()
        notifyDataSetChanged()
    }
}
