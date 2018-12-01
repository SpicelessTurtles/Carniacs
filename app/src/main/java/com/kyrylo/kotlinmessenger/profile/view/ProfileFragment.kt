package com.kyrylo.kotlinmessenger.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseFragment
import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.users.view.UsersFragment

class ProfileFragment : BaseFragment(), MVPView {

    companion object {
        val TAG = "ProfileFragment"
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_profile, container, false)

    override fun setUp() {

    }
}