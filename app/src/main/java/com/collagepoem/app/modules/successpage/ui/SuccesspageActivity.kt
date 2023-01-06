package com.collagepoem.app.modules.successpage.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivitySuccesspageBinding
import com.collagepoem.app.modules.successpage.`data`.viewmodel.SuccesspageVM
import kotlin.String
import kotlin.Unit

class SuccesspageActivity : BaseActivity<ActivitySuccesspageBinding>(R.layout.activity_successpage)
    {
  private val viewModel: SuccesspageVM by viewModels<SuccesspageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.successpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "SUCCESSPAGE_ACTIVITY"

  }
}
