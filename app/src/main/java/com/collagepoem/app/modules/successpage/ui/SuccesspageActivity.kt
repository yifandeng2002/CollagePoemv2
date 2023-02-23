package com.collagepoem.app.modules.successpage.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivitySuccesspageBinding
import com.collagepoem.app.modules.canvas.ui.Constant
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.successpage.`data`.viewmodel.SuccesspageVM
import com.jaeger.library.StatusBarUtil
import java.io.File
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SuccesspageActivity : BaseActivity<ActivitySuccesspageBinding>(R.layout.activity_successpage)
    {

  private val viewModel: SuccesspageVM by viewModels<SuccesspageVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 116

      //    将StatusBar设置为透明
      fun setStatusBarTranslucent() {
        StatusBarUtil.setTranslucentForImageViewInFragment(
          this,
          0, null
        )
        StatusBarUtil.setLightMode(this)
      }


      //   作品在成功、分享页面显示
      public var lat: FrameLayout?=null
      public var imageView:ImageView?=null

      @SuppressLint("MissingInflatedId")
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successpage)

        val sdpath: String = Environment.getExternalStorageDirectory().getAbsolutePath() // 获取sdcard的根路径
        Log.e(ConstraintLayoutStates.TAG,sdpath.toString())

        val filepath = Constant.curSCREEN_SHOT
        val file = File(filepath)
        imageView=findViewById(R.id.imagePoem)

        var bytes = ByteArray(file.length().toInt() + 1)
        Log.e(ConstraintLayoutStates.TAG,bytes.size.toString())
        for(byte:Byte in bytes)
        {
          Log.e(ConstraintLayoutStates.TAG,byte.toString())
        }
        lat=findViewById(R.id.frameImagePoem)


        if (file.exists()) {
          val bm: Bitmap = BitmapFactory.decodeFile(filepath)
          val mat=Matrix()
          mat.setScale(0.1f,0.1f)
          val bc: Bitmap = Bitmap.createBitmap(bm,0,0,bm.width,bm.height,mat,true)

          // 将图片显示到ImageView中
          imageView!!.setImageBitmap(bm)
        }

////跳转微信分享
//        val intent = Intent(this, SharetoWeChatActivity::class.java)
//        imageShare.setOnClickListener {
//          val intent = Intent(this, SharetoWeChatActivity::class.java)
//          intent.putExtra ("key", "value")
//          startActivity(intent)
//        }
      }


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.successpageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageClose.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
    binding.imageBackbtn.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "SUCCESSPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SuccesspageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
