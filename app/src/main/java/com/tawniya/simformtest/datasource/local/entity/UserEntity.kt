package com.tawniya.simformtest.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.tawniya.simformtest.datasource.local.DbConstant.Companion.TBL_USERS
import com.tawniya.simformtest.model.*

@Entity(tableName = TBL_USERS)
data class UserEntity(
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "dob") val dob: String,
    @ColumnInfo(name = "registered") val registered: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "cell") val cell: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "nat") val nat: String,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L

}

fun UserEntity.toBasicInfo(gson: Gson): UserBasicInfo {
    return UserBasicInfo(
        id = id,
        nameInfo = gson.fromJson(name, UserNameInfo::class.java),
        nationality = nat,
        profileImage = gson.fromJson(picture, UserProfileImage::class.java),
    )
}

fun UserEntity.toDetailedInfo(gson: Gson): UserDetailedInfo {
    return UserDetailedInfo(
        id = id,
        nameInfo = gson.fromJson(name, UserNameInfo::class.java),
        nationality = nat,
        profileImage = gson.fromJson(picture, UserProfileImage::class.java),
        location = gson.fromJson(location, UserLocation::class.java)
    )
}