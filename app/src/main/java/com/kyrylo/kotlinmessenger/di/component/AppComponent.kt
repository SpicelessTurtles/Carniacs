package com.kyrylo.kotlinmessenger.di.component

import android.app.Application
import com.kyrylo.kotlinmessenger.MvpApp
import com.kyrylo.kotlinmessenger.di.builder.ActivityBuilder
import com.mindorks.framework.mvp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by jyotidubey on 05/01/18.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}