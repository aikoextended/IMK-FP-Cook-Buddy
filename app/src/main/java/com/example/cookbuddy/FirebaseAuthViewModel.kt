package com.example.cookbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirebaseAuthViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState = _authState.asStateFlow()
    
    init {
        // Check if user is already logged in
        repository.getCurrentUser()?.let {
            _authState.value = AuthState.Authenticated(it)
        }
    }
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(email, password)
            _authState.value = result.fold(
                onSuccess = { AuthState.Authenticated(it) },
                onFailure = { AuthState.Error(it.message ?: "Login failed") }
            )
        }
    }
    
    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.register(email, password)
            _authState.value = result.fold(
                onSuccess = { AuthState.Authenticated(it) },
                onFailure = { AuthState.Error(it.message ?: "Registration failed") }
            )
        }
    }
    
    fun logout() {
        repository.logout()
        _authState.value = AuthState.Initial
    }
    
    fun resetState() {
        if (_authState.value is AuthState.Error) {
            _authState.value = AuthState.Initial
        }
    }
    
    sealed class AuthState {
        object Initial : AuthState()
        object Loading : AuthState()
        data class Authenticated(val user: FirebaseUser) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}