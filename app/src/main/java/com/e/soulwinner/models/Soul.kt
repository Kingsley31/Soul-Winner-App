package com.e.soulwinner.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "souls")
data class Soul (
	@PrimaryKey
	@SerializedName("id") val id : Int,
	@SerializedName("user_id") val user_id : Int,
	@SerializedName("firstname") val firstname : String,
	@SerializedName("lastname") val lastname : String,
	@SerializedName("phone") val phone : Int,
	@SerializedName("email") val email : String,
	@SerializedName("address") val address : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("occupation") val occupation : String,
	@SerializedName("status") val status : Int,
	@SerializedName("created_at") val created_at : String
): Parcelable