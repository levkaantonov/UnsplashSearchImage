package levkaantonov.com.study.searchimage.api

import levkaantonov.com.study.searchimage.data.Photo

data class Response(
    val results: List<Photo>
)