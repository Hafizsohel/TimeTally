package com.example.timetally.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.Data.Employee
import com.example.timetally.R

class PresenceAdapter(private val selectedDate: String?) : RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder>() {
    private var presenceEmployees = mutableListOf<Employee>()

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
        val currentEmployee = presenceEmployees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
    }
    override fun getItemCount(): Int = presenceEmployees.size

    fun setPresenceEmployees(employees: List<Employee>) {
        this.presenceEmployees = employees.toMutableList()
        this.presenceEmployees = employees.filter { it.date == selectedDate }.toMutableList()
        notifyDataSetChanged()
    }
    fun getEmployees(): List<Employee> {
        return presenceEmployees
    }
}
