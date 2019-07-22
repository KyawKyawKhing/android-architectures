package com.kkk.androidarchitectures.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kkk.androidarchitectures.data.db.dao.MovieDao
import com.kkk.androidarchitectures.data.vos.MovieVO

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "MyDBName")
                    .allowMainThreadQueries()//to allow query in main thread
                    .build()
            }
            return instance as MyDatabase
        }
    }
}