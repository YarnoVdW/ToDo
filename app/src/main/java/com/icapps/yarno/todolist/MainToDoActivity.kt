package com.icapps.yarno.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainToDoActivity : AppCompatActivity(), ToDoListAdapter.ToDoListClickListener { //launcher veranderen in manifest

    private lateinit var recView: RecyclerView
    private lateinit var toDoListAdapter: ToDoListAdapter
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var listDataManager: ToDoListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_to_do)

        recView = findViewById(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        //listDataManager = ToDoListViewModel(this)
        toDoListAdapter = ToDoListAdapter(ArrayList(), this)
        recView.adapter = toDoListAdapter

        floatingButton = findViewById(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
           showDialog()
        }



    }




    private fun showDialog() {
        val toDoExit = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        }

        AlertDialog.Builder(this)
            .setView(toDoExit)
            .setTitle("Add To Do")
            .setPositiveButton("Create") {dialog, _ ->
                val list = ToDoItem(toDoListAdapter.itemCount,toDoExit.text.toString())
                toDoListAdapter.addNewItem(list)
                listDataManager.saveToDoList(list)
                launchActivity(list)
                dialog.dismiss()


            }
            .create()
            .show()


    }
    private fun launchActivity(list: ToDoItem){
        startActivity(Intent(this, DetailActivity::class.java)).apply{
            val charSet = ('a'..'z')
            for (i in 0..4) {
                list.taskList.add(List(9) {charSet.random()}.joinToString(""))
            }

        }
    }

    override fun toDoListItemClicked(list: ToDoItem) {
        launchActivity(list)
    }


}