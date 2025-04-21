package com.telect.dailyquotes.vm

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telect.dailyquotes.model.Quote
import com.telect.dailyquotes.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    val snackBarHostState = SnackbarHostState()
    private val _data = MutableStateFlow<List<Quote>>(emptyList())
    val data = _data
    private val _loading = MutableStateFlow(true)
    val loading = _loading
    private val _error = MutableStateFlow("")
    val error = _error


    init {
        Log.i("TAG", ": called init")
        getQuotes()
    }

    private fun getQuotes() = viewModelScope.launch {
        try {
            _data.value = dataRepository.getQuotes()
        } catch (e: Exception) {
            Log.i("TAG", "getQuotes: ${e.message}")
            _error.value = e.message.toString()
        }
        _loading.value = false
    }

}