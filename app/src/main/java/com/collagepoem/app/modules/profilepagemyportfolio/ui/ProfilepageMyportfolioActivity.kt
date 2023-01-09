package com.collagepoem.app.modules.profilepagemyportfolio.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMyportfolioBinding
import com.collagepoem.app.modules.communitypage.ui.CommunitypageActivity
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.model.WorkRowModel
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.viewmodel.ProfilepageMyportfolioVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMyportfolioActivity :
    BaseActivity<ActivityProfilepageMyportfolioBinding>(R.layout.activity_profilepage_myportfolio) {
  private val viewModel: ProfilepageMyportfolioVM by viewModels<ProfilepageMyportfolioVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 951

  private val REQUEST_CODE_COMMUNITYPAGE_ACTIVITY: Int = 732

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val workAdapter = WorkAdapter(viewModel.workList.value?:mutableListOf())
    binding.recyclerWork.adapter = workAdapter
    workAdapter.setOnItemClickListener(
    object : WorkAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : WorkRowModel) {
        onClickRecyclerWork(view, position, item)
      }
    }
    )
    viewModel.workList.observe(this) {
      workAdapter.updateData(it)
    }
    binding.profilepageMyportfolioVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageHome.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right ,R.anim.left_to_right )
    }
    binding.viewBackground.setOnClickListener {
      finish()
    }
    binding.imageEye.setOnClickListener {
      val destIntent = CommunitypageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.left_to_right ,R.anim.left_to_right )
    }
  }

  fun onClickRecyclerWork(
    view: View,
    position: Int,
    item: WorkRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROFILEPAGE_MYPORTFOLIO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ProfilepageMyportfolioActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
