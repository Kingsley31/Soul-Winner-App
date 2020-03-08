package com.e.soulwinner.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeedBack (
	@SerializedName("id") val id : Int,
	@SerializedName("user_id") val user_id : Int,
	@SerializedName("message") val message : String,
	@SerializedName("created_at") val created_at : String
): Parcelable