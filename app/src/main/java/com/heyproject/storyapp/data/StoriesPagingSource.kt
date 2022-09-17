package com.heyproject.storyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.heyproject.storyapp.data.network.StoryService
import com.heyproject.storyapp.data.network.response.ListStoryItem

class StoriesPagingSource(private val storyService: StoryService) :
    PagingSource<Int, ListStoryItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData =
                storyService.getStories(position, params.loadSize, 1, "Bearer").listStory

            LoadResult.Page(
                data = responseData ?: listOf(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}