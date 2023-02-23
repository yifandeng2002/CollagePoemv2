package com.collagepoem.app.modules.canvaspoem.ui

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.DragEvent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity

import com.collagepoem.app.databinding.ActivityCanvasPoem2Binding
import com.collagepoem.app.modules.canvaseditone.ui.CanvasEditoneActivity
import com.collagepoem.app.modules.canvaspoem.data.viewmodel.CanvasPoemVM
import com.collagepoem.app.modules.floatwindowmycutsvtwo.ui.FloatwindowMycutsVtwoActivity
import com.collagepoem.app.modules.landinpage.views.ImagePickerFragmentDialog
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener


class CanvasPoemActivity : BaseActivity<ActivityCanvasPoem2Binding>(R.layout.activity_canvas_poem2) {
  private val viewModel: CanvasPoemVM by viewModels<CanvasPoemVM>()

  private val REQUEST_CODE_CANVAS_ACTIVITY: Int = 653


  private val REQUEST_CODE_FLOATWINDOW_MYCUTS_VTWO_ACTIVITY: Int = 809


  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 831


  private val REQUEST_CODE_CANVAS_EDITONE_ACTIVITY: Int = 425
  private lateinit var dragView: View
  private lateinit var dragView2: View
  private lateinit var llTrash: View
  private lateinit var llTop: View
  private lateinit var llBottom: View

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
    //setContentView(R.layout.activity_canvas_poem)
    dragView = findViewById(R.id.imagePaperOne)
    dragView2 = findViewById(R.id.imagePaperTwo)
    llTop = findViewById(R.id.canva)
    llBottom = findViewById(R.id.sidebar)
    llTrash = findViewById(R.id.imageTrashbtn)
    llTop.setOnDragListener (dragListener)
    llBottom.setOnDragListener (dragListener)
    llTrash.setOnDragListener(dragListener)


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



  override fun setUpClicks(): Unit {
    binding.imageSwitchbtn.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
    }
    binding.imageFilebtn.setOnClickListener {
      XPopup.Builder(this@CanvasPoemActivity)
        .isDarkTheme(false)
        .hasShadowBg(true)
        .borderRadius(100f)
        .maxHeight(2000)
        .hasBlurBg(true)
        .moveUpToKeyboard(false)
        .isCoverSoftInput(true) //
        .asBottomList(
          "纸条夹",
          arrayOf("条目1", "条目2","条目3"),
          intArrayOf(R.drawable.img_note3,R.drawable.img_note1,R.drawable.img_note2),
          -1,
          OnSelectListener { position, text -> Toast.makeText(this@CanvasPoemActivity, "click $text", Toast.LENGTH_SHORT).show() },
          R.layout.noterecyclerview,
          R.layout.noteitemforcanvas
        ) .show()
    }
    binding.imageCamerabtn.setOnClickListener {
      ImagePickerFragmentDialog().show(supportFragmentManager)
      { path ->//TODO HANDLE DATA
      }

    }
    binding.imageBackbtn.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
    }
    binding.imageScissorsbtn.setOnClickListener {
      val destIntent = CanvasEditoneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_EDITONE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
//    binding.imageWihtepic.setOnClickListener {
//      val destIntent = CanvasActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_ACTIVITY)
//      this.overridePendingTransition(R.anim.slide_up_2 ,R.anim.slide_down_2 )
//    }
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



