package com.collagepoem.app.modules.mainpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityMainpageBinding
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.communitypage.ui.CommunitypageActivity
import com.collagepoem.app.modules.mainpage.`data`.viewmodel.MainpageVM
import com.collagepoem.app.modules.profilepagemypage.ui.ProfilepageMypageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class MainpageActivity : BaseActivity<ActivityMainpageBinding>(R.layout.activity_mainpage) {
  private val viewModel: MainpageVM by viewModels<MainpageVM>()

  private val REQUEST_CODE_CANVAS_POEM_ACTIVITY: Int = 682


  private val REQUEST_CODE_COMMUNITYPAGE_ACTIVITY: Int = 237


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY: Int = 411


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.mainpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imagePoemcard.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageEye.setOnClickListener {
      val destIntent = CommunitypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.right_to_left ,R.anim.left_to_right )
    }
    binding.imageImageprofilepi.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageImageAddNew.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imagePoemcardOne.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageUser.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.right_to_left ,R.anim.left_to_right )
    }
  }

  companion object {
    const val TAG: String = "MAINPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MainpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
