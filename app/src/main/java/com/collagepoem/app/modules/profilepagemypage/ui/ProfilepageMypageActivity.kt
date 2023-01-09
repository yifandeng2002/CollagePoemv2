package com.collagepoem.app.modules.profilepagemypage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMypageBinding
import com.collagepoem.app.modules.communitypage.ui.CommunitypageActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.mycutspagebelongings.ui.MycutspageBelongingsActivity
import com.collagepoem.app.modules.profilepagemypage.`data`.model.Works1RowModel
import com.collagepoem.app.modules.profilepagemypage.`data`.viewmodel.ProfilepageMypageVM
import com.collagepoem.app.modules.profilepagemyportfolio.ui.ProfilepageMyportfolioActivity
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMypageActivity :
    BaseActivity<ActivityProfilepageMypageBinding>(R.layout.activity_profilepage_mypage) {
  private val viewModel: ProfilepageMypageVM by viewModels<ProfilepageMypageVM>()

  private val REQUEST_CODE_PROFILEPAGE_MYPORTFOLIO_ACTIVITY: Int = 301

  private val REQUEST_CODE_COMMUNITYPAGE_ACTIVITY: Int = 453

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 493

  private val REQUEST_CODE_MYCUTSPAGE_BELONGINGS_ACTIVITY: Int = 604

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
    val worksAdapter = WorksAdapter(viewModel.worksList.value?:mutableListOf())
    binding.recyclerWorks.adapter = worksAdapter
    worksAdapter.setOnItemClickListener(
    object : WorksAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Works1RowModel) {
        onClickRecyclerWorks(view, position, item)
      }
    }
    )
    viewModel.worksList.observe(this) {
      worksAdapter.updateData(it)
    }
    binding.profilepageMypageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.linearWorks.setOnClickListener {
      val destIntent = ProfilepageMyportfolioActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPORTFOLIO_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
    binding.imageEye.setOnClickListener {
      val destIntent = CommunitypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right ,R.anim.right_to_left )
    }
    binding.imageHome.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right ,R.anim.right_to_left )
    }
    binding.linearFlips.setOnClickListener {
      val destIntent = MycutspageBelongingsActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MYCUTSPAGE_BELONGINGS_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
  }

  fun onClickRecyclerWorks(
    view: View,
    position: Int,
    item: Works1RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROFILEPAGE_MYPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ProfilepageMypageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
