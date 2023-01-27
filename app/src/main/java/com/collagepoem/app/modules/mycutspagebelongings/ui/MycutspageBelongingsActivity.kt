package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.modules.driftcutspagetwo.ui.DriftcutspagetwoActivity
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.viewmodel.MycutspageBelongingsVM
import kotlin.Int
import kotlin.String
import kotlin.Unit
import com.collagepoem.app.databinding.ActivityMycutspageBelongingsBinding
//import com.collagepoem.app.modules.mycutspagebelongingsone.ui.MycutspageBelongingsOneActivity
//import com.collagepoem.app.modules.profilepagemypagecontainer.ui.ProfilepageMypageContainerActivity


class MycutspageBelongingsActivity :
  BaseActivity<ActivityMycutspageBelongingsBinding>(R.layout.activity_mycutspage_belongings) {
  private val viewModel: MycutspageBelongingsVM by viewModels<MycutspageBelongingsVM>()

  private val REQUEST_CODE_MYCUTSPAGE_BELONGINGS_ONE_ACTIVITY: Int = 837


  private val REQUEST_CODE_PROFILEPAGE_MYPAGE_CONTAINER_ACTIVITY: Int = 787


  private val REQUEST_CODE_DRIFTCUTSPAGETWO_ACTIVITY: Int = 333


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
//    binding.txtFlowaway.setOnClickListener {
//      val destIntent = MycutspageBelongingsOneActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_MYCUTSPAGE_BELONGINGS_ONE_ACTIVITY)
//      this.overridePendingTransition(R.anim.right_to_left ,R.anim.left_to_right )
//    }
//    binding.imageBack.setOnClickListener {
//      val destIntent = ProfilepageMypageContainerActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_PROFILEPAGE_MYPAGE_CONTAINER_ACTIVITY)
//      this.overridePendingTransition(R.anim.zoom_out ,R.anim.zoom_in )
//    }
  }

  fun onClickRecyclerSticks(
    view: View,
    position: Int,
    item: SticksRowModel
  ): Unit {
    when(view.id) {
      R.id.linearStick -> {
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
