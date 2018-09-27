package com.kyrylo.kotlinmessenger

import android.app.Application

//import android.app.Activity
//import android.app.Application
//import dagger.android.DispatchingAndroidInjector
//import dagger.android.HasActivityInjector
//import javax.inject.Inject
/*
class MvpApp : Application(), HasActivityInjector {

    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
      /*  DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)*/
    }

}*/
class MvpApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}