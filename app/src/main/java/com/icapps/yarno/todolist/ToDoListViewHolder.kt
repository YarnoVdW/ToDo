package com.icapps.yarno.todolist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListViewHolder(itemView: View, var clickListener: ToDoListAdapter.ToDoListClickListener): RecyclerView.ViewHolder(itemView) {
    private var idTextView: TextView = itemView.findViewById(R.id.index_id)
    private var toDoTextView: TextView = itemView.findViewById(R.id.naam_todo)



    fun bindData(position: Int,data: ToDoItem ) {

        idTextView.text = position.toString()
        toDoTextView.text = data.description
        toDoTextView.setOnClickListener {
            clickListener.toDoListItemClicked(data)
        }
    }



}

