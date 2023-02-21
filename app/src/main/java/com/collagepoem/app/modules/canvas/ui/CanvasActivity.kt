package com.collagepoem.app.modules.canvas.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.appcomponents.views.ImagePickerFragmentDialog
import com.collagepoem.app.databinding.ActivityCanvasBinding
import com.collagepoem.app.modules.canvas.data.viewmodel.CanvasVM
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.loadingworkpage.ui.LoadingworkpageActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


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
//    binding.imageFinish.setOnClickListener {
//      val destIntent = LoadingworkpageActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY)
//      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
//    }
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
  private var path: String? = null
  private var file_path: String? = null
  private var mPicGetHeight = 0
  private var mPicGetWidth = 0
  private var saveBitmap: Bitmap? = null
  private var mTotal: LinearLayout? = null
  private val mSavePositions = IntArray(2)
  private var formatter: SimpleDateFormat? =null
  private var successHandler: SuccessHandler? = null
  private var  curDate:Date?=null
  private var initHandler: InitHandler? = null
  private var str: String? =null
  private var cun=Constant()


  @RequiresApi(Build.VERSION_CODES.R)
  protected override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    super.onCreate(savedInstanceState)


    //setContentView(R.layout.activity_canvas)
    initView()
    mSave?.setOnClickListener{
      mSave!!.text = "存储中……"
      mSave!!.isEnabled = false

      var card1 =findViewById<ImageView>(R.id.imageFilebtn)
      var card2 =findViewById<ImageView>(R.id.imageCamerabtn)
      var card3 =findViewById<ImageView>(R.id.imageScissorsbtn)
      var card4 =findViewById<ImageView>(R.id.imageTrashbtn)
      card1.visibility=View.INVISIBLE
      card2.visibility=View.INVISIBLE
      card3.visibility=View.INVISIBLE
      card4.visibility=View.INVISIBLE

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

  @RequiresApi(Build.VERSION_CODES.R)
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


        formatter = SimpleDateFormat("HH_mm_ss");
        curDate = Date(System.currentTimeMillis());

        //获取当前时间
        str = "T_"+formatter!!.format(curDate);

        val imageName: String = str + Constant.SCREEN_SHOT
        path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
        file_path = path
        //获取截屏图片路径
        Constant.curSCREEN_SHOT=path+imageName

        val imageDir = File(path)

//        if (!imageDir.exists()) {
//          imageDir.mkdir()
//        }
        val file = File(imageDir, imageName)
        Log.i("TAG",file.toString())
//        try {
//          if (file.exists()) {
////            file.delete()
//          }
//          file.createNewFile()
//        } catch (e: IOException) {
//          e.printStackTrace()
//        }
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

    var card1 =findViewById<ImageView>(R.id.imageFilebtn)
    var card2 =findViewById<ImageView>(R.id.imageCamerabtn)
    var card3 =findViewById<ImageView>(R.id.imageScissorsbtn)
    var card4 =findViewById<ImageView>(R.id.imageTrashbtn)
    card1.visibility=View.VISIBLE
    card2.visibility=View.VISIBLE
    card3.visibility=View.VISIBLE
    card4.visibility=View.VISIBLE

      val destIntent = LoadingworkpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
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
