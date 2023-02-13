package com.collagepoem.app.modules.canvaseditone.ui

import android.app.ActionBar
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.collagepoem.app.R
import com.collagepoem.app.modules.canvaseditone.data.viewmodel.CanvasEditoneVM
import com.collagepoem.app.modules.canvasedittwo.ui.CanvasEdittwoActivity
import com.jaeger.library.StatusBarUtil
import java.io.FileNotFoundException
import java.io.FileOutputStream


@Suppress("DEPRECATION")
class CanvasEditoneActivity :
    AppCompatActivity(), View.OnTouchListener {
  private val viewModel: CanvasEditoneVM by viewModels<CanvasEditoneVM>()

  private val REQUEST_CODE_CANVAS_EDITTWO_ACTIVITY: Int = 667

  private var x = 0
  private var y = 0
  private var m = 0
  private var n = 0
  private var width= 0
  private var height= 0
  private var bitmap: Bitmap? = null
  private var myView: MyView? = null
  private var image1: View? = null
  private var image2: ImageView? = null
  private var button: ImageView? = null
  private var paint: Paint? = null
  private var path: Path? = null
  private var downX = 0f
  private  var downY: Float = 0f
  private var orgX: Int = 0
  private  var orgY: Int = 0
  private var tempX = 0f
  private  var tempY: Float = 0f
  private var back: View? = null
  private var cav: Canvas? = null

  fun setStatusBarTranslucent() {
    StatusBarUtil.setTranslucentForImageViewInFragment(
      this,
      0, null
    )
    StatusBarUtil.setLightMode(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_canvas_editone)
    image1 = findViewById(R.id.imageImgbackground)
    image2 = findViewById<View>(R.id.imageImgcircle) as ImageView
    myView = MyView(this)
    button = findViewById(R.id.imageScissors)
    back = findViewById(R.id.imageVectortwo)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    setStatusBarTranslucent()
    image2!!.setOnClickListener {
      val destIntent = CanvasEdittwoActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_EDITTWO_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out)
    }
    back!!.setOnClickListener {
      finish()
    }
    button!!.setOnClickListener{
      if (myView!!.isSign) {
        myView!!.setSeat(0, 0, 0, 0)
        myView!!.isSign = false
//        button!!.text = "停止截屏"
      } else {
        myView!!.isSign = true
//        button!!.text = "开始截屏"
        image2!!.setImageBitmap(null)
      }
      myView!!.postInvalidate()
    }
    image1!!.setOnTouchListener(this);
    this.addContentView(myView, ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT))
  }



  companion object {
    const val TAG: String = "CANVAS_EDITONE_ACTIVITY"
    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasEditoneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }

  /**
   * 根据绘制的区域生成图片
   *
   * @param activity
   * @return
   */
  private fun getBitmap(activity: Activity): Bitmap? {
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    bitmap = view.drawingCache
    val frame = Rect()
    activity.window.decorView.getWindowVisibleDisplayFrame(frame)
    val toHeight = frame.top
    //bitmap = Bitmap.createBitmap(bitmap, x, y+2*toHeight, width, height);
    // 108是上边界的距离（大约上边框栏的大小 这里根据自己情况调整）
    Log.i(TAG,width.toString())
    Log.i(TAG,height.toString())
    bitmap = Bitmap.createBitmap(bitmap!!, x+5, y+5 , width-5, height-5)
    //这个能获得上边界的画面
    //bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
    try {

      // 保存截图的而位置（需要sdcard写入权限）
      val fout = FileOutputStream("mnt/sdcard/testScreenShot.png")
      bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, fout)
    } catch (e: FileNotFoundException) {
      // TODO Auto-generated catch block
      e.printStackTrace()
    }
    view.isDrawingCacheEnabled = false
    return bitmap
  }

  override fun onTouch(v: View?, event: MotionEvent?): Boolean {
    if(event!!.getAction() == MotionEvent.ACTION_DOWN){
      x = 0;
      y = 0;
      width = 0;
      height = 0;
      x = event!!.getX().toInt();
      y = event!!.getY().toInt();

      Log.i(TAG, "onTouch: x = "+x +" y = "+y);
    }

    // 移动的时候进行绘制框
    if(event!!.getAction() == MotionEvent.ACTION_MOVE){
      m = event!!.getX().toInt();
      n = event!!.getY().toInt();
      myView!!.setSeat(x, y, m, n);
      myView!!.postInvalidate();

      Log.i(TAG, "onTouch: x = "+x +" y = "+y +" m = "+m +" n = "+n);
    }

    // 抬起的时候整理绘制的框 长宽
    if(event!!.getAction() == MotionEvent.ACTION_UP){


      if(event!!.getX()>x){
        width = (event!!.getX()-x).toInt();
      }else{
        width = (x-event!!.getX()).toInt();
        x = event.getX().toInt();
      }
      if(event!!.getY()>y){
        height = (event!!.getY()-y).toInt();
      }else{
        height = (y-event!!.getY()).toInt();
        y =  event.getY().toInt();
      }

      Log.i(TAG, "onTouch: x = "+x +" y = "+y +" m = "+m +" n = "+n);

      // 设置图片显示
      image2!!.setImageBitmap(getBitmap(this));
    }
    if(myView!!.isSign()){
      return false
    }else{
      return true
    }
  }


}


