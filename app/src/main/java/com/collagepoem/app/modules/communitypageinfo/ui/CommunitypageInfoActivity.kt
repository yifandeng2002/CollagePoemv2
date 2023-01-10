package com.collagepoem.app.modules.communitypageinfo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageInfoBinding
import com.collagepoem.app.modules.communitypageinfo.`data`.model.CommentsRowModel
import com.collagepoem.app.modules.communitypageinfo.`data`.viewmodel.CommunitypageInfoVM
import com.collagepoem.app.modules.communitypageinfoone.ui.CommunitypageInfoOneActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageInfoActivity :
    BaseActivity<ActivityCommunitypageInfoBinding>(R.layout.activity_communitypage_info) {
  private val viewModel: CommunitypageInfoVM by viewModels<CommunitypageInfoVM>()

  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY: Int = 964


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
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.btnFollow.setOnClickListener {
      val destIntent = CommunitypageInfoOneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
    binding.imageBackbtn.setOnClickListener {
      finish()
    }
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


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CommunitypageInfoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
