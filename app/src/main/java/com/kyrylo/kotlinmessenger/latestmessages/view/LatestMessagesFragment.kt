package com.kyrylo.kotlinmessenger.latestmessages.view

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import android.view.*
import com.kyrylo.kotlinmessenger.base.view.BaseFragment
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesMVPPresenter
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.latestmessages.view.viewholder.LatestMessageRow
import kotlinx.android.synthetic.main.fragment_latest_messages.*
import javax.inject.Inject


class LatestMessagesFragment : BaseFragment(), LatestMessagesMVPView {

    @Inject
    lateinit var presenter: LatestMessagesMVPPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor>
    @Inject
    lateinit var adapter: GroupAdapter<ViewHolder>


    companion object {
        val TAG = "LatestMessages"

        fun newInstance(): LatestMessagesFragment {
            return LatestMessagesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_latest_messages, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun refreshRecyclerViewMessages(latestMessageRow: HashMap<String, ChatMessage>) {
        adapter.clear()
        latestMessageRow.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }

    override fun setUp() {
        recycler_view_latest_messages.adapter = adapter
        recycler_view_latest_messages.addItemDecoration(DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL))
        presenter.onViewPrepared()
        adapter.setOnItemClickListener { item, view ->
            val row = item as LatestMessageRow
            presenter.setChatParterUser(row.chatPartnerUser)
            presenter.openChatMessage()
        }
    }

    override fun openChatFragment() =
        (getBaseActivity() as MainActivity).presenter.openChatFragment()


    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
