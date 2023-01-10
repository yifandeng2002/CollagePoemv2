package com.collagepoem.app.modules.communitypagemessages.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageMessagesBinding
import com.collagepoem.app.modules.communitypagemessages.`data`.model.MessagesRowModel
import com.collagepoem.app.modules.communitypagemessages.`data`.viewmodel.CommunitypageMessagesVM
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageMessagesActivity :
    BaseActivity<ActivityCommunitypageMessagesBinding>(R.layout.activity_communitypage_messages) {
  private val viewModel: CommunitypageMessagesVM by viewModels<CommunitypageMessagesVM>()

  //    将StatusBar设置为透明
  fun setStatusBarTranslucent() {
    StatusBarUtil.setTranslucentForImageViewInFragment(
      this,
      0, null
    )
    StatusBarUtil.setLightMode(this)
  }

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val messagesAdapter = MessagesAdapter(viewModel.messagesList.value?:mutableListOf())
    binding.recyclerMessages.adapter = messagesAdapter
    messagesAdapter.setOnItemClickListener(
    object : MessagesAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : MessagesRowModel) {
        onClickRecyclerMessages(view, position, item)
      }
    }
    )
    viewModel.messagesList.observe(this) {
      messagesAdapter.updateData(it)
    }
    binding.communitypageMessagesVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageBackbtn.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerMessages(
    view: View,
    position: Int,
    item: MessagesRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "COMMUNITYPAGE_MESSAGES_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CommunitypageMessagesActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
