package com.group.furniture_shopping_android_app.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState.Success(ProfileStateData()))
    val uiState: StateFlow<ProfileState> = _uiState

    init {
        getDatabaseData()
    }

    private fun getDatabaseData() {
        viewModelScope.launch {
            repository.getShippingAddressesQuantity().collect { quantity ->
                _uiState.update { ProfileState.Success(it.data.copy(shippingAddressesQuantity = quantity)) }
            }
        }
        viewModelScope.launch {
            val userData = repository.getProfileData()
            _uiState.update {
                ProfileState.Success(
                    it.data.copy(
                        name = userData.name ?: "",
                        email = userData.email ?: ""
                    )
                )
            }

        }
    }
}