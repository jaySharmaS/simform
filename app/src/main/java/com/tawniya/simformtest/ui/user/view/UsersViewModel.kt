package com.tawniya.simformtest.ui.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tawniya.simformtest.datasource.local.DbClient
import com.tawniya.simformtest.datasource.local.entity.toBasicInfo
import com.tawniya.simformtest.repository.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: UserRepository): ViewModel() {

    private val gson = Gson()

    val users = repository.getUsers().map {
        it.map { entity ->
            entity.toBasicInfo(gson)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }

}

class UsersViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(UserRepository(DbClient.invoke(application).userDao())) as T
        }

        throw IllegalArgumentException("ViewModel not found.")
    }

}