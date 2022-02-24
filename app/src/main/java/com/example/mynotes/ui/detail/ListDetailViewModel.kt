package com.example.mynotes.ui.detail

import androidx.lifecycle.ViewModel
import com.example.mynotes.models.TaskList

class ListDetailViewModel : ViewModel() {
    lateinit var onTaskAdded: () -> Unit
    lateinit var list: TaskList

    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}