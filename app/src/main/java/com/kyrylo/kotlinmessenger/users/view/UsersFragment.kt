package com.kyrylo.kotlinmessenger.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseFragment
import com.kyrylo.kotlinmessenger.users.view.viewholder.UserItem
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.users.interactor.UsersMVPInteractor
import com.kyrylo.kotlinmessenger.users.presenter.UsersMVPPresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_new_message.*
import javax.inject.Inject

class UsersFragment : BaseFragment(), UsersMVPView {

    @Inject
    lateinit var presenter: UsersMVPPresenter<UsersMVPView, UsersMVPInteractor>

    @Inject
    lateinit var adapter: GroupAdapter<ViewHolder>

    companion object {
        val TAG = "UsersFragment"
        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        recycelview_newmessage.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            val userItem = item as UserItem
            presenter.setChatParterUser(userItem.user)
            presenter.openChatMessage()
        }

        presenter.onViewPrepared()
    }

    override fun openChatFragment() =
        getMainActivity().presenter.openChatFragment()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_new_message, container, false)

    override fun refreshUserRecycleView() =
        adapter.notifyDataSetChanged()


    override fun addItem(user : UserItem) =
        adapter.add(user)

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}