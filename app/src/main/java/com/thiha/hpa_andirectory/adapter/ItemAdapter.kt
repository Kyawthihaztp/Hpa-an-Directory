package com.thiha.hpa_andirectory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thiha.hpa_andirectory.R
import com.thiha.hpa_andirectory.model.Info
import com.thiha.hpa_andirectory.model.Item
import kotlinx.android.synthetic.main.categories_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_search.view.*

class ItemAdapter(var clickListener: ItemAdapter.ItemAdapterClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    var data = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var ItemList: Item

        init {
            itemView.search_title.setOnClickListener(this)
        }

        fun bindItemList(ItemList: Item) {
            this.ItemList = ItemList

            itemView.search_title.text = ItemList.title
        }

        override fun onClick(v: View?) {
            clickListener.ItemAdapterOnClick(ItemList)
        }


    }

    interface ItemAdapterClickListener {
        fun ItemAdapterOnClick(ItemList: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false))

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)=holder.bindItemList(data[position])

}