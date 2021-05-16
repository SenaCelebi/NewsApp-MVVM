package com.senacelebi.mvvmnewsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.senacelebi.mvvmnewsapp.model.Article

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}