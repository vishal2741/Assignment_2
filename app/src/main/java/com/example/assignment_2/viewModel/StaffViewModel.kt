package com.example.assignment_2.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment_2.model.ActorsDetails
import com.example.assignment_2.utils.APIConsumer
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class StaffViewModel @Inject constructor(private val apiStaff : APIConsumer) : ViewModel(){

    private val actorsData = MutableLiveData<ActorsDetails>()
    val staffLiveData: LiveData<ActorsDetails> = actorsData

    init {
        fetchStaff()
    }

     fun fetchStaff() {


        val actorsCall: Call<ActorsDetails> = apiStaff.getStaffLists()
        actorsCall.enqueue(object : Callback<ActorsDetails> {
            override fun onResponse(call: Call<ActorsDetails>, response: Response<ActorsDetails>) {
                if (response.isSuccessful) {
                    val actorsList = response.body()
                    actorsList?.let {
                        actorsData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<ActorsDetails>, t: Throwable) {

            }
        })
    }

}