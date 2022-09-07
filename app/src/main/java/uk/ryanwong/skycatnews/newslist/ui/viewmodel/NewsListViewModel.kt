/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.newslist.data.repository.NewsListRepository
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsListRepository: NewsListRepository,
    @DispatcherModule.MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _newsList = MutableStateFlow(listOf<NewsItem>())
    val newsList = _newsList.asStateFlow()

    init {
        refreshNewsList()
    }

    fun refreshNewsList() {
        _isRefreshing.value = true
        viewModelScope.launch(dispatcher) {
            withContext(Dispatchers.IO) {
                newsListRepository.getNewsList().onSuccess { newsListResult ->
                    _newsList.value = newsListResult.newsItems
                    _isRefreshing.value = false
                }.onFailure { ex ->
                    // TODO: print error
                    Timber.e("!!!")
                    ex.printStackTrace()
                    _isRefreshing.value = false
                }
            }
        }
    }
}
