package com.icapps.yarno.todolist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(var toDoList: MutableList<ToDoItem>, var clickListener: ToDoListClickListener): RecyclerView.Adapter<ToDoListViewHolder>() {

    fun interface ToDoListClickListener { // we gaan de interface als een functie gebruiken
        fun toDoListItemClicked(list: ToDoItem) // gaat enkel als we maar 1 functie hebben in de interface!!!
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ToDoListViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.bindData(position+1, toDoList[position])


    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun addNewItem(item: ToDoItem) {
        toDoList.add(item)
        notifyItemInserted(toDoList.size - 1) // force rerender
    }

}
