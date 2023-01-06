package com.collagepoem.app.modules.canvaspoem.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasPoemBinding
import com.collagepoem.app.modules.canvaspoem.`data`.viewmodel.CanvasPoemVM
import kotlin.String
import kotlin.Unit

class CanvasPoemActivity : BaseActivity<ActivityCanvasPoemBinding>(R.layout.activity_canvas_poem) {
  private val viewModel: CanvasPoemVM by viewModels<CanvasPoemVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.canvasPoemVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CANVAS_POEM_ACTIVITY"

  }
}
