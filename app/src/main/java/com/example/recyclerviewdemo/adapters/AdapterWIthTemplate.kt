package com.example.recyclerviewdemo.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerviewdemo.R
import com.example.recyclerviewdemo.data.ExampleItemData
import kotlinx.android.synthetic.main.example_item.view.*

class AdapterWIthTemplate(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // taken from https://www.youtube.com/watch?v=xSL_lSDVxnQ&t=567s

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExampleItemData>() {

        override fun areItemsTheSame(oldItem: ExampleItemData, newItem: ExampleItemData): Boolean {
            return oldItem.firstText == newItem.firstText
            // should be used for example comparing primary keys and things like that
        }

        override fun areContentsTheSame(oldItem: ExampleItemData, newItem: ExampleItemData): Boolean {
            return oldItem == newItem // will determine if the contents are the same
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.example_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is itemViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ExampleItemData>) {
        differ.submitList(list)
    }

    class itemViewHolder
    constructor(itemView: View, private val interaction: Interaction? // an interface for detecting clicks
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ExampleItemData) = with(itemView) {

            itemView.image_view_recycler_item.setImageResource(item.imageResource)
            itemView.text_view_recycler_item.text = item.firstText
            itemView.text_view_2_recycler_item.text = item.SecondText

            //Transitions
            itemView.image_view_recycler_item.transitionName = "image ${item.firstText}"
            itemView.text_view_recycler_item.transitionName = item.firstText

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item, image_view_recycler_item, text_view_recycler_item)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ExampleItemData, image : ImageView, title : TextView)
    }
}
