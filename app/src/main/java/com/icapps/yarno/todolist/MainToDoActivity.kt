package com.icapps.yarno.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainToDoActivity : AppCompatActivity() { //launcher veranderen in manifest

    private lateinit var recView: RecyclerView
    private lateinit var toDoListAdapter: ToDoListAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_to_do)

        recView.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        toDoListAdapter = ToDoListAdapter(listOf("kuisen", "wassen"))
        recView.adapter = toDoListAdapter


    }

}