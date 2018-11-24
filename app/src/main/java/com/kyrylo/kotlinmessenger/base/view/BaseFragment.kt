package com.kyrylo.kotlinmessenger.base.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.kyrylo.kotlinmessenger.utilities.CommonUtil
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * Created by jyotidubey on 12/01/18.
 */
abstract class BaseFragment : Fragment(), MVPView {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun hideProgress() {
        progressBar_loader?.animate()?.alpha(0.0f)?.setDuration(150)?.setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                progressBar_loader?.visibility = View.GONE
            }
        })
    }

    override fun showProgress() {
    //    hideProgress()
        progressBar_loader?.visibility = View.VISIBLE

    }

    fun getBaseActivity() = parentActivity

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun setUp()
}