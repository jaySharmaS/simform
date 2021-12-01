package com.tawniya.simformtest.datasource.network.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.tawniya.simformtest.datasource.local.entity.UserEntity

data class UserDtoResponse(
    @SerializedName("info") val resultsInfo: UserResultsInfoDto,
    @SerializedName("results") val entries: List<UserResultsDto>
)

data class UserResultsDto(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: JsonObject,
    @SerializedName("location") val location: JsonObject,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: JsonObject,
    @SerializedName("dob") val dob: JsonObject,
    @SerializedName("registered") val registered: JsonObject,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("id") val id: JsonObject,
    @SerializedName("picture") val picture: JsonObject,
    @SerializedName("nat") val nat: String,
)

data class UserResultsInfoDto(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String,
)

fun UserResultsDto.toEntity(): UserEntity {
    return UserEntity(
        gender = gender,
        name = name.toString(),
        location = location.toString(),
        email = email,
        login = login.toString(),
        dob = dob.toString(),
        registered = registered.toString(),
        phone = phone,
        cell = cell,
        picture = picture.toString(),
        nat = nat,
    )
}