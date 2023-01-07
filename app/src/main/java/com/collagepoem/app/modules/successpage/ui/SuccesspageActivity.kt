package com.collagepoem.app.modules.successpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivitySuccesspageBinding
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.successpage.`data`.viewmodel.SuccesspageVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SuccesspageActivity : BaseActivity<ActivitySuccesspageBinding>(R.layout.activity_successpage)
    {
  private val viewModel: SuccesspageVM by viewModels<SuccesspageVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 654


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.successpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageClose.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
    }
    binding.imageBackbtn.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "SUCCESSPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SuccesspageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
