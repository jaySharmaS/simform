package com.tawniya.simformtest.ui.user.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.google.gson.Gson
import com.tawniya.simformtest.datasource.local.DbClient
import com.tawniya.simformtest.datasource.local.entity.toDetailedInfo
import com.tawniya.simformtest.repository.UserDetailsRepository

class UserDetailsViewModel(repository: UserDetailsRepository): ViewModel() {

    private val gson = Gson()

    val userDetails = repository.getUserDetails().map {
        it.toDetailedInfo(gson)
    }

}

class UsersViewModelFactory(private val id: Long, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(UserDetailsRepository(id, DbClient.invoke(application).userDao())) as T
        }

        throw IllegalArgumentException("ViewModel not found.")
    }

}