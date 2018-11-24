package com.kyrylo.kotlinmessenger.di.module


import android.arch.persistence.room.Room
import android.content.Context
import com.kyrylo.kotlinmessenger.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule::class])
class RoomModule {

    @Provides fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "my-todo-db").allowMainThreadQueries().build()

    @Provides fun providesToDoDao(database: AppDatabase) = database.taskDao()

}