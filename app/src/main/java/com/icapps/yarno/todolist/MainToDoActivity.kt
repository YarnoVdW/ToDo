package com.icapps.yarno.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainToDoActivity : AppCompatActivity() { //launcher veranderen in manifest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_to_do)
    }
}