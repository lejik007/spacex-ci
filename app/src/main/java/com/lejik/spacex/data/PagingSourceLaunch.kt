package com.lejik.spacex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lejik.spacex.model.Docs
import com.lejik.spacex.network.ApiServiceLaunches
import kotlin.math.max

private const val STARTING_KEY = 0

class PagingSourceLaunch : PagingSource<Int, Docs>() {
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, Docs>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val docs = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = ((docs.id?.toInt() ?: STARTING_KEY) - (state.config.pageSize / 2)))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Docs> {
        val startKey = params.key ?: STARTING_KEY
        val range = startKey.until(startKey + params.loadSize)
        val data = ApiServiceLaunches.create().getPhotos()
        val prevKey = when (startKey) {
            STARTING_KEY -> null
            else -> range.last - 1
        }
        val nextKey = range.last + 1
        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}