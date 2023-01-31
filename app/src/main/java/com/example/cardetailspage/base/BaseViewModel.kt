package com.example.cardetailspage.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel: ViewModel() {

    fun launchOnDefault(action : suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { withContext(Dispatchers.Default) { action() } }

    fun launchOnIO(action : suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { withContext(Dispatchers.IO) { action() } }

    fun launchOnMain(action : suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { withContext(Dispatchers.Main) { action() } }

    suspend fun <T> switchToUi(action : suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Main) { action() }

    suspend fun <T> switchToIO(action : suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.IO) { action() }

    suspend fun <T> switchToDefault(action : suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Default) { action() }
}