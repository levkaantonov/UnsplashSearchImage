package levkaantonov.com.study.searchimage.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import levkaantonov.com.study.searchimage.data.Photo
import levkaantonov.com.study.searchimage.api.Api
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class MyPagingSource(
    private val unsplashApi: Api,
    private val query: String
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return null
    }
}