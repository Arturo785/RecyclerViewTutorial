package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        main_recycler_view.adapter = AdapterOne(exampleList)
        // to set how the items are going to be displayed
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<ExampleItemData> {

        val list = ArrayList<ExampleItemData>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android_black_24dp
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }

            val item = ExampleItemData(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}
