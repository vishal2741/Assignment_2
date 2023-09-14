package com.example.assignment_2.utils

import com.example.assignment_2.model.ActorsDetails
import retrofit2.Call
import retrofit2.http.GET

interface APIConsumer {
    @GET("characters")
     fun getCharacters(): Call<ActorsDetails>

    @GET("characters/staff")
     fun getStaffLists():Call<ActorsDetails>

    @GET("characters/students")
     fun getStudentsList():Call<ActorsDetails>
}