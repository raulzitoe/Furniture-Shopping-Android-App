package com.group.furniture_shopping_android_app.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState.Success(ProfileStateData()))
    val uiState: StateFlow<ProfileState> = _uiState

    init {
        getProfileData()
    }

    private fun getProfileData() {
        viewModelScope.launch {
            repository.getShippingAddressesQuantity().collect {
                _uiState.value =
                    ProfileState.Success(_uiState.value.data.copy(shippingAddressesQuantity = it))
            }
        }
    }

}