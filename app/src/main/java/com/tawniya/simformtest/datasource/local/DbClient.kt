package com.tawniya.simformtest.datasource.local

import android.app.Application
import androidx.room.Room

class DbClient {

    companion object {

        @Volatile
        private var instance: SimformDatabase? = null
        private val LOCK = Any()

        operator fun invoke(application: Application)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(application).also { instance = it}
        }

        private fun buildDatabase(application: Application) = Room.databaseBuilder(application, SimformDatabase::class.java, DbConstant.DB_NAME).build()

    }

}