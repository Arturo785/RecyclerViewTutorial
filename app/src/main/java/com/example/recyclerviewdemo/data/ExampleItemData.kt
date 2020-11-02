package com.example.recyclerviewdemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExampleItemData(
    val imageResource : Int,
    val firstText : String,
    val SecondText : String
) : Parcelable