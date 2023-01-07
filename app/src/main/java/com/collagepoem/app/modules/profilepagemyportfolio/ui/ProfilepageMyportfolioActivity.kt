package com.collagepoem.app.modules.profilepagemyportfolio.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMyportfolioBinding
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.model.WorkRowModel
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.viewmodel.ProfilepageMyportfolioVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMyportfolioActivity :
    BaseActivity<ActivityProfilepageMyportfolioBinding>(R.layout.activity_profilepage_myportfolio) {
  private val viewModel: ProfilepageMyportfolioVM by viewModels<ProfilepageMyportfolioVM>()

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
