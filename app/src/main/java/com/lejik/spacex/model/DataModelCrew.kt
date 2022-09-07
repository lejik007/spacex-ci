package com.lejik.spacex.model

import com.google.gson.annotations.SerializedName

// 'launches' is equal to 'id' in the DataModelLaunches

//@JsonClass(generateAdapter = true)
data class Crew(
    @SerializedName("id") // 'id' is equal to 'launches' in the DataModelCrew
//    @Json(name = "id")
    val id: String?,
    @SerializedName("name")
//    @Json(name = "name")
    val name: String?,
    @SerializedName("agency")
//    @Json(name = "agency")
    val agency: String?,
    @SerializedName("status")
//    @Json(name="status")
    val status: String?
)