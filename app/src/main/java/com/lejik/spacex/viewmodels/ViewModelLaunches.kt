package com.lejik.spacex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lejik.spacex.data.RepositoryLaunches
import com.lejik.spacex.model.Docs
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 10

class ViewModelLaunches(
    repositoryLaunches: RepositoryLaunches
) : ViewModel() {
    val items: Flow<PagingData<Docs>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            repositoryLaunches.pagingSourceLaunch()
        }
    )
        .flow
        .cachedIn(viewModelScope)
}