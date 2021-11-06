package levkaantonov.com.study.searchimage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import levkaantonov.com.study.searchimage.api.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val unsplashApi: Api) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MyPagingSource(unsplashApi, query) }
        ).liveData
}