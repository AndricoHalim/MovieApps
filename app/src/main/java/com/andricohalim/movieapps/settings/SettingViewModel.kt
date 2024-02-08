package com.andricohalim.movieapps.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.andricohalim.movieapps.core.domain.usecase.SettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor( private val settingUseCase: SettingUseCase): ViewModel(){
    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingUseCase.saveThemeSetting(isDarkModeActive)
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return settingUseCase.getThemeSetting().asLiveData()
    }
}