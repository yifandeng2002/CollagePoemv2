package com.collagepoem.app.modules.driftcutspageone.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityDriftcutspageoneBinding
import com.collagepoem.app.modules.communitypageinfo.ui.CommunitypageInfoActivity
import com.collagepoem.app.modules.driftcutspageone.`data`.model.Works3RowModel
import com.collagepoem.app.modules.driftcutspageone.`data`.viewmodel.DriftcutspageoneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class DriftcutspageoneActivity :
    BaseActivity<ActivityDriftcutspageoneBinding>(R.layout.activity_driftcutspageone) {
  private val viewModel: DriftcutspageoneVM by viewModels<DriftcutspageoneVM>()

  private val REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY: Int = 280

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val worksAdapter = WorksAdapter(viewModel.worksList.value?:mutableListOf())
    binding.recyclerWorks.adapter = worksAdapter
    worksAdapter.setOnItemClickListener(
    object : WorksAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Works3RowModel) {
        onClickRecyclerWorks(view, position, item)
      }
    }
    )
    viewModel.worksList.observe(this) {
      worksAdapter.updateData(it)
    }
    binding.driftcutspageoneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageBack.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerWorks(
    view: View,
    position: Int,
    item: Works3RowModel
  ): Unit {
    when(view.id) {
      R.id.linearColumnworktitle ->  {
        val destIntent = CommunitypageInfoActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_COMMUNITYPAGE_INFO_ACTIVITY)
        this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
      }
    }
  }

  companion object {
    const val TAG: String = "DRIFTCUTSPAGEONE_ACTIVITY"

  }
}
