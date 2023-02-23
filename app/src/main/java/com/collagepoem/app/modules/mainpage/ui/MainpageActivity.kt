package com.collagepoem.app.modules.mainpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.appcomponents.di.MyApp
import com.collagepoem.app.databinding.ActivityMainpageBinding
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.communitypage.ui.CommunitypageActivity
import com.collagepoem.app.modules.mainpage.data.model.CardRowModel
import com.collagepoem.app.modules.mainpage.`data`.viewmodel.MainpageVM
import com.collagepoem.app.modules.profilepagemypage.ui.ProfilepageMypageActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class MainpageActivity : BaseActivity<ActivityMainpageBinding>(R.layout.activity_mainpage) {
  private val viewModel: MainpageVM by viewModels<MainpageVM>()

  private val REQUEST_CODE_CANVAS_POEM_ACTIVITY: Int = 594


  private val REQUEST_CODE_COMMUNITYPAGE_ACTIVITY: Int = 532


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY: Int = 674
  private val list=ArrayList<CardRowModel>()
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
    binding.mainpageVM = viewModel
    setStatusBarTranslucent()
    list.add(CardRowModel("2023-2-23","贰月贰拾叁","面朝大海\n春暖花开",
            "从明天起 做一个幸福的人\n" +
            "喂马 劈柴 周游世界\n" +
            "从明天起 关心粮食和蔬菜\n" +
            "我有一所房子 面朝大海 春暖花开\n" +
            "从明天起 和每一个亲人通信\n" +
            "告诉他们我的幸福\n那幸福的闪电告诉我的\n" +
            "我将告诉每一个人\n" +
            "给每一条河每一座山取一个温暖的名字\n" +
            "陌生人 我也为你祝福\n" +
            "愿你有一个灿烂的前程\n" +
            "愿你有情人终成眷属\n" +
            "愿你在尘世获的幸福\n" +
            "我也愿面朝大海 春暖花开",MyApp.getInstance().resources.getDrawable(R.drawable.img_poemcard_648x356)))
    list.add(CardRowModel("2023-2-23","贰月贰拾叁","思念前生",
            "庄子在水中洗手\n" +
            "洗完了手手掌上一片寂静\n" +
            "庄子在水中洗身\n" +
            "身子是一匹布\n" +
            "那布上粘满了\n" +
            "水面上漂来漂去的声音\n" +
            "庄子想混入\n" +
            "凝望月亮的野兽\n" +
            "骨头一寸一寸\n" +
            "在肚脐上下\n" +
            "象树枝一样长着\n" +
            "也许庄子就是我\n",MyApp.getInstance().resources.getDrawable(R.drawable.img_poemcard_648x356)))
    val layoutManager = LinearLayoutManager( this,LinearLayoutManager.HORIZONTAL,false)
    val recyclerView = findViewById<RecyclerView> (R.id.recyclerView)
    recyclerView.layoutManager = layoutManager
    var recyclerCard=CardAdapter(list)
    Log.e("pic2",list[0].imageCardView.toString())
    recyclerView.adapter=recyclerCard
  }

  override fun setUpClicks(): Unit {
    binding.recyclerView.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageEye.setOnClickListener {
      val destIntent = CommunitypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right_2 ,R.anim.right_to_left_2 )
    }
    binding.imageImageprofilepi.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageImageAddNew2.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imagePoemcardOne.setOnClickListener {
      val destIntent = CanvasPoemActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CANVAS_POEM_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
    binding.imageUser.setOnClickListener {
      val destIntent = ProfilepageMypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right_2 ,R.anim.right_to_left_2 )
    }
  }

  companion object {
    const val TAG: String = "MAINPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MainpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
