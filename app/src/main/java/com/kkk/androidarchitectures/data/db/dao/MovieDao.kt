package com.kkk.androidarchitectures.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kkk.androidarchitectures.data.vos.MovieVO

@Dao
interface MovieDao {

    @get:Query("select * from movie")
    val allData: List<MovieVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MovieVO>)

}