package com.evg_ivanoff.countrydirectory

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path


interface RestCountriesAPI {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://countriesinfo21.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesAPI = retrofit.create(RestCountriesAPI::class.java)