package com.collagepoem.app.modules.canvaseditone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCanvasEditoneBinding
import com.collagepoem.app.modules.canvaseditone.`data`.viewmodel.CanvasEditoneVM
import com.collagepoem.app.modules.canvasedittwo.ui.CanvasEdittwoActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CanvasEditoneActivity :
    BaseActivity<ActivityCanvasEditoneBinding>(R.layout.activity_canvas_editone) {
  private val viewModel: CanvasEditoneVM by viewModels<CanvasEditoneVM>()

  private val REQUEST_CODE_CANVAS_EDITTWO_ACTIVITY: Int = 667

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
    binding.canvasEditoneVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageImgcircle.setOnClickListener {
      val destIntent = CanvasEdittwoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_EDITTWO_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageVectortwo.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CANVAS_EDITONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasEditoneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
