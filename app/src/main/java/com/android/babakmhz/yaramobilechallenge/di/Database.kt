package com.android.babakmhz.yaramobilechallenge.di

import androidx.room.Room
import com.android.babakmhz.yaramobilechallenge.data.db.AppDatabase
import com.android.babakmhz.yaramobilechallenge.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDependency = module {
    factory {
        val db = Room.databaseBuilder(
            androidContext(), AppDatabase::class.java,
            DATABASE_NAME
        )
        db.build().moviesDao()
    }

}