package com.collagepoem.app.modules.landinpage.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLandinpageBinding
import com.collagepoem.app.modules.landinpage.`data`.viewmodel.LandinpageVM
import kotlin.String
import kotlin.Unit

class LandinpageActivity : BaseActivity<ActivityLandinpageBinding>(R.layout.activity_landinpage) {
  private val viewModel: LandinpageVM by viewModels<LandinpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.landinpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LANDINPAGE_ACTIVITY"

  }
}
