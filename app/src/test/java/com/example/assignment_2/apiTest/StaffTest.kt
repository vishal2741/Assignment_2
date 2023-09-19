package com.example.assignment_2.apiTest

import com.example.assignment_2.model.ActorsDetails
import com.example.assignment_2.utils.APIConsumer
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StaffTest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var ApiService : APIConsumer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        ApiService = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")).build().create(APIConsumer::class.java)
    }

    @Test
    fun testStaffData() {
        val responseBody = "[]"
        mockWebServer.enqueue(
            MockResponse()
            .setResponseCode(200)
            .setBody(responseBody))

        val call: Call<ActorsDetails> = ApiService.getStaffLists()
        val response = call.execute()
        runBlocking {
            mockWebServer.takeRequest()
            assert(response.isSuccessful)
            Assert.assertNotNull(response.body())
        }
    }


    @Test
    fun testStaffAndResponseError() {

        val responseBody = "404! Error....... Data is Not Found"
        val mockResponse = MockResponse()
            .setResponseCode(404)
            .setBody(responseBody)
        mockWebServer.enqueue(mockResponse)

        val call: Call<ActorsDetails> = ApiService.getStaffLists()
        val response: Response<ActorsDetails> = call.execute()
        mockWebServer.takeRequest()

        Assert.assertFalse(response.isSuccessful)
        Assert.assertEquals(404, response.code())
        val errorResponseBody = response.errorBody()?.string()
        Assert.assertEquals(responseBody, errorResponseBody)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}