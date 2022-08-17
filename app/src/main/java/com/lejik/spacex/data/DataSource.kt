package com.lejik.spacex.data

import com.lejik.spacex.R
import com.lejik.spacex.model.ResData

class DataSource {
    fun loadData(): List<ResData> {
        return listOf<ResData>(
            ResData(R.string.res0),
            ResData(R.string.res1),
            ResData(R.string.res2),
            ResData(R.string.res3),
            ResData(R.string.res4),
            ResData(R.string.res5),
            ResData(R.string.res6),
            ResData(R.string.res7),
            ResData(R.string.res8),
            ResData(R.string.res9)
        )
    }
}