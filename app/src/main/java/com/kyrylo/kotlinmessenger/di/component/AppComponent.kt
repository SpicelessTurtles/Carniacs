package com.kyrylo.kotlinmessenger.di.component

import android.app.Application
import com.kyrylo.kotlinmessenger.MvpApp
import com.kyrylo.kotlinmessenger.di.builder.ActivityBuilder
import com.kyrylo.kotlinmessenger.di.module.AppModule
import com.kyrylo.kotlinmessenger.di.module.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by jyotidubey on 05/01/18.
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (RoomModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}