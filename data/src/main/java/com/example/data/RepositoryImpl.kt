package com.example.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.data.paging.PagingSourceNews
import com.example.domain.News
import com.example.domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class RepositoryImpl(
    private val apiService: ApiService,
    private val pagingSource: PagingSourceNews
) : NewsRepository {
    override suspend fun getNews() = apiService.getNews()

    override suspend fun getListNews() = apiService.getNewsList()
    override suspend fun getPageOfNews() = Pager(
        config = PagingConfig(
            pageSize = 30,
            prefetchDistance = 5,
            enablePlaceholders = false,
            initialLoadSize = 30,
            maxSize = 100
        ),
        pagingSourceFactory = { pagingSource }).flow

}