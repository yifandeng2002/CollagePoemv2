package com.collagepoem.app.modules.communitypage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageBinding
import com.collagepoem.app.modules.communitypage.`data`.viewmodel.CommunitypageVM
import com.collagepoem.app.modules.communitypageinfo.ui.CommunitypageInfoActivity
import com.collagepoem.app.modules.communitypageinfoone.ui.CommunitypageInfoOneActivity
import com.collagepoem.app.modules.communitypagemessages.ui.CommunitypageMessagesActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.profilepagemypage.ui.ProfilepageMypageActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageActivity :
    BaseActivity<ActivityCommunitypageBinding>(R.layout.activity_communitypage) {
  private val viewModel: CommunitypageVM by viewModels<CommunitypageVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 368


  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY: Int = 556


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY: Int = 196


  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY: Int = 354


  private val REQUEST_CODE_COMMUNITYPAGE_MESSAGES_ACTIVITY: Int = 376


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
    binding.communitypageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageHome.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right ,R.anim.right_to_left )
    }
    binding.linearCard.setOnClickListener {
      val destIntent = CommunitypageInfoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out )
    }
    binding.imageUser.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right_2 ,R.anim.right_to_left_2 )
    }
    binding.btnFollow.setOnClickListener {
      val destIntent = CommunitypageInfoOneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
    binding.imageNotificationbtn.setOnClickListener {
      val destIntent = CommunitypageMessagesActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_MESSAGES_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
  }

  companion object {
    const val TAG: String = "COMMUNITYPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CommunitypageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
