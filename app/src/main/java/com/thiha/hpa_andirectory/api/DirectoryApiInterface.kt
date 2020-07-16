package com.thiha.hpa_andirectory.api
import com.thiha.hpa_andirectory.model.CategoriesList
import com.thiha.hpa_andirectory.model.DirectoryList
import com.thiha.hpa_andirectory.model.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DirectoryApiInterface {

//    @GET("filter.php")
//    fun getFood(@Query("c") foodType: String): Call<Food>

    @GET("list")
    fun getRandom(): Call<DirectoryList>

    @GET("getList")
    fun getCategoriesList(
        @Query("listing_id")id:Int
    ):Call<CategoriesList>

    @POST("searches")
    fun getSearch(
        @Query("searchval")searchval:String

    ):Call<Items>




}