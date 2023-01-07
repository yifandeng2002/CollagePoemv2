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
import com.collagepoem.app.modules.communitypagemessages.ui.CommunitypageMessagesActivity
import com.collagepoem.app.modules.profilepagemyinfo.ui.ProfilepageMyinfoActivity
import com.collagepoem.app.modules.profilepagemypage.ui.ProfilepageMypageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CommunitypageActivity :
    BaseActivity<ActivityCommunitypageBinding>(R.layout.activity_communitypage) {
  private val viewModel: CommunitypageVM by viewModels<CommunitypageVM>()

  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY: Int = 276


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY: Int = 931


  private val REQUEST_CODE_PROFILEPAGE_MYINFO_ACTIVITY: Int = 992


  private val REQUEST_CODE_COMMUNITYPAGE_MESSAGES_ACTIVITY: Int = 714


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.communitypageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearCard.setOnClickListener {
      val destIntent = CommunitypageInfoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageUser.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.btnFollow.setOnClickListener {
      val destIntent = ProfilepageMyinfoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYINFO_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
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
