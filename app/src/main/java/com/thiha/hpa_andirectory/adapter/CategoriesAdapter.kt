package com.thiha.hpa_andirectory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.model.Info
import kotlinx.android.synthetic.main.categories_list.view.*
import kotlinx.android.synthetic.main.item_list.view.card_category

class CategoriesAdapter(var clickListener: CategoryAdapterClickListener) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryAdapterViewHolder>() {


    var data = listOf<Info>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var categorylist: Info

        init {
            itemView.card_category.setOnClickListener(this)
        }

        fun bindCategoryList(categorylist: Info) {
            this.categorylist = categorylist
            Picasso.get()
                .load("https://hpa-an-guide.khaingthinkyi.me/" + categorylist.photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_categoryList)

            itemView.categories_text.text = categorylist.title
        }

        override fun onClick(v: View?) {
           clickListener.categoryAdapterOnClick(categorylist)

        }


    }

    interface CategoryAdapterClickListener {
        fun categoryAdapterOnClick(categoryList: Info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder =
        CategoryAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.categories_list,parent,false))

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int)=holder.bindCategoryList(data[position])

}