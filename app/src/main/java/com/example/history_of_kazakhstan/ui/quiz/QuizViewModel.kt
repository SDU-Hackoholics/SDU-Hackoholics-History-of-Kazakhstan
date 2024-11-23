package com.example.history_of_kazakhstan.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is quiz Fragment"
    }
    val text: LiveData<String> = _text
}