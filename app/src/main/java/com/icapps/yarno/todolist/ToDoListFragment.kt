package com.icapps.yarno.todolist

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.icapps.yarno.todolist.databinding.ToDoListFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ToDoListFragment : Fragment(){

    private var _binding: ToDoListFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var toDoListViewModel: ToDoListViewModel
    private lateinit var toDoListAdapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toDoListViewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)

        toDoListViewModel.taskList.observe(this) {

            toDoListAdapter.toDoList = it
            toDoListAdapter.notifyDataSetChanged()


        }
        toDoListViewModel.readToDoList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ToDoListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoListAdapter = ToDoListAdapter(ArrayList()) {
            navigateToTaskList(it)
        }
        binding.recView.adapter = toDoListAdapter

        with(binding.recView) {
            layoutManager = LinearLayoutManager(context)

        }
        binding.floatingActionButton.setOnClickListener {
            showDialog()
        }
    }

    private fun navigateToTaskList(list: ToDoItem) {
        val action = ToDoListFragmentDirections.actionTodoListFragmentToTaskListFragment(list)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showDialog() {
        val toDoExit = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        }

        context?.let {
            AlertDialog.Builder(it)
                .setView(toDoExit)
                .setTitle("Add To Do")
                .setPositiveButton("Create") {dialog, _ ->
                    val list = ToDoItem(toDoListAdapter.itemCount,toDoExit.text.toString())
                    toDoListViewModel.saveToDoList(list)

                    dialog.dismiss()


                }
                .create()
                .show()
        }


    }



}