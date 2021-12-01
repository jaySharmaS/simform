package com.tawniya.simformtest.repository

import com.tawniya.simformtest.datasource.local.dao.UserDao
import com.tawniya.simformtest.datasource.network.ApiClient
import com.tawniya.simformtest.datasource.network.dto.toEntity

class UserDetailsRepository(private val id: Long, private val userDao: UserDao) {

    fun getUserDetails() = userDao.getUser(id)

}