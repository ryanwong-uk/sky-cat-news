package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote

interface NewsListService {

    suspend fun getAllItems(): List<String>

    companion object {
        const val BASE_URL = "http://192.168.1.1/"
    }

    sealed class Endpoints(val url: String) {
        object GetAllItems : Endpoints("$BASE_URL/news-list")
    }
}
