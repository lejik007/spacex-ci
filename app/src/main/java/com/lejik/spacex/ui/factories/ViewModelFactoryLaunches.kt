package com.lejik.spacex.ui.factories

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.lejik.spacex.data.RepositoryLaunches
import com.lejik.spacex.viewmodels.ViewModelLaunches

class ViewModelFactoryLaunches(
    owner: SavedStateRegistryOwner,
    private val repositoryLaunches: RepositoryLaunches
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ViewModelLaunches::class.java)) {
            return ViewModelLaunches(repositoryLaunches) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}