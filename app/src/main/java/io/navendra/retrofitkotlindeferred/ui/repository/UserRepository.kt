package io.navendra.retrofitkotlindeferred.ui.repository

import android.service.autofill.UserData

//class UserRepository(
//    private val newsRemoteDataSource: NewsRemoteDataSource,
//    private val userData: UserData
//) {
//    val favoriteLatestNews: Flow<List<ArticleHeadline>> =
//        newsRemoteDataSource.latestNews
//            // Intermediate operation to filter the list of favorite topics
//            .map { news -> news.filter { userData.isFavoriteTopic(it) } }
//            // Intermediate operation to save the latest news in the cache
//            .onEach { news -> saveInCache(news) }
//}
//comment