package com.example.psmart.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "My Account"
    }
    val text: LiveData<String> = _text
}