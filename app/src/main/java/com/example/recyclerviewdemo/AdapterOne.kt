package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class AdapterOne(private val exampleList: List<ExampleItemData>) : RecyclerView.Adapter<AdapterOne.AdapterOneViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterOneViewHolder {
        // converts the xml into a real view that can be used by the recyclerList and "filled" by the viewHolder (makes kind of a template)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,parent,false)
        // viewType can be used when we want to display more than one type of view in the recycler

        return AdapterOneViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun onBindViewHolder(holder: AdapterOneViewHolder, position: Int) {
        val currentItem = exampleList[position]
        // gets the item from the list passed to the adapter and fills the item that is going to be displayed en the row

        // by convention all of this should be in a method
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.firstText
        holder.textView2.text = currentItem.SecondText

        //just a test, don't know if it is the correct way

        /*exampleList[position].let { item ->
            holder.bindExampleItem(item)
        }*/


        // this should never be done because it calls find by id every time
        //holder.itemView.text_view_recycler_item.text = currentItem.firstText
    }



    class AdapterOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // represents a single row in our recyclerView
        val imageView : ImageView = itemView.image_view_recycler_item
        val textView : TextView = itemView.text_view_recycler_item
        val textView2 : TextView = itemView.text_view_2_recycler_item


        fun bindExampleItem(item: ExampleItemData){
            imageView.setImageResource(item.imageResource)
            textView.text = item.SecondText
            textView2.text = item.SecondText
        }
    }

}