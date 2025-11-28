package com.kznetwork.ibestie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel with common functionality for error handling and loading states
 */
abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    protected fun handleError(throwable: Throwable) {
        _error.value = throwable.message ?: "An unknown error occurred"
        _isLoading.value = false
    }

    protected fun clearError() {
        _error.value = null
    }

    protected fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    protected fun launchSafe(block: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            try {
                setLoading(true)
                block()
            } finally {
                setLoading(false)
            }
        }
    }
}

