package com.lejik.spacex.injection

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.lejik.spacex.data.RepositoryLaunches
import com.lejik.spacex.ui.factories.ViewModelFactoryLaunches

object Injection {
    private fun provideRepositoryLaunches(): RepositoryLaunches = RepositoryLaunches()

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactoryLaunches(owner, provideRepositoryLaunches())
    }
}