package com.collagepoem.app.modules.canvas.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.ImageView
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
import java.io.FileNotFoundException
import java.io.FileOutputStream
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

  /*private var bitmap //生成的位图
          : Bitmap? = null
  private lateinit var img: ImageView
  private lateinit var btn: ImageView
  var width = 0
  var height = 0

  // 判断授权列表
  private var unGrantedPermissions: List<String>? = null
  protected override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // 获取 UI 元素
    setContentView(R.layout.activity_canvas)
    img = findViewById(R.id.imageCanvas)
    btn = findViewById(R.id.imageFinish)

    // 获取屏幕的宽高
    val display: Display = getWindowManager().getDefaultDisplay()
    width = display.getWidth()
    height = display.getHeight()
    Log.i(AlbumpageActivity.TAG, "width = $width,height = $height")

    // 点击按钮截图
    btn.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        Log.e(AlbumpageActivity.TAG,"lkq is a puppy")
        img.setImageBitmap(getBitmap(this, 0, 0, width, height))
      }
    })
    checkPermissions(this)
  }

  *//**
   * 获取屏幕截图
   * @param activity
   * @param x
   * @param y
   * @param width
   * @param height
   * @return
   *//*
  private fun getBitmap(activity: View.OnClickListener, x: Int, y: Int, width: Int, height: Int): Bitmap? {
//    val view: View = activity.Window().DecorView()
//    view.setDrawingCacheEnabled(true)
//    view.buildDrawingCache()
//    bitmap = view.getDrawingCache()

    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawinocache()

    // 截图位置 生成的 bitmap
    bitmap = Bitmap.createBitmap(bitmap!!, x, y, width, height)
    try {
      // 保存地址
      val fout = FileOutputStream("mnt/sdcard/test.png")
      bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, fout)
    } catch (e: FileNotFoundException) {
      // TODO Auto-generated catch block
      e.printStackTrace()
    }

//        //保存到相册
//        try {
//            //传图片路径就ok
//            MediaStore.Images.Media.insertImage(contentResolver, mFilePath, "title", "description")
//            sendBroadcast(
//                Intent(
//                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                    Uri.fromFile(File(mFilePath))
//                )
//            )
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//            return
//        }

    view.setDrawingCacheEnabled(false)
    return bitmap
  }

  *//**
   * 申请权限
   * @param activity
   *//*
  fun checkPermissions(activity: Activity?) {
    Log.i(AlbumpageActivity.TAG, "checkPermissions: 检测权限")
    unGrantedPermissions = ArrayList()
    val MANDATORY_PERMISSIONS = arrayOf(
      "android.permission.CAMERA",
      "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"
    )
    Log.i(AlbumpageActivity.TAG, "checkPermissions: MANDATORY_PERMISSIONS :$MANDATORY_PERMISSIONS")
    for (permission in MANDATORY_PERMISSIONS) {
      Log.i(AlbumpageActivity.TAG, "checkPermissions: unGrantedPermissions")
      if (ContextCompat.checkSelfPermission(
          activity!!,
          permission
        ) !== PackageManager.PERMISSION_GRANTED
      ) {
        (unGrantedPermissions as ArrayList<String>).add(permission)
      }
    }
    if ((unGrantedPermissions as ArrayList<String>).size !== 0) { //已经获得了所有权限，开始加入聊天室
      val array = arrayOfNulls<String>((unGrantedPermissions as ArrayList<String>).size)
      ActivityCompat.requestPermissions(activity!!, (unGrantedPermissions as ArrayList<String>).toArray(array), 0)
      Log.i(AlbumpageActivity.TAG, "checkPermissions: 已经获得了所有权限")
    }
    Log.i(AlbumpageActivity.TAG, "checkPermissions: Is Over")
  }*/
}
