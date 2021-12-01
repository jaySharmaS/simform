package com.tawniya.simformtest

import com.tawniya.simformtest.datasource.network.ApiClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `is api working`() = runBlocking {
        assert(ApiClient.getClient()
            .getUsers().code() == 200) {
            "Api successful."
        }
    }
}