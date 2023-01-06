package com.collagepoem.app.modules.profilepagemyinfo.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMyinfoBinding
import com.collagepoem.app.modules.profilepagemyinfo.`data`.model.WorksRowModel
import com.collagepoem.app.modules.profilepagemyinfo.`data`.viewmodel.ProfilepageMyinfoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMyinfoActivity :
    BaseActivity<ActivityProfilepageMyinfoBinding>(R.layout.activity_profilepage_myinfo) {
  private val viewModel: ProfilepageMyinfoVM by viewModels<ProfilepageMyinfoVM>()

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
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerWorks(
    view: View,
    position: Int,
    item: WorksRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROFILEPAGE_MYINFO_ACTIVITY"

  }
}
