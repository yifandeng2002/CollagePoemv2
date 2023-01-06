package com.collagepoem.app.modules.profilepagemypage.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityProfilepageMypageBinding
import com.collagepoem.app.modules.profilepagemypage.`data`.model.Works1RowModel
import com.collagepoem.app.modules.profilepagemypage.`data`.viewmodel.ProfilepageMypageVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ProfilepageMypageActivity :
    BaseActivity<ActivityProfilepageMypageBinding>(R.layout.activity_profilepage_mypage) {
  private val viewModel: ProfilepageMypageVM by viewModels<ProfilepageMypageVM>()

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
  }

  override fun setUpClicks(): Unit {
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

  }
}
