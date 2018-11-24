package com.kyrylo.kotlinmessenger.database.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.database.entities.Task
import io.reactivex.Maybe

@Dao interface TaskDAO {

    @Query("select * from article")
    fun getAllArticles(): List<Task>

    @Query("select * from article where id = :articleId")
    fun findArticleById(articleId: Long): Maybe<Task>

    @Insert(onConflict = REPLACE)
    fun insertArticle(task: Task)

    @Update(onConflict = REPLACE)
    fun updateArticle(task: Task)

    @Delete
    fun deleteArticle(task: Task)
}