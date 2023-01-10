package com.collagepoem.app.modules.canvasedittwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasEdittwoBinding
import com.collagepoem.app.modules.canvasedittwo.`data`.viewmodel.CanvasEdittwoVM
import com.jaeger.library.StatusBarUtil
import kotlin.String
import kotlin.Unit

class CanvasEdittwoActivity :
    BaseActivity<ActivityCanvasEdittwoBinding>(R.layout.activity_canvas_edittwo) {
  private val viewModel: CanvasEdittwoVM by viewModels<CanvasEdittwoVM>()

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
    binding.canvasEdittwoVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageVectorone.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CANVAS_EDITTWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasEdittwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
