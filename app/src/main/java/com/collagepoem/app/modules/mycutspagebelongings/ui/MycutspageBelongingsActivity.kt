package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityMycutspageBelongingsBinding
import com.collagepoem.app.modules.driftcutspagetwo.ui.DriftcutspagetwoActivity
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.viewmodel.MycutspageBelongingsVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class MycutspageBelongingsActivity :
    BaseActivity<ActivityMycutspageBelongingsBinding>(R.layout.activity_mycutspage_belongings) {
  private val viewModel: MycutspageBelongingsVM by viewModels<MycutspageBelongingsVM>()

  private val REQUEST_CODE_DRIFTCUTSPAGETWO_ACTIVITY: Int = 257


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
    binding.imageBack.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerSticks(
    view: View,
    position: Int,
    item: SticksRowModel
  ): Unit {
    when(view.id) {
      R.id.imageStick -> {
        onClickRecyclerSticksImageStick(view, position, item)
      }
    }
  }

  fun onClickRecyclerSticksImageStick(
    view: View,
    position: Int,
    item: SticksRowModel
  ): Unit {
    /** TODO As per your logic, Add constant type for item click.*/
    when(0) {
      0 -> {
        val destIntent = DriftcutspagetwoActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_DRIFTCUTSPAGETWO_ACTIVITY)
        this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
      }
      1 -> {
        val destIntent = DriftcutspagetwoActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_DRIFTCUTSPAGETWO_ACTIVITY)
        this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
      }
    }
  }

  companion object {
    const val TAG: String = "MYCUTSPAGE_BELONGINGS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MycutspageBelongingsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
