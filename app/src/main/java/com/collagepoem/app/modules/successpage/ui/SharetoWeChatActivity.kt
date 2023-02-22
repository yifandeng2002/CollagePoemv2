package com.collagepoem.app.modules.successpage.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.collagepoem.app.R
import com.collagepoem.app.modules.canvas.ui.Constant
import java.io.File

class SharetoWeChatActivity : AppCompatActivity() {

    public var lat: FrameLayout?=null
    public var imageView: ImageView?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shareto_we_chat)

        val sdpath: String = Environment.getExternalStorageDirectory().getAbsolutePath() // 获取sdcard的根路径
        Log.e(ConstraintLayoutStates.TAG,sdpath.toString())

        val filepath = Constant.curSCREEN_SHOT
        val file = File(filepath)
        imageView=findViewById(R.id.imageWorkS)

        var bytes = ByteArray(file.length().toInt() + 1)
        Log.e(ConstraintLayoutStates.TAG,bytes.size.toString())
        for(byte:Byte in bytes)
        {
            Log.e(ConstraintLayoutStates.TAG,byte.toString())
        }
        lat=findViewById(R.id.frameShare)

        //Log.e(ConstraintLayoutStates.TAG,file.exists().toString())
        //Log.e(ConstraintLayoutStates.TAG,Constant.curSCREEN_SHOT)

        if (file.exists()) {
            val bm: Bitmap = BitmapFactory.decodeFile(filepath)
            val mat= Matrix()
            mat.setScale(0.1f,0.1f)
            val bc: Bitmap = Bitmap.createBitmap(bm,0,0,bm.width,bm.height,mat,true)

            // 将图片显示到ImageView中
            imageView!!.setImageBitmap(bm)
        }
    }

    companion object {
        const val TAG: String = "SHARETOWECHAT_ACTIVITY"


        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, SharetoWeChatActivity::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}