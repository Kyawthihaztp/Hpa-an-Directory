package com.thiha.hpa_andirectory.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiha.hpa_andirectory.api.DirectoryApi
import com.thiha.hpa_andirectory.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DirectoryViewModel : ViewModel() {


    var loadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    fun getError(): LiveData<Boolean> = loadError
    fun getLoading(): LiveData<Boolean> = loading

    var directoryList: MutableLiveData<List<Lists>> = MutableLiveData()
    fun getRandomList(): LiveData<List<Lists>> = directoryList

    var categoriesList: MutableLiveData<List<Info>> = MutableLiveData()
    fun getCategoriesList(): LiveData<List<Info>> = categoriesList

    var searchlist: MutableLiveData<List<Item>> = MutableLiveData()
    fun getsearchList(): LiveData<List<Item>> = searchlist




    private val directoryApi: DirectoryApi = DirectoryApi()

    fun loadDirectoryList() {
        loading.value = true
        val apiCall = directoryApi.getDirectoryList()

        apiCall.enqueue(object : Callback<DirectoryList> {

            override fun onFailure(call: Call<DirectoryList>, t: Throwable) {
//                println("ERROR")
                loadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<DirectoryList>, response: Response<DirectoryList>) {
//                println("RESPONSE ${response.body().toString()}")
                response.isSuccessful.let {
                    loading.value = false
                    val resultList: List<Lists> = response.body()?.lists ?: emptyList()
                    directoryList.value = resultList
                }
            }

        })
    }

    fun loadCategoriesList(id:Int) {
        loading.value = true
        val apiCall = directoryApi.getCategoriesLists(id)

        apiCall.enqueue(object : Callback<CategoriesList> {
            override fun onFailure(call: Call<CategoriesList>, t: Throwable) {
                loadError.value=true
                loading.value=false
            }

            override fun onResponse(
                call: Call<CategoriesList>,
                response: Response<CategoriesList>
            ) {
               loading.value=false
                val categorieslist:List<Info> = response.body()?.infos?: emptyList()
                categoriesList.value=categorieslist
            }


        })
    }
    fun loadSearchList(searchval:String) {
        loading.value = true
        val apiCall = directoryApi.getSearchLists(searchval)

        apiCall.enqueue(object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
                loadError.value=true
                loading.value=false
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                loading.value= false
                val searchList:List<Item> = response.body()?.items?:emptyList()
                searchlist.value=searchList

            }
//            override fun onFailure(call: Call<CategoriesList>, t: Throwable) {
//                loadError.value=true
//                loading.value=false
//            }
//
//            override fun onResponse(
//                call: Call<CategoriesList>,
//                response: Response<CategoriesList>
//            ) {
//                loading.value=false
//                val searchlist:List<Info> = response.body()?.infos?: emptyList()
//                searchList.value=searchlist
//                Log.e(">>>",response.body().toString())
//            }


        })
    }






}