package com.e.soulwinner.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class SojApiResponse (
	@SerializedName("status") val status : Int,
	@SerializedName("message") val message : String,
	@SerializedName("data") val data : Any
)