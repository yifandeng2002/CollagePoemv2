package com.collagepoem.app.modules.loadingworkpage.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLoadingworkpageBinding
import com.collagepoem.app.modules.loadingworkpage.`data`.viewmodel.LoadingworkpageVM
import com.jaeger.library.StatusBarUtil
import kotlin.String
import kotlin.Unit

class LoadingworkpageActivity :
    BaseActivity<ActivityLoadingworkpageBinding>(R.layout.activity_loadingworkpage) {
  private val viewModel: LoadingworkpageVM by viewModels<LoadingworkpageVM>()

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
    binding.loadingworkpageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LOADINGWORKPAGE_ACTIVITY"

  }
}
