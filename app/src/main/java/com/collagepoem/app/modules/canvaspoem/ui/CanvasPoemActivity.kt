package com.collagepoem.app.modules.canvaspoem.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.ActionBar
import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
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
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity

import com.collagepoem.app.databinding.ActivityCanvasPoem2Binding
import com.collagepoem.app.modules.canvas.ui.CanvasActivity
import com.collagepoem.app.modules.canvas.ui.Constant
import com.collagepoem.app.modules.canvaseditone.ui.CanvasEditoneActivity
import com.collagepoem.app.modules.canvaspoem.data.viewmodel.CanvasPoemVM
import com.collagepoem.app.modules.canvaspoem.data.viewmodel.MyView
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.landinpage.views.ImagePickerFragmentDialog
import com.collagepoem.app.modules.loadingworkpage.ui.LoadingworkpageActivity
import com.collagepoem.app.modules.mainpage.PoemCut
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.successpage.ui.SuccesspageActivity
import com.jaeger.library.StatusBarUtil
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.*


class CanvasPoemActivity : AppCompatActivity(),View.OnTouchListener {
  private val viewModel: CanvasPoemVM by viewModels<CanvasPoemVM>()

  private val REQUEST_CODE_CANVAS_ACTIVITY: Int = 653
  private val REQUEST_CODE_CANVAS_POEM_ACTIVITY: Int = 454
  private val REQUEST_CODE_LOADINGWORKPAGE_ACTIVITY: Int = 989
  private val REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY: Int = 809
  private val REQUEST_CODE_SUCCESSPAGE_ACTIVITY: Int = 100

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 831


  private val REQUEST_CODE_CANVAS_EDITONE_ACTIVITY: Int = 425
  private lateinit var dragView: View
  private lateinit var dragView2: View
  private lateinit var llTrash: View
  private lateinit var llTop: View
  private lateinit var llBottom: View
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
  private var downX = 0f
  private  var downY: Float = 0f
  private var orgX: Int = 0
  private  var orgY: Int = 0
  private var tempX = 0f
  private  var tempY: Float = 0f
  private var back: View? = null
  private var cav: Canvas? = null
  //    将StatusBar设置为透明
  fun setStatusBarTranslucent() {
    StatusBarUtil.setTranslucentForImageViewInFragment(
      this,
      0, null
    )
    StatusBarUtil.setLightMode(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_canvas_poem2)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    //binding.canvasPoemVM = viewModel
    setStatusBarTranslucent()
    //setContentView(R.layout.activity_canvas_poem)
    dragView = findViewById(R.id.imagePaperOne)
    dragView2 = findViewById(R.id.imagePaperTwo)
    llTop = findViewById(R.id.canva)
    myView = MyView(this)
    llBottom = findViewById(R.id.sidebar)
    llTrash = findViewById(R.id.imageTrashbtn)
    llTop.setOnDragListener (dragListener)
    llBottom.setOnDragListener (dragListener)
    llTrash.setOnDragListener(dragListener)
    var imageSwitchbtn = findViewById<ImageView>(R.id.imageSwitchbtn)
    var imageFilebtn = findViewById<ImageView>(R.id.imageFilebtn)
    var imageCamerabtn = findViewById<ImageView>(R.id.imageCamerabtn)
    var imageBackbtn = findViewById<ImageView>(R.id.imageBackbtn)
    var imageScissorsbtn = findViewById<ImageView>(R.id.imageScissorsbtn)
    var imageUp2 = findViewById<ImageView>(R.id.imageUp2)
    var imageUp3 = findViewById<ImageView>(R.id.imageUp3)
    image1 = findViewById(R.id.framePoem)
    image2 = findViewById<View>(R.id.imagePaperTwo) as ImageView
    myView = MyView(this)
    button = findViewById(R.id.imageScissorsbtn)
    dragView = findViewById(R.id.imagePaperOne)
    dragView2 = findViewById(R.id.imagePaperTwo)
    var txtOrder = findViewById<TextView>(R.id.txtOrder)
    var txtDescription = findViewById<TextView>(R.id.txtDescription)
    var txtTimeCN = findViewById<TextView>(R.id.txtTimeCN)
    var txtTimeEN = findViewById<TextView>(R.id.txtTimeEN)
    txtOrder.setText(PoemCut.txtName)
    txtDescription.setText(PoemCut.txtthis)
    txtTimeCN.setText(PoemCut.txtTimecn)
    txtTimeEN.setText(PoemCut.txtTimeen)


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
    imageSwitchbtn.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
    }
    imageFilebtn.setOnClickListener {
      XPopup.Builder(this@CanvasPoemActivity)
        .isDarkTheme(false)
        .hasShadowBg(true)
        .borderRadius(100f)
        .maxHeight(2000)
        .hasBlurBg(true)
        .moveUpToKeyboard(false)
        .isCoverSoftInput(true)
        .asBottomList(
          "纸条夹",
          arrayOf("条目1", "条目2","条目3","条目4"),
          intArrayOf(R.drawable.img_note3,R.drawable.img_note2,R.drawable.img_note2,R.drawable.img_note4),
          -1,
          OnSelectListener { position, text -> Toast.makeText(this@CanvasPoemActivity, "click $text", Toast.LENGTH_SHORT).show() },
          R.layout.noterecyclerview,
          R.layout.noteitemforcanvas
        ) .show()
    }

//    binding.imageUp2.setOnClickListener {
//      val destIntent = CanvasActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_SUCCESSPAGE_ACTIVITY)
//      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
//    }


    imageCamerabtn.setOnClickListener {
      ImagePickerFragmentDialog().show(supportFragmentManager)
      { path ->//TODO HANDLE DATA
      }

    }
    imageBackbtn.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
    }
    imageScissorsbtn.setOnClickListener {
      Log.e("zhuzhu1",myView!!.isSign.toString())
      if (!myView!!.isSign) {
        myView!!.setSeat(0, 0, 0, 0)
        myView!!.isSign = true
        Toast.makeText(this,"开始截屏",Toast.LENGTH_SHORT)
        llTrash.isVisible = false
        image2!!.setImageBitmap(null)
        imageBackbtn.isVisible = false
        imageFilebtn.isVisible = false
        imageCamerabtn.isVisible = false
      } else {
        myView!!.isSign = false
        Toast.makeText(this,"截屏结束",Toast.LENGTH_SHORT)
        llTrash.isVisible = true
        imageBackbtn.isVisible = true
        imageFilebtn.isVisible = true
        imageCamerabtn.isVisible = true
      }
      myView!!.postInvalidate()
    }
//    binding.imageWihtepic.setOnClickListener {
//      val destIntent = CanvasActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
//      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
//    }
    imageUp2.setOnClickListener {
      var view = findViewById<View>(R.id.frameStackfinish)
      var set = AnimatorSet()
      // 向下动画(暂时不用)
      var downAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, 30F, 0F)
      // 向上动画
      var upAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, -2500F)
      //upAnim.repeatMode = ValueAnimator.REVERSE
      //upAnim.repeatCount = Animation.INFINITE
      set.duration = 1000
      // 顺序执行动画
      //set.playSequentially(upAnim)
      set.playSequentially(upAnim)
      set.start()
    }
    imageUp3.setOnClickListener {
      var view = findViewById<View>(R.id.frameStackfinish)
      var set = AnimatorSet()
      // 向下动画(暂时不用)
      var downAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, 30F, 0F)
      // 向上动画
      var upAnim = ObjectAnimator.ofFloat(view, "translationY", -2500F, 0F)
      //upAnim.repeatMode = ValueAnimator.REVERSE
      //upAnim.repeatCount = Animation.INFINITE
      set.duration = 1000
      // 顺序执行动画
      //set.playSequentially(upAnim)
      set.playSequentially(upAnim)
      set.start()
    }

    dragView.setOnLongClickListener{
      val clipText = "This is our ClipData text"
      val item = ClipData. Item (clipText)
      val mimeTypes = arrayOf (ClipDescription.MIMETYPE_TEXT_PLAIN)
      val data = ClipData (clipText, mimeTypes, item)
      val dragshadowBuilder = View.DragShadowBuilder(it)

      it.startDragAndDrop (data, dragshadowBuilder, it, 0)
      it.visibility = View. INVISIBLE
      true

    }

    dragView2.setOnLongClickListener{
            val clipText = "This is our ClipData text"
            val item = ClipData. Item (clipText)
            val mimeTypes = arrayOf (ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData (clipText, mimeTypes, item)
            val dragshadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop (data, dragshadowBuilder, it, 0)
            it.visibility = View. INVISIBLE
            true

    }


    image1!!.setOnTouchListener(this);
    this.addContentView(myView, ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT))
  }


  val dragListener = View.OnDragListener { view, event ->
    when (event.action) {
      DragEvent.ACTION_DRAG_STARTED -> {
        event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)

      }
      DragEvent.ACTION_DRAG_ENTERED -> {
        view.invalidate()
        true
      }
      DragEvent.ACTION_DRAG_LOCATION -> true
      DragEvent.ACTION_DRAG_EXITED -> {
        view.invalidate()
        true
      }
      DragEvent.ACTION_DROP -> {
        val item = event.clipData.getItemAt(0)
        val dragData = item.text

        view.invalidate()

        val v = event.localState as View
        val owner = v.parent as ViewGroup
        owner.removeView(v)
        if(view!=llTrash){
        val destination = view as LinearLayout
        destination.addView(v)
        v.visibility = View.VISIBLE
        }
        else{
          Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show()
        }
        true
      }
      DragEvent.ACTION_DRAG_ENDED -> {
        view.invalidate()
        true
      }
      else -> false
    }
  }

  val canvasdragListener = View.OnDragListener { view, event ->
    when (event.action) {
      DragEvent.ACTION_DRAG_STARTED -> {
        event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
      }
      DragEvent.ACTION_DRAG_ENTERED -> {
        view.invalidate()
        true
      }
      DragEvent.ACTION_DRAG_LOCATION -> true
      DragEvent.ACTION_DRAG_EXITED -> {
        view.invalidate()
        true
      }
      DragEvent.ACTION_DROP -> {
        val item = event.clipData.getItemAt(0)
        val dragData = item.text
        Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()

        view.invalidate()

        val v = event.localState as View
        val owner = v.parent as ViewGroup
        owner.removeView(v)
        val destination = view as LinearLayout
        destination.addView(v)
        v.visibility = View.VISIBLE
        true

      }
      DragEvent.ACTION_DRAG_ENDED -> {
        view.invalidate()
        true
      }
      else -> false
    }
  }



//  override fun setUpClicks(): Unit {
//    binding.imageSwitchbtn.setOnClickListener {
//      val destIntent = CanvasPoemActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
//      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
//    }
//    binding.imageFilebtn.setOnClickListener {
//      val destIntent = FloatwindowMycutsVtwoActivity.getIntent(this, null)
//      destIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//      startActivityForResult(destIntent, REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY)
//      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
//    }
//
////    binding.imageUp2.setOnClickListener {
////      val destIntent = CanvasActivity.getIntent(this, null)
////      startActivityForResult(destIntent, REQUEST_CODE_SUCCESSPAGE_ACTIVITY)
////      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
////    }
//
//
//    binding.imageCamerabtn.setOnClickListener {
//      ImagePickerFragmentDialog().show(supportFragmentManager)
//      { path ->//TODO HANDLE DATA
//      }
//
//    }
//    binding.imageBackbtn.setOnClickListener {
//      val destIntent = MainpageActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
//      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
//    }
//    binding.imageScissorsbtn.setOnClickListener {
//      val destIntent = CanvasEditoneActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_EDITONE_ACTIVITY)
//      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
//    }
////    binding.imageWihtepic.setOnClickListener {
////      val destIntent = CanvasActivity.getIntent(this, null)
////      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
////      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
////    }
//    binding.imageUp2.setOnClickListener {
//      var view = findViewById<View>(R.id.frameStackfinish)
//      var set = AnimatorSet()
//      // 向下动画(暂时不用)
//      var downAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, 30F, 0F)
//      // 向上动画
//      var upAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, -2500F)
//      //upAnim.repeatMode = ValueAnimator.REVERSE
//      //upAnim.repeatCount = Animation.INFINITE
//      set.duration = 1000
//      // 顺序执行动画
//      //set.playSequentially(upAnim)
//        set.playSequentially(upAnim)
//      set.start()
//    }
//  }

  companion object {
    const val TAG: String = "CANVAS_POEM_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CanvasPoemActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }










  //截屏作品，并保存至图库

  private var mSave: Button? = null
  private var mSaveArea: ImageView? = null
  private var mCutTop = 0
  private var mCutLeft = 0
  private var mPicGet: ImageView? = null
  private var mFL: FrameLayout? = null
  private var path: String? = null
  private var file_path: String? = null
  private var mPicGetHeight = 0
  private var mPicGetWidth = 0
  private var saveBitmap: Bitmap? = null
  private var mTotal: FrameLayout? = null
  private val mSavePositions = IntArray(2)
  private var formatter: SimpleDateFormat? =null
  private var successHandler: SuccessHandler? = null
  private var  curDate: Date?=null
  private var initHandler: InitHandler? = null
  private var str: String? =null
  private var cun= Constant()


//  @RequiresApi(Build.VERSION_CODES.R)
//  protected override fun onCreate(savedInstanceState: Bundle?) {
//    requestWindowFeature(Window.FEATURE_NO_TITLE)
//    super.onCreate(savedInstanceState)
//
//
//    //setContentView(R.layout.activity_canvas)
//    initView()
//    mSave?.setOnClickListener{
//      mSave!!.text = "存储中……"
//      mSave!!.isEnabled = false
//
//      var card1 =findViewById<ImageView>(R.id.imageFilebtn)
//      var card2 =findViewById<ImageView>(R.id.imageCamerabtn)
//      var card3 =findViewById<ImageView>(R.id.imageScissorsbtn)
//      var card4 =findViewById<ImageView>(R.id.imageTrashbtn)
//      card1.visibility=View.INVISIBLE
//      card2.visibility=View.INVISIBLE
//      card3.visibility=View.INVISIBLE
//      card4.visibility=View.INVISIBLE
//
//      Thread { screenshot() }.start()
//    }
//  }


  //override fun requestWindowFeature(featureNoTitle: Int): Boolean {}
  //override fun setContentView(activity_main: Int) {}
  private fun initView() {
    mSave = findViewById(R.id.button)
//        Log.e(ControlsProviderService.TAG, mSave!!.id.toString())
    mSaveArea = findViewById(R.id.imageCanvas) as ImageView?
    mPicGet = findViewById(R.id.iv_pic_get) as ImageView?
    mFL = findViewById(R.id.fl_pic) as FrameLayout?
    mTotal = findViewById(R.id.frameStackbackground) as FrameLayout?
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

        val file = File(imageDir, imageName)
        Log.i("TAG",file.toString())
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
      Toast.makeText(this@CanvasPoemActivity, "存储失败", Toast.LENGTH_SHORT).show()
      mSave!!.isEnabled = true
      mSave!!.text = "完成"
    }
  }

  /**
   * 截图成功后显示动画
   */
  private fun showSuccess() {
    Toast.makeText(this@CanvasPoemActivity, "保存成功", Toast.LENGTH_SHORT).show()

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
    Toast.makeText(this@CanvasPoemActivity, "保存成功", Toast.LENGTH_SHORT).show()
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
    Log.i(CanvasEditoneActivity.TAG,width.toString())
    Log.i(CanvasEditoneActivity.TAG,height.toString())
    bitmap = Bitmap.createBitmap(bitmap!!, x, y , width, height)
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
      x = event!!.getRawX().toInt();
      y = event!!.getRawY().toInt();
      //pat.moveTo(x.toFloat(),y.toFloat())
      Log.i(CanvasEditoneActivity.TAG, "onTouch: x = "+x +" y = "+y);
    }

    // 移动的时候进行绘制框
    if(event!!.getAction() == MotionEvent.ACTION_MOVE){
      m = event!!.getRawX().toInt();
      n = event!!.getRawY().toInt();
      //pat.quadTo(orgX.toFloat(), orgY.toFloat(), m.toFloat(), n.toFloat())
      orgX =m;
      orgY = n;
      myView!!.setSeat(x, y, m, n);
      myView!!.postInvalidate();
//      myView!!.invalidate()
      myView!!.forceLayout()
      Log.i(CanvasEditoneActivity.TAG, "onTouch: x = "+x +" y = "+y +" m = "+m +" n = "+n);
    }

    // 抬起的时候整理绘制的框 长宽
    if(event!!.getAction() == MotionEvent.ACTION_UP){


      if(event!!.getRawX()>x){
        width = (event!!.getRawX()-x).toInt();
      }else{
        width = (x-event!!.getRawX()).toInt();
        x = event.getRawX().toInt();
      }
      if(event!!.getRawY()>y){
        height = (event!!.getRawY()-y).toInt();
      }else{
        height = (y-event!!.getRawY()).toInt();
        y =  event.getRawY().toInt();
      }

      Log.i(CanvasEditoneActivity.TAG, "onTouch: x = "+x +" y = "+y +" m = "+m +" n = "+n);

      // 设置图片显示

        image2!!.setImageBitmap(getBitmap(this));



    }
    if(!myView!!.isSign()){
      return false
    }else{
      return true
    }

  }

}



