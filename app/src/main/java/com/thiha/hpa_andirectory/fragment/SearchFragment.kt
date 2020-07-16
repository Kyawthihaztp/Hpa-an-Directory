package com.thiha.hpa_andirectory.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.adapter.CategoriesAdapter
import com.thiha.hpa_andirectory.adapter.ItemAdapter
import com.thiha.hpa_andirectory.model.Item
import com.thiha.hpa_andirectory.viewmodel.DirectoryViewModel
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(),ItemAdapter.ItemAdapterClickListener {

    private lateinit var directoryViewModel: DirectoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        directoryViewModel = ViewModelProvider(this).get(DirectoryViewModel::class.java)

        txtappBar3.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId==EditorInfo.IME_ACTION_SEARCH){
                Log.e(">>>>",txtappBar3.text.toString())

                if (txtappBar3.text.toString().isNotEmpty()){
                    directoryViewModel.loadSearchList(txtappBar3.text.toString())
                    directoryViewModel.getsearchList().observe(
                        viewLifecycleOwner, Observer {

                            var adapter = ItemAdapter(this)
                            adapter.data = it
                            recyclerSearch.adapter = adapter
                            recyclerSearch.layoutManager = LinearLayoutManager(context)

                            Log.e(">>>>>>>>>>",it.toString())
                        }
                    )
                }
                true
            }else false

        })

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
    }

    override fun ItemAdapterOnClick(ItemList: Item) {
        var action=SearchFragmentDirections.actionSearchFragmentToDetailFragment(ItemList.title,ItemList.photo,ItemList.location,ItemList.address,ItemList.phoneno,ItemList.description,true)
        findNavController().navigate(action)
    }


}