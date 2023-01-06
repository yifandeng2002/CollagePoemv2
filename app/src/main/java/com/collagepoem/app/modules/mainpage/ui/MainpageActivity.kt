package com.collagepoem.app.modules.mainpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityMainpageBinding
import com.collagepoem.app.modules.mainpage.`data`.viewmodel.MainpageVM
import kotlin.String
import kotlin.Unit

class MainpageActivity : BaseActivity<ActivityMainpageBinding>(R.layout.activity_mainpage) {
  private val viewModel: MainpageVM by viewModels<MainpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.mainpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
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
