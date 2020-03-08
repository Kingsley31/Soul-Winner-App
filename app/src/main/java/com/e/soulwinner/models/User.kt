package com.e.soulwinner.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User (
	@PrimaryKey
	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("surname") val surname : String,
	@SerializedName("othername") val othername : String,
	@SerializedName("sex") val sex : String,
	@SerializedName("nationality") val nationality : String,
	@SerializedName("address") val address : String,
	@SerializedName("user_pic") val user_pic : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("pin_code") val pin_code : String
): Parcelable