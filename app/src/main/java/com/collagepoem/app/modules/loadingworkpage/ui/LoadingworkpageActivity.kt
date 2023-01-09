package com.collagepoem.app.modules.loadingworkpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLoadingworkpageBinding
import com.collagepoem.app.modules.loadingworkpage.`data`.viewmodel.LoadingworkpageVM
import com.collagepoem.app.modules.successpage.ui.SuccesspageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class LoadingworkpageActivity :
    BaseActivity<ActivityLoadingworkpageBinding>(R.layout.activity_loadingworkpage) {
  private val viewModel: LoadingworkpageVM by viewModels<LoadingworkpageVM>()

  private val REQUEST_CODE_SUCCESSPAGE_ACTIVITY: Int = 303

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loadingworkpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageBackbtn.setOnClickListener {
      finish()
    }
    binding.imageFinish.setOnClickListener {
      val destIntent = SuccesspageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SUCCESSPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
  }

  companion object {
    const val TAG: String = "LOADINGWORKPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoadingworkpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
