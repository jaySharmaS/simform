package com.tawniya.simformtest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserBasicInfo(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("nameInfo") val nameInfo: UserNameInfo,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("profileImage") val profileImage: UserProfileImage,
): Parcelable

// TODO add more items into this model
@Parcelize
data class UserDetailedInfo(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("nameInfo") val nameInfo: UserNameInfo,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("profileImage") val profileImage: UserProfileImage,
    @SerializedName("location") val location: UserLocation,
): Parcelable

@Parcelize
data class UserNameInfo(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
): Parcelable

@Parcelize
data class UserProfileImage(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String,
): Parcelable

@Parcelize
data class UserLocation(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
): Parcelable

@Parcelize
data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
): Parcelable