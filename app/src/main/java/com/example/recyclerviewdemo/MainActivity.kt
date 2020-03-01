package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.adapters.AdapterOne
import com.example.recyclerviewdemo.adapters.AdapterWIthTemplate
import com.example.recyclerviewdemo.data.ExampleItemData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterWIthTemplate.Interaction {

    //this is from the interaction from the recycler with template
    override fun onItemSelected(position: Int, item: ExampleItemData) {
        Toast.makeText(this,"clicked $position",Toast.LENGTH_SHORT).show()
    }

    lateinit var templateAdapter : AdapterWIthTemplate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        // this is the classic adapter

        /*main_recycler_view.adapter = AdapterOne(exampleList)
        // to set how the items are going to be displayed
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)*/

        //This is the one with the template
        templateAdapter = AdapterWIthTemplate()
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)
        templateAdapter.submitList(exampleList) // sends the list to the adapter
        main_recycler_view.adapter = templateAdapter

    }

    private fun generateDummyList(size: Int): List<ExampleItemData> {

        val list = ArrayList<ExampleItemData>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android_black_24dp
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }

            val item =
                ExampleItemData(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}
