package com.icapps.yarno.todolist

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

/***
 * lijm tussen alles, in mvp zou het de presenter zijn
 *
 */

class ToDoListViewModel(application: Application): AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val _tasklist = MutableLiveData<MutableList<ToDoItem>>() // gebruikt de life cycle, weet wanneer er iets gedestroyed word
    // de observer observed de live data
    val taskList: LiveData<MutableList<ToDoItem>>
        get() = _tasklist

    // zolang view niet zichtbaar is gaat er niks gebeuren, voorkomt memory leaks


    fun saveToDoList(tdList: ToDoItem) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putStringSet(tdList.description, tdList.taskList.toHashSet())
            .putInt("position_${tdList.description}", tdList.index)
            .apply()
        readToDoList()

    }

    fun readToDoList(){
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val content = sharedPrefs.all
        val mockedItems = ArrayList<ToDoItem>()

        for (item in content) {
            if(item.key.startsWith("position")) continue
            val toDoItems = ArrayList(item.value as HashSet<String>)
            val index = content["position_${item.key}"] as Int
            mockedItems.add(ToDoItem(index, item.key, toDoItems))
        }
        _tasklist.postValue(mockedItems)

    }
}