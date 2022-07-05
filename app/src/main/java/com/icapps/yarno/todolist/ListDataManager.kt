package com.icapps.yarno.todolist

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {
    fun saveToDoList(tdList: MutableList<String>) {
//        PreferenceManager.getDefaultSharedPreferences(context)
//            .edit()
//            .putStringSet("mocked_data", tdList.toHashSet())
//            .apply()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
        for((taskIndex, task) in tdList.withIndex()) {
            sharedPreferences.putString("$taskIndex", task)
        }
        sharedPreferences.apply()
    }

    fun readToDoList(): MutableList<String>{
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val content = sharedPrefs.all
        val mockedItems = ArrayList<String>()
        for (item in content) {
            mockedItems.add(item.value.toString())
        }
        return mockedItems



    }
}