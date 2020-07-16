package com.thiha.hpa_andirectory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.model.Lists
import kotlinx.android.synthetic.main.item_list.view.*

class DirectoryAdapter(var clickListener: DirectoryAdapterClickListener) :
    RecyclerView.Adapter<DirectoryAdapter.DirectoryAdapterViewHolder>() {


    var data = listOf<Lists>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class DirectoryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var category: Lists

        init {
            itemView.card_category.setOnClickListener(this)
        }

        fun bindMainCategory(category: Lists) {
            this.category = category
            Picasso.get()
                .load("https://hpa-an-guide.khaingthinkyi.me/"+category.list_image)
                .resize(150,150)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_view_category)

            itemView.category_text.text = category.list_name
        }

        override fun onClick(v: View?) {
            clickListener.mainAdapterOnClick(category)
        }
    }

    interface DirectoryAdapterClickListener {
        fun mainAdapterOnClick(category: Lists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryAdapterViewHolder =
        DirectoryAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DirectoryAdapterViewHolder, position: Int) =
        holder.bindMainCategory(data[position])

}