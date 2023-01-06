package com.collagepoem.app.modules.communitypageinfoone.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageInfoOneBinding
import com.collagepoem.app.modules.communitypageinfoone.`data`.model.Comments1RowModel
import com.collagepoem.app.modules.communitypageinfoone.`data`.viewmodel.CommunitypageInfoOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageInfoOneActivity :
    BaseActivity<ActivityCommunitypageInfoOneBinding>(R.layout.activity_communitypage_info_one) {
  private val viewModel: CommunitypageInfoOneVM by viewModels<CommunitypageInfoOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val commentsAdapter = CommentsAdapter(viewModel.commentsList.value?:mutableListOf())
    binding.recyclerComments.adapter = commentsAdapter
    commentsAdapter.setOnItemClickListener(
    object : CommentsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Comments1RowModel) {
        onClickRecyclerComments(view, position, item)
      }
    }
    )
    viewModel.commentsList.observe(this) {
      commentsAdapter.updateData(it)
    }
    binding.communitypageInfoOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerComments(
    view: View,
    position: Int,
    item: Comments1RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "COMMUNITYPAGE_INFO_ONE_ACTIVITY"

  }
}
