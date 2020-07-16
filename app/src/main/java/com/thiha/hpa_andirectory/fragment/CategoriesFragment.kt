
package com.thiha.hpa_andirectory.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.adapter.CategoriesAdapter
import com.thiha.hpa_andirectory.adapter.ItemAdapter
import com.thiha.hpa_andirectory.model.Info
import com.thiha.hpa_andirectory.viewmodel.DirectoryViewModel
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : Fragment(),CategoriesAdapter.CategoryAdapterClickListener {
    private lateinit var directoryViewModel: DirectoryViewModel
    var sid :Int= 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        directoryViewModel = ViewModelProvider(this).get(DirectoryViewModel::class.java)
        var messageArgs = CategoriesFragmentArgs.fromBundle(requireArguments())
        sid=messageArgs.id
        txtappBar2.text=messageArgs.ListName

        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
    }

    override fun onResume() {
        super.onResume()
        
        directoryViewModel.loadCategoriesList(sid)
        directoryViewModel.getCategoriesList().observe(
            viewLifecycleOwner,
            Observer {
                var adapter = CategoriesAdapter(this)
                adapter.data = it
                recyclerCategoriesList.adapter = adapter
                recyclerCategoriesList.layoutManager = LinearLayoutManager(context)
            }
        )
    }

    override fun categoryAdapterOnClick(info: Info) {
       var action= CategoriesFragmentDirections.actionCategoriesFragmentToDetailFragment(info.title,info.photo,info.location,info.address,info.phoneno,info.description,false)
        findNavController().navigate(action)
       //Log.e("vvv",categoryList.id.toString())
    }


}