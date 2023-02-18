package com.collagepoem.app.modules.canvaspoem.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.modules.landinpage.views.ImagePickerFragmentDialog
import com.collagepoem.app.databinding.ActivityCanvasPoemBinding
import com.collagepoem.app.modules.canvas.ui.CanvasActivity
import com.collagepoem.app.modules.canvaseditone.ui.CanvasEditoneActivity
import com.collagepoem.app.modules.canvaspoem.`data`.viewmodel.CanvasPoemVM
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CanvasPoemActivity : BaseActivity<ActivityCanvasPoemBinding>(R.layout.activity_canvas_poem) {
  private val viewModel: CanvasPoemVM by viewModels<CanvasPoemVM>()

  private val REQUEST_CODE_CANVAS_ACTIVITY: Int = 653


  private val REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY: Int = 809


  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 831


  private val REQUEST_CODE_CANVAS_EDITONE_ACTIVITY: Int = 425

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
    binding.canvasPoemVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageSwitchbtn.setOnClickListener {
      val destIntent = CanvasActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
    }
    binding.imageFilebtn.setOnClickListener {
      val destIntent = FloatwindowMycutsVtwoActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
      startActivityForResult(destIntent, REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
    }
    binding.imageCamerabtn.setOnClickListener {
      ImagePickerFragmentDialog().show(supportFragmentManager)
      { path ->//TODO HANDLE DATA
      }

    }
    binding.imageImageBack.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
    }
    binding.imageScissorsbtn.setOnClickListener {
      val destIntent = CanvasEditoneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_EDITONE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageWihtepic.setOnClickListener {
      val destIntent = CanvasActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
    }
  }

  companion object {
    const val TAG: String = "CANVAS_POEM_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasPoemActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
