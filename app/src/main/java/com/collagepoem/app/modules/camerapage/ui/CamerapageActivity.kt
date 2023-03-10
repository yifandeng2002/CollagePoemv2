package com.collagepoem.app.modules.camerapage.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCamerapageBinding
import com.collagepoem.app.modules.camerapage.`data`.viewmodel.CamerapageVM
import com.jaeger.library.StatusBarUtil
import kotlin.String
import kotlin.Unit

class CamerapageActivity : BaseActivity<ActivityCamerapageBinding>(R.layout.activity_camerapage) {
  private val viewModel: CamerapageVM by viewModels<CamerapageVM>()

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
    binding.camerapageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CAMERAPAGE_ACTIVITY"

  }
}
