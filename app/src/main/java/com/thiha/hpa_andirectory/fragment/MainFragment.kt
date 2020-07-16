package com.thiha.hpa_andirectory.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.adapter.DirectoryAdapter
import com.thiha.hpa_andirectory.model.Lists
import com.thiha.hpa_andirectory.viewmodel.DirectoryViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), DirectoryAdapter.DirectoryAdapterClickListener {
    private lateinit var directoryViewModel: DirectoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        directoryViewModel = ViewModelProvider(this).get(DirectoryViewModel::class.java)
        btnsearch.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        directoryViewModel.loadDirectoryList()
        directoryViewModel.getRandomList().observe(
            viewLifecycleOwner,
            Observer {
                var adapter = DirectoryAdapter(this)
                adapter.data = it
                recyclerList.adapter = adapter
                recyclerList.layoutManager = GridLayoutManager(context, 2)
            }
        )
    }

    override fun mainAdapterOnClick(category: Lists) {
        var action=MainFragmentDirections.actionMainFragmentToCategoriesFragment(category.id,category.list_name)
        findNavController().navigate(action)
        Log.e("Data : ",category.id.toString())

    }
}