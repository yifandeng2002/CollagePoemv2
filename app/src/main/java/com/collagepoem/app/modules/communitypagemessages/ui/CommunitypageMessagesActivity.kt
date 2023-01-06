package com.collagepoem.app.modules.communitypagemessages.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageMessagesBinding
import com.collagepoem.app.modules.communitypagemessages.`data`.model.MessagesRowModel
import com.collagepoem.app.modules.communitypagemessages.`data`.viewmodel.CommunitypageMessagesVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageMessagesActivity :
    BaseActivity<ActivityCommunitypageMessagesBinding>(R.layout.activity_communitypage_messages) {
  private val viewModel: CommunitypageMessagesVM by viewModels<CommunitypageMessagesVM>()

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
  }

  override fun setUpClicks(): Unit {
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

  }
}
