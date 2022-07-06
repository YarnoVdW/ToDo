package com.icapps.yarno.todolist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class ToDoItem(var index: Int, var description: String, var taskList: MutableList<String> = ArrayList()): Parcelable {

}
