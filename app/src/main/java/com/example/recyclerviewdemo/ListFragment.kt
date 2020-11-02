package com.example.recyclerviewdemo

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdemo.adapters.AdapterWIthTemplate
import com.example.recyclerviewdemo.data.ExampleItemData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.example_item.*
import kotlinx.android.synthetic.main.example_item.view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), AdapterWIthTemplate.Interaction {

    //this is from the interaction from the recycler with template
    override fun onItemSelected(
        position: Int,
        item: ExampleItemData,
        image: ImageView,
        title: TextView
    ) {
       // Toast.makeText(context,"clicked $position", Toast.LENGTH_SHORT).show()
        val direction : NavDirections = ListFragmentDirections.actionListFragmentToDetailFragment(item)

        //every shared needs an unique name
        val extras = FragmentNavigatorExtras(
            image to "image ${item.firstText}",
            title to item.firstText
        )

        findNavController().navigate(direction, extras)

    }

    private lateinit var templateAdapter : AdapterWIthTemplate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exampleList = generateDummyList(500)

        // this is the classic adapter

        /*main_recycler_view.adapter = AdapterOne(exampleList)
        // to set how the items are going to be displayed
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)*/

        //This is the one with the template
        templateAdapter = AdapterWIthTemplate(this)
        main_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        main_recycler_view.setHasFixedSize(true)
        templateAdapter.submitList(exampleList) // sends the list to the adapter
        main_recycler_view.adapter = templateAdapter

        postponeEnterTransition()

        main_recycler_view.doOnPreDraw {
            startPostponedEnterTransition()
        }
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