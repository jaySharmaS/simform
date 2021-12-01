package com.tawniya.simformtest.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tawniya.simformtest.datasource.local.DbConstant.Companion.DB_VERSION
import com.tawniya.simformtest.datasource.local.dao.UserDao
import com.tawniya.simformtest.datasource.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class SimformDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}