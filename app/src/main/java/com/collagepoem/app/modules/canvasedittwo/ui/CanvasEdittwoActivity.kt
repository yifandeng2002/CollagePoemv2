package com.collagepoem.app.modules.canvasedittwo.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasEdittwoBinding
import com.collagepoem.app.modules.canvasedittwo.`data`.viewmodel.CanvasEdittwoVM
import kotlin.String
import kotlin.Unit

class CanvasEdittwoActivity :
    BaseActivity<ActivityCanvasEdittwoBinding>(R.layout.activity_canvas_edittwo) {
  private val viewModel: CanvasEdittwoVM by viewModels<CanvasEdittwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.canvasEdittwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CANVAS_EDITTWO_ACTIVITY"

  }
}
