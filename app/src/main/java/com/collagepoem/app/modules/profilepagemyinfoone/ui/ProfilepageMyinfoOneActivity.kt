package com.collagepoem.app.modules.profilepagemyinfoone.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMyinfoOneBinding
import com.collagepoem.app.modules.profilepagemyinfoone.`data`.model.Works2RowModel
import com.collagepoem.app.modules.profilepagemyinfoone.`data`.viewmodel.ProfilepageMyinfoOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMyinfoOneActivity :
    BaseActivity<ActivityProfilepageMyinfoOneBinding>(R.layout.activity_profilepage_myinfo_one) {
  private val viewModel: ProfilepageMyinfoOneVM by viewModels<ProfilepageMyinfoOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val worksAdapter = WorksAdapter(viewModel.worksList.value?:mutableListOf())
    binding.recyclerWorks.adapter = worksAdapter
    worksAdapter.setOnItemClickListener(
    object : WorksAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Works2RowModel) {
        onClickRecyclerWorks(view, position, item)
      }
    }
    )
    viewModel.worksList.observe(this) {
      worksAdapter.updateData(it)
    }
    binding.profilepageMyinfoOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerWorks(
    view: View,
    position: Int,
    item: Works2RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROFILEPAGE_MYINFO_ONE_ACTIVITY"

  }
}
