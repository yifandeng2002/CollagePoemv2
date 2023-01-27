package com.collagepoem.app

import android.R.attr.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.collagepoem.app.modules.albumpage.ui.AlbumpageActivity.Companion.TAG
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private var bitmap //生成的位图
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
        Log.i(TAG, "width = $width,height = $height")

        // 点击按钮截图
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e(TAG,"lkq is a puppy")
                img.setImageBitmap(getBitmap(this@MainActivity, 0, 0, width, height))
            }
        })
        checkPermissions(this@MainActivity)
    }

    /**
     * 获取屏幕截图
     * @param activity
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    private fun getBitmap(activity: Activity, x: Int, y: Int, width: Int, height: Int): Bitmap? {
        val view: View = activity.window.decorView
        view.setDrawingCacheEnabled(true)
        view.buildDrawingCache()
        bitmap = view.getDrawingCache()

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

    /**
     * 申请权限
     * @param activity
     */
    fun checkPermissions(activity: Activity?) {
        Log.i(TAG, "checkPermissions: 检测权限")
        unGrantedPermissions = ArrayList()
        val MANDATORY_PERMISSIONS = arrayOf(
            "android.permission.CAMERA",
            "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"
        )
        Log.i(TAG, "checkPermissions: MANDATORY_PERMISSIONS :$MANDATORY_PERMISSIONS")
        for (permission in MANDATORY_PERMISSIONS) {
            Log.i(TAG, "checkPermissions: unGrantedPermissions")
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
            Log.i(TAG, "checkPermissions: 已经获得了所有权限")
        }
        Log.i(TAG, "checkPermissions: Is Over")
    }
}