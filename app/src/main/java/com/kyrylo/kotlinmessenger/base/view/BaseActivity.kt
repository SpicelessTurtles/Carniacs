package com.kyrylo.kotlinmessenger.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.kyrylo.kotlinmessenger.utilities.CommonUtil
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_main.*

/**
 * Created by jyotidubey on 04/01/18.
 */
abstract class BaseActivity : DaggerAppCompatActivity(), MVPView,BaseFragment.CallBack{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun hideProgress() {

    }

    override fun showProgress() {

    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    private fun performDI() = AndroidInjection.inject(this)

}