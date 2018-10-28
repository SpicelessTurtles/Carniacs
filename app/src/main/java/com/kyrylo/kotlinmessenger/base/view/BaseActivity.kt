package com.kyrylo.kotlinmessenger.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.kyrylo.kotlinmessenger.utilities.CommonUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main_main.*

/**
 * Created by jyotidubey on 04/01/18.
 */
abstract class BaseActivity : AppCompatActivity(), MVPView,BaseFragment.CallBack{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }


    override fun hideProgress() {
  //      if (progressBar_loader != null && progressBar_loader?.visibility == View.VISIBLE) {
  //          progressBar_loader.visibility = View.GONE
  //      }
    }

    override fun showProgress() {
     //   hideProgress()
     //   progressBar_loader.visibility = View.VISIBLE
    }

    private fun performDI() = AndroidInjection.inject(this)

}