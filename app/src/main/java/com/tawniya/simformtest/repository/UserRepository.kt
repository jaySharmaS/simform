package com.tawniya.simformtest.repository

import com.tawniya.simformtest.datasource.local.dao.UserDao
import com.tawniya.simformtest.datasource.network.ApiClient
import com.tawniya.simformtest.datasource.network.dto.toEntity

class UserRepository(private val userDao: UserDao) {

    fun getUsers() = userDao.getAllUsers()

    suspend fun refresh() {
        try {
            val users = ApiClient.getClient().getUsers().body()?.entries?.map {
                it.toEntity()
            } ?: emptyList()

            userDao.addAll(users)

        } catch (e: Exception) {

        }
    }

}