package com.jakadam.mvlb.api

import com.jakadam.mvlb.api.model.Article
import com.jakadam.mvlb.api.model.Author
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("wp/v2/posts")
    fun getArticles(@Query("categories") category: Int): Observable<List<Article>>

    @GET("wp/v2/users/{authorId}")
    fun getAuthor(@Path("authorId") authorId: Int): Observable<Author>

}