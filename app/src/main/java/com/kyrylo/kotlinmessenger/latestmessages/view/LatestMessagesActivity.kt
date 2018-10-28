package com.kyrylo.kotlinmessenger.latestmessages.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.R.mipmap.ic_launcher_round
import com.kyrylo.kotlinmessenger.models.ChatMessage
import com.kyrylo.kotlinmessenger.models.User
import com.kyrylo.kotlinmessenger.register.view.RegisterActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.*
import android.support.v4.app.NotificationCompat
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.messages.ChatLogActivity
import com.kyrylo.kotlinmessenger.messages.LatestMessageRow
import com.kyrylo.kotlinmessenger.messages.NewMessageActivity
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesMVPPresenter
import javax.inject.Inject


class LatestMessagesActivity : BaseActivity() {

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {
    }


    @Inject
    lateinit var presenter: LatestMessagesMVPPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor>

    companion object {
        var currentUser: User? = null
        val TAG = "LatestMessages"
    }

    val adapter = GroupAdapter<ViewHolder>()

    val latesMessagesMap = HashMap<String, ChatMessage>()

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "notificationExample"
    private val description = "hi"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //  setupDummyRows()
        recycler_view_latest_messages.adapter = adapter
        recycler_view_latest_messages.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        adapter.setOnItemClickListener{item, view ->

            val intent = Intent(this, ChatLogActivity::class.java)

            val row = item as LatestMessageRow

            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

        listenForLatestMessages()

        fetchCurrentUser()

    //    verifyIsUserLoggedIn()

    }


    private fun refreshRecyclerViewMessages() {
        adapter.clear()
        latesMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }

    private fun listenForLatestMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latesMessagesMap[p0.key!!] = chatMessage

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val audioAttributes = AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build()

                    notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)
                    notificationChannel.setSound(Uri.parse("android.resource://" + packageName + "/" + R.raw.notification),audioAttributes)
                    notificationManager.createNotificationChannel(notificationChannel)


                }
                    builder = NotificationCompat.Builder(this@LatestMessagesActivity,channelId)

                      builder.setContentTitle("Messanger")
                            .setContentText(chatMessage.text)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(this@LatestMessagesActivity.resources,ic_launcher_round))
                            .setSound(Uri.parse("android.resource://" + packageName + "/" + R.raw.notification))


                notificationManager.notify(1,builder.build())

                refreshRecyclerViewMessages()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latesMessagesMap[p0.key!!] = chatMessage

                refreshRecyclerViewMessages()
            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })
    }

    fun setupDummyRows() {

//        adapter.add(LatestMessageRow())
//        adapter.add(LatestMessageRow())
//        adapter.add(LatestMessageRow())


    }

    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                currentUser = p0.getValue(User::class.java)
                Log.d("Latest message:", "Current user ${currentUser?.username}")
            }

            override fun onCancelled(p0: DatabaseError) {


            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_new_message -> {
                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_signed_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }
}
