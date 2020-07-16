package com.thiha.hpa_andirectory.api

import com.thiha.hpa_andirectory.model.CategoriesList
import com.thiha.hpa_andirectory.model.DirectoryList
import com.thiha.hpa_andirectory.model.Items
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DirectoryApi {

    private val directoryApiInterface: DirectoryApiInterface


    companion object {
        const val BASE_URL = "https://hpa-an-guide.khaingthinkyi.me/api/"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        directoryApiInterface = retrofit.create(DirectoryApiInterface::class.java)
    }

//    fun getFood(category: String): Call<Food> {
//        return mealDbInterface.getFood(category)
//    }

    fun getDirectoryList(): Call<DirectoryList> {
        return directoryApiInterface.getRandom()
    }

    fun getCategoriesLists(id:Int):Call<CategoriesList>{
        return directoryApiInterface.getCategoriesList(id)
    }
    fun getSearchLists(searchval:String):Call<Items>{
        return directoryApiInterface.getSearch(searchval)
    }



}