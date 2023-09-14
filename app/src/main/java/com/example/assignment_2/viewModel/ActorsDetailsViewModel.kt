package com.example.assignment_2.viewModel

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
class ActorsDetailsViewModel @Inject constructor(private val apiActors : APIConsumer) : ViewModel(){

    private val actorsData = MutableLiveData<ActorsDetails>()
    val actorsLiveData: LiveData<ActorsDetails> = actorsData

    init {
        fetchActors()
    }

    fun fetchActors() {


        val actorsCall: Call<ActorsDetails> = apiActors.getCharacters()
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
                // Handle failure
            }
        })
    }

}

