package com.lejik.spacex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lejik.spacex.model.*
import com.lejik.spacex.network.ApiServiceLaunches
import kotlin.math.max

private const val STARTING_KEY = 0

class PagingSourceLaunch : PagingSource<Int, Docs>() {
//    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    private val service = ApiServiceLaunches.create()

    override fun getRefreshKey(state: PagingState<Int, Docs>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val docs = state.closestPageToPosition(anchorPosition) ?: return null
//        return ensureValidKey(key = ((docs.docs.id?.toInt() ?: STARTING_KEY) - (state.config.pageSize / 2)))
        return docs.prevKey?.plus(1) ?: docs.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Docs> {
        val startKey = params.key ?: STARTING_KEY
        val range = startKey.until(startKey + params.loadSize)
        val request = LaunchesRequest(
            Options(
                Sort(date_utc = -1),
                page = startKey,
                limit = 10
            )
        )
        val data = service.getLaunches(request).docs//?.map { LoadResult() }
//        val prevKey = when (startKey) {
//            STARTING_KEY -> null
//            else -> range.last - 1
//        }
        val prevKey = if (startKey == 0) null else startKey - 1
//        val nextKey = range.last + 1
        val nextKey = if (data.size < params.loadSize) null else startKey + 1
        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}