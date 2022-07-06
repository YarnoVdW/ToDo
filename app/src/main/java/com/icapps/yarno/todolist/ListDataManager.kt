package com.icapps.yarno.todolist

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {
    fun saveToDoList(tdList: ToDoItem) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putStringSet(tdList.description, tdList.taskList.toHashSet())
            .putInt("position_${tdList.description}", tdList.index)
            .apply()

//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//            .edit()
//        for((taskIndex, task) in tdList.list.withIndex()) {
//            sharedPreferences.putString("$taskIndex", task.toString())
//        }
//        sharedPreferences.apply()
    }

    fun readToDoList(): MutableList<ToDoItem>{
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val content = sharedPrefs.all
        val mockedItems = ArrayList<ToDoItem>()

        for (item in content) {
            if(item.key.startsWith("position")) continue
            val toDoItems = ArrayList(item.value as HashSet<String>)
            val index = content["position_${item.key}"] as Int
            mockedItems.add(ToDoItem(index, item.key, toDoItems))
        }
        return mockedItems





    }
}