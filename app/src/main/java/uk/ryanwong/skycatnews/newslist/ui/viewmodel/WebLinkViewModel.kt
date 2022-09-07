/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.newslist.data.repository.NewsListRepository
import javax.inject.Inject

@HiltViewModel
class WebLinkViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val newsListRepository: NewsListRepository,
    @DispatcherModule.MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    init {
        val listId = stateHandle.get<Int>("list_id") ?: 0
        //  getStory()
    }
}
