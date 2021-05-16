package com.senacelebi.mvvmnewsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senacelebi.mvvmnewsapp.model.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converter::class)
abstract class ArticleDB : RoomDatabase() {
    abstract  fun getArticleDAO(): Dao

    companion object{
        @Volatile
        private var nstance: ArticleDB? = null
        private val LOCK = Any()

        operator  fun invoke(context: Context) = nstance ?: synchronized(LOCK){
            nstance ?: createDB(context).also { nstance = it}
        }

        private fun createDB(context: Context)=
            Room.databaseBuilder(
               context.applicationContext,
                ArticleDB::class.java,
                "article_db.db"
            ).build()
    }
}