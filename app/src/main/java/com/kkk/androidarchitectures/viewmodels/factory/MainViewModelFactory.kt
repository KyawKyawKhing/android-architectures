package com.kkk.androidarchitectures.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kkk.androidarchitectures.data.repositories.MainRepository
import com.kkk.androidarchitectures.viewmodels.MainViewModel

class MainViewModelFactory(val repo: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}
