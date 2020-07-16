package com.thiha.hpa_andirectory.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.thiha.hpa_andirectory.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var messageArgs= DetailFragmentArgs.fromBundle(requireArguments())
        Picasso.get()
            .load("https://hpa-an-guide.khaingthinkyi.me/" + messageArgs.photo)
            .placeholder(R.drawable.ic_launcher_background)
            .into(img_Detail)

        detail_title.text=messageArgs.title
        detail_location.text=messageArgs.location
        detail_address.text=messageArgs.address
        detail_phone.text=messageArgs.phone
        detail_description.text=messageArgs.description
        var isSearch=messageArgs.isSearch
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               if (isSearch){
                   findNavController().popBackStack(R.id.mainFragment,false)
               }else{
                   findNavController().popBackStack()
               }

            }



        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)


        btnMap.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${messageArgs.location}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                startActivity(mapIntent)
            }
        }

        btnCall.setOnClickListener {
            val phone = "0${messageArgs.phone}"
            val intent =
                Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }


    }


}