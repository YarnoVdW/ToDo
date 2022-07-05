package com.icapps.yarno.todolist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(var toDoList: MutableList<String>): RecyclerView.Adapter<ToDoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.bindData(position+1, toDoList[position])
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun addNewItem(toString: String) {
        toDoList.add(if (toString.isEmpty()) "New item ${toDoList.size +1}" else toString)
    }

}
