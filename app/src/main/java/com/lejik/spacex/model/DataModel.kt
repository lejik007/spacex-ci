package com.lejik.spacex.model

import android.telecom.Call
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName

//@JsonClass(generateAdapter = true)
data class Docs(
    @SerializedName("links")
//    @Json(name = "links")
    val links: Links?,
    @SerializedName("name")
//    @Json(name = "name")
    val name: String?,
    @SerializedName("cores")
//    @Json(name = "cores")
    val cores: List<Cores>?, // Угловые скобки ставятся, когда объект в квадратных скобках
    @SerializedName("success")
//    @Json(name = "success")
    val success: Boolean?,
    @SerializedName("date_utc")
//    @Json(name = "date_utc")
    val date_utc: String?,
    @SerializedName("details")
//    @Json(name="details")
    val details: String?
)

//@JsonClass(generateAdapter = true)
data class Links(
    @SerializedName("patch")
//    @Json(name = "patch")
    val patch: Patch?
)

//@JsonClass(generateAdapter = true)
data class Patch(
    @SerializedName("small")
//    @Json(name = "small")
    val small: String?
)

//@JsonClass(generateAdapter = true)
data class Cores(
    @SerializedName("flight")
//    @Json(name = "flight")
    val flight: Int?
)