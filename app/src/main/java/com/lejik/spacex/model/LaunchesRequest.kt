package com.lejik.spacex.model

data class LaunchesRequest(
    val options: Options
)

data class Options(
    val sort: Sort,
    val page: Int,
    val limit: Int,
)

data class Sort(
    val date_utc: Int
)
