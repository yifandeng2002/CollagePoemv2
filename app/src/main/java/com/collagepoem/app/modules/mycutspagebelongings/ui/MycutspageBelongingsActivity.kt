package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.dao.UserDao
import com.collagepoem.app.modules.driftcutspagetwo.ui.DriftcutspagetwoActivity
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.viewmodel.MycutspageBelongingsVM
import kotlin.Int
import kotlin.String
import kotlin.Unit
import com.collagepoem.app.databinding.ActivityMycutspageBelongingsBinding
import com.collagepoem.app.entity.Note
import com.collagepoem.app.entity.Note1
//import com.collagepoem.app.modules.mycutspagebelongingsone.ui.MycutspageBelongingsOneActivity
import com.collagepoem.app.modules.profilepagemypage.ui.ProfilepageMypageActivity
import com.collagepoem.app.noteinfo.NoteInfo
import com.jaeger.library.StatusBarUtil
import com.lxj.xpopup.XPopup
import java.security.AccessController.getContext
import java.sql.Date


class MycutspageBelongingsActivity :
  AppCompatActivity() {
  private val viewModel: MycutspageBelongingsVM by viewModels<MycutspageBelongingsVM>()

  private val REQUEST_CODE_MYCUTSPAGE_BELONGINGS_ONE_ACTIVITY: Int = 837


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_CONTAINER_ACTIVITY: Int = 787


  private val REQUEST_CODE_DRIFTCUTSPAGETWO_ACTIVITY: Int = 333

  private var list = ArrayList<SticksRowModel>()

  public var name = ArrayList<String>()
  public var note = Note1
  fun setStatusBarTranslucent() {
    StatusBarUtil.setTranslucentForImageViewInFragment(
      this,
      0, null
    )
    StatusBarUtil.setLightMode(this)
  }

  var handler = object : Handler(Looper.getMainLooper()){
    override fun handleMessage(msg: Message) {
      super.handleMessage(msg)
      if(msg.obj !=null)
      {
        note = msg.obj as Note1

        if(note.title.size!=0)
        {
          //updateData(note)
          var newData=ArrayList<SticksRowModel>()
          for( i in 0 until note.author.size)
          {
            //Log.e("g",SticksRowModel(note.title[i], note.collecttime[i] as Date?,null).toString())
            newData.add(SticksRowModel(note.title[i], note.collecttime[i] as Date?))
          }
          if (newData != null) {
            list = newData
          }
          val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
          val adapter = SticksAdapter(list)
          recyclerView.adapter = adapter
        }
      }
    }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_mycutspage_belongings)
    setStatusBarTranslucent()
    initlist()
    val layoutManager = LinearLayoutManager(this)
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    val imageBack = findViewById<ImageView>(R.id.imageBack)
    recyclerView.layoutManager = layoutManager



//    viewModel.sticksList.observe(this) {
//      //sticksAdapter.updateData(it)
//    }
    imageBack.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_CONTAINER_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in)
    }
  }
  private fun initlist(){
    object : Thread() {
      override fun run() {
        val Shownote = NoteInfo()
        val msg = Message()
        val note2 = Shownote.showNote(note)
        if (note2 != null) {
          if(note2.title.size!= 0){
            msg.obj= note2
            handler.sendMessage(msg)
          }
        }


      }
    }.start()
  }

  private fun updateData(mag: Note1) {
    var newData:ArrayList<SticksRowModel>? =null
    for( i in 0 until mag.author.size)
    {
      newData!!.add(SticksRowModel(mag.title.get(i), mag.collecttime.get(i) as Date?,null))

    }
    if (newData != null) {
      list = newData
    }

  }


  companion object {
    const val TAG: String = "MYCUTSPAGE_BELONGINGS_ACTIVITY"
    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MycutspageBelongingsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
