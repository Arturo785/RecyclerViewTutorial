package com.example.recyclerviewdemo

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.concurrent.TimeUnit

class DetailFragment : Fragment(){

    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageDetail.setImageResource(args.itemPassed.imageResource)
        itemText.text = args.itemPassed.firstText

        imageDetail.transitionName = "image ${args.itemPassed.firstText}"
        itemText.transitionName = args.itemPassed.firstText

    }
}