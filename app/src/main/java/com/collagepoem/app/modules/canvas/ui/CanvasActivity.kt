package com.collagepoem.app.modules.canvas.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasBinding
import com.collagepoem.app.modules.canvas.`data`.viewmodel.CanvasVM
import kotlin.String
import kotlin.Unit

class CanvasActivity : BaseActivity<ActivityCanvasBinding>(R.layout.activity_canvas) {
  private val viewModel: CanvasVM by viewModels<CanvasVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.canvasVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CANVAS_ACTIVITY"

  }
}
