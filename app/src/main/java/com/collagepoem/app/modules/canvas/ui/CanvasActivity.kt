package com.collagepoem.app.modules.canvas.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.appcomponents.views.ImagePickerFragmentDialog
import com.collagepoem.app.databinding.ActivityCanvasBinding
import com.collagepoem.app.modules.canvas.`data`.viewmodel.CanvasVM
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.loadingworkpage.ui.LoadingworkpageActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CanvasActivity : BaseActivity<ActivityCanvasBinding>(R.layout.activity_canvas) {
  private val viewModel: CanvasVM by viewModels<CanvasVM>()

  private val REQUEST_CODE_CANVAS_POEM_ACTIVITY: Int = 464


  private val REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY: Int = 643


  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 266


  private val REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY: Int = 281


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.canvasVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageCamerabtn.setOnClickListener {
      ImagePickerFragmentDialog().show(supportFragmentManager)
      { path ->//TODO HANDLE DATA
      }

    }
    binding.imageUp.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_down ,R.anim.slide_up )
    }
    binding.imageFilebtn.setOnClickListener {
      val destIntent = FloatwindowMycutsVtwoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up ,R.anim.slide_down )
    }
    binding.imageBackbtn.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
    }
    binding.imageFinish.setOnClickListener {
      val destIntent = LoadingworkpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
  }

  companion object {
    const val TAG: String = "CANVAS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
