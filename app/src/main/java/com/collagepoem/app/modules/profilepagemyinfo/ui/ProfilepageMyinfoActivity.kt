package com.collagepoem.app.modules.profilepagemyinfo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMyinfoBinding
import com.collagepoem.app.modules.communitypageinfo.ui.CommunitypageInfoActivity
import com.collagepoem.app.modules.communitypageinfoone.ui.CommunitypageInfoOneActivity
import com.collagepoem.app.modules.profilepagemyinfo.`data`.model.WorksRowModel
import com.collagepoem.app.modules.profilepagemyinfo.`data`.viewmodel.ProfilepageMyinfoVM
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMyinfoActivity :
    BaseActivity<ActivityProfilepageMyinfoBinding>(R.layout.activity_profilepage_myinfo) {
  private val viewModel: ProfilepageMyinfoVM by viewModels<ProfilepageMyinfoVM>()

  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY: Int = 687

  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY: Int = 141

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
      override fun onItemClick(view:View, position:Int, item : WorksRowModel) {
        onClickRecyclerWorks(view, position, item)
      }
    }
    )
    viewModel.worksList.observe(this) {
      worksAdapter.updateData(it)
    }
    binding.profilepageMyinfoVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.imageBack.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerWorks(
    view: View,
    position: Int,
    item: WorksRowModel
  ): Unit {
    when(view.id) {
      R.id.linearWorkcard ->  {
        onClickRecyclerWorksLinearWorkcard(view, position, item)
      }
    }
  }

  fun onClickRecyclerWorksLinearWorkcard(
    view: View,
    position: Int,
    item: WorksRowModel
  ) {
    /** TODO As per your logic, Add constant type for item click.*/
    when(0) {
      0 ->  {
        val destIntent = CommunitypageInfoActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY)
        this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
      }
      1 ->  {
        val destIntent = CommunitypageInfoOneActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ONE_ACTIVITY)
        this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
      }
    }
  }

  companion object {
    const val TAG: String = "PROFILEPAGE_MYINFO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ProfilepageMyinfoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
