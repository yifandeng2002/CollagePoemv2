package com.collagepoem.app.modules.communitypageinfo.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageInfoBinding
import com.collagepoem.app.modules.communitypageinfo.`data`.model.CommentsRowModel
import com.collagepoem.app.modules.communitypageinfo.`data`.viewmodel.CommunitypageInfoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageInfoActivity :
    BaseActivity<ActivityCommunitypageInfoBinding>(R.layout.activity_communitypage_info) {
  private val viewModel: CommunitypageInfoVM by viewModels<CommunitypageInfoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val commentsAdapter = CommentsAdapter(viewModel.commentsList.value?:mutableListOf())
    binding.recyclerComments.adapter = commentsAdapter
    commentsAdapter.setOnItemClickListener(
    object : CommentsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : CommentsRowModel) {
        onClickRecyclerComments(view, position, item)
      }
    }
    )
    viewModel.commentsList.observe(this) {
      commentsAdapter.updateData(it)
    }
    binding.communitypageInfoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerComments(
    view: View,
    position: Int,
    item: CommentsRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "COMMUNITYPAGE_INFO_ACTIVITY"

  }
}
