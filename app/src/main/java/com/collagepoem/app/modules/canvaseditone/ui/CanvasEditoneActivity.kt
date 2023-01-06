package com.collagepoem.app.modules.canvaseditone.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasEditoneBinding
import com.collagepoem.app.modules.canvaseditone.`data`.viewmodel.CanvasEditoneVM
import kotlin.String
import kotlin.Unit

class CanvasEditoneActivity :
    BaseActivity<ActivityCanvasEditoneBinding>(R.layout.activity_canvas_editone) {
  private val viewModel: CanvasEditoneVM by viewModels<CanvasEditoneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.canvasEditoneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CANVAS_EDITONE_ACTIVITY"

  }
}
