package com.collagepoem.app.modules.canvas.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.Display
import android.view.View
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.appcomponents.views.ImagePickerFragmentDialog
import com.collagepoem.app.databinding.ActivityCanvasBinding
import com.collagepoem.app.modules.albumpage.ui.AlbumpageActivity
import com.collagepoem.app.modules.canvas.`data`.viewmodel.CanvasVM
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.loadingworkpage.ui.LoadingworkpageActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CanvasActivity : BaseActivity<ActivityCanvasBinding>(R.layout.activity_canvas) {
  private val viewModel: CanvasVM by viewModels<CanvasVM>()
  private val REQUEST_CODE_CANVAS_POEM_ACTIVITY: Int = 454
  private val REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY: Int = 546
  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 285
  private val REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY: Int = 989

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
    binding.canvasVM = viewModel
//    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setStatusBarTranslucent()
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
      this.overridePendingTransition(R.anim.slide_up ,R.anim.slide_down )
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
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
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



  //截屏作品，并保存至图库
  private var mSave: Button? = null
  private var mSaveArea: FrameLayout? = null
  private var mCutTop = 0
  private var mCutLeft = 0
  private var mPicGet: ImageView? = null
  private var mFL: FrameLayout? = null

  private var mPicGetHeight = 0
  private var mPicGetWidth = 0
  private var saveBitmap: Bitmap? = null
  private var mTotal: LinearLayout? = null
  private val mSavePositions = IntArray(2)

  private var successHandler: SuccessHandler? = null

  private var initHandler: InitHandler? = null

  protected override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    super.onCreate(savedInstanceState)

    //setContentView(R.layout.activity_canvas)
    initView()
    mSave?.setOnClickListener{
      mSave!!.text = "存储中……"
      mSave!!.isEnabled = false
      Thread { screenshot() }.start()
    }
  }

  //override fun requestWindowFeature(featureNoTitle: Int): Boolean {}
  //override fun setContentView(activity_main: Int) {}
  private fun initView() {
    mSave = findViewById(R.id.imageFinish)
//        Log.e(ControlsProviderService.TAG, mSave!!.id.toString())
    mSaveArea = findViewById(R.id.frameStackcanvas) as FrameLayout?
    mPicGet = findViewById(R.id.iv_pic_get) as ImageView?
    mFL = findViewById(R.id.fl_pic) as FrameLayout?
    mTotal = findViewById(R.id.linearColumnbackground) as LinearLayout?
//    mSave!!.setOnClickListener (this)
    successHandler = SuccessHandler()
    initHandler = InitHandler()
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    if (hasFocus) {
      mSaveArea!!.getLocationOnScreen(mSavePositions)
      mCutLeft = mSavePositions[0]
      mCutTop = mSavePositions[1]
      mPicGetHeight = mTotal!!.height
      mPicGetWidth = mTotal!!.width
    }
  }

//  override fun onClick(v: View?) {
//        mSave!!.text = "存储中……"
//        mSave!!.isEnabled = false
//        Thread { screenshot() }.start()
//  }

  private fun screenshot() {
    // 获取屏幕
    val dView: View = getWindow().getDecorView()
    dView.isDrawingCacheEnabled = true
    dView.buildDrawingCache()

    val bmp = dView.drawingCache

    if (bmp != null) {
      try {
        //二次截图
        saveBitmap = Bitmap.createBitmap(
          mSaveArea!!.width,
          mSaveArea!!.height,
          Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(saveBitmap!!)
        val paint = Paint()
        canvas.drawBitmap(
          bmp,
          Rect(
            mCutLeft,
            mCutTop,
            mCutLeft + mSaveArea!!.width,
            mCutTop + mSaveArea!!.height
          ),
          Rect(0, 0, mSaveArea!!.width, mSaveArea!!.height),
          paint
        )
        val imageDir = File(Constant.IMAGE_DIR)

        if (!imageDir.exists()) {
          imageDir.mkdir()
        }
        val imageName: String = Constant.SCREEN_SHOT

        val file = File(imageDir, imageName)
        Log.e("TAG",file.toString())
        try {
          if (file.exists()) {
            file.delete()
          }
          file.createNewFile()
        } catch (e: IOException) {
          e.printStackTrace()
        }
        val os = FileOutputStream(file)
        saveBitmap!!.compress(Bitmap.CompressFormat.PNG, 100, os)
        os.flush()
        os.close()

        //将截图保存至相册并广播通知系统刷新
        MediaStore.Images.Media.insertImage(
          getContentResolver(),
          file.absolutePath,
          imageName,
          null
        )
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file))
        sendBroadcast(intent)
        successHandler!!.sendMessage(Message.obtain())
      } catch (e: Exception) {
        e.printStackTrace()
      }
    } else {
      initHandler!!.sendMessage(Message.obtain())
    }
  }

  /**
   * 成功动画handler
   */
  private inner class SuccessHandler : Handler() {
    override fun handleMessage(msg: Message) {
      showSuccess();
      showSuccessNew()
    }
  }

  /**
   * 恢复初始化handler
   */
  private inner class InitHandler : Handler() {
    override fun handleMessage(msg: Message) {
      Toast.makeText(this@CanvasActivity, "存储失败", Toast.LENGTH_SHORT).show()
      mSave!!.isEnabled = true
      mSave!!.text = "完成"
    }
  }

  /**
   * 截图成功后显示动画
   */
  private fun showSuccess() {
    Toast.makeText(this@CanvasActivity, "保存成功", Toast.LENGTH_SHORT).show()
    mPicGet!!.setImageBitmap(saveBitmap)
    val valueAnimator = ValueAnimator.ofFloat(1f, 0.7f)
    valueAnimator.addUpdateListener { animation ->
      val lp = mPicGet!!.layoutParams
      lp.height = (mPicGetHeight * animation.animatedValue as Float).toInt()
      lp.width = (mPicGetWidth * animation.animatedValue as Float).toInt()
      mPicGet!!.layoutParams = lp
    }
    valueAnimator.addListener(object : AnimatorListenerAdapter() {
      override fun onAnimationStart(animation: Animator) {
        mFL!!.visibility = View.VISIBLE
        mPicGet!!.visibility = View.VISIBLE
        val lp = mPicGet!!.layoutParams
        lp.height = mPicGetHeight
        lp.width = mPicGetWidth
        mPicGet!!.layoutParams = lp
      }

      override fun onAnimationEnd(animation: Animator) {
        Handler().postDelayed({
          mPicGet!!.visibility = View.GONE
          mFL!!.visibility = View.GONE
          mSave!!.isEnabled = true
          mSave!!.text = "存储到相册"
        }, 1500)
      }
    })
    valueAnimator.interpolator = DecelerateInterpolator()
    valueAnimator.duration = 800
    valueAnimator.start()
  }

  private fun showSuccessNew() {
    Toast.makeText(this@CanvasActivity, "保存成功", Toast.LENGTH_SHORT).show()
    mPicGet!!.setImageBitmap(saveBitmap)
    val paramsAnimator = ObjectAnimator.ofFloat(Wrapper(mPicGet!!), "params", 1f, 0.7f)
    paramsAnimator.duration = 800
    paramsAnimator.addListener(object : AnimatorListenerAdapter() {
      override fun onAnimationStart(animation: Animator) {
        mFL!!.visibility = View.VISIBLE
        mPicGet!!.visibility = View.VISIBLE
      }

      override fun onAnimationEnd(animation: Animator) {
        super.onAnimationEnd(animation)
        Handler().postDelayed({
          mPicGet!!.visibility = View.GONE
          mFL!!.visibility = View.GONE
          mSave!!.isEnabled = true
          mSave!!.text = "完成"
        }, 1500)
      }
    })
    paramsAnimator.start()
  }

  /**
   * 包装类
   */
  internal inner class Wrapper(private val mTarget: View) {
    var params: Float
      get() {
        val lp = mTarget.layoutParams
        return (lp.height / mPicGetHeight).toFloat()
      }
      set(params) {
        val lp = mTarget.layoutParams
        lp.height = (mPicGetHeight * params).toInt()
        lp.width = (mPicGetWidth * params).toInt()
        mTarget.requestLayout()
      }
  }

}
