package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityMycutspageBelongingsBinding
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.viewmodel.MycutspageBelongingsVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class MycutspageBelongingsActivity :
    BaseActivity<ActivityMycutspageBelongingsBinding>(R.layout.activity_mycutspage_belongings) {
  private val viewModel: MycutspageBelongingsVM by viewModels<MycutspageBelongingsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sticksAdapter = SticksAdapter(viewModel.sticksList.value?:mutableListOf())
    binding.recyclerSticks.adapter = sticksAdapter
    sticksAdapter.setOnItemClickListener(
    object : SticksAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SticksRowModel) {
        onClickRecyclerSticks(view, position, item)
      }
    }
    )
    viewModel.sticksList.observe(this) {
      sticksAdapter.updateData(it)
    }
    binding.mycutspageBelongingsVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerSticks(
    view: View,
    position: Int,
    item: SticksRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "MYCUTSPAGE_BELONGINGS_ACTIVITY"

  }
}
