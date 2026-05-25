package com.example.naylaapps.data.api

import com.example.naylaapps.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiServices {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}