package com.collagepoem.app.modules.floatwindowmycutsvtwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityFloatwindowMycutsVtwoBinding
import com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.model.StickRowModel
import com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.viewmodel.FloatwindowMycutsVtwoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FloatwindowMycutsVtwoActivity :
    BaseActivity<ActivityFloatwindowMycutsVtwoBinding>(R.layout.activity_floatwindow_mycuts_vtwo) {
  private val viewModel: FloatwindowMycutsVtwoVM by viewModels<FloatwindowMycutsVtwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val stickAdapter = StickAdapter(viewModel.stickList.value?:mutableListOf())
    binding.recyclerStick.adapter = stickAdapter
    stickAdapter.setOnItemClickListener(
    object : StickAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : StickRowModel) {
        onClickRecyclerStick(view, position, item)
      }
    }
    )
    viewModel.stickList.observe(this) {
      stickAdapter.updateData(it)
    }
    binding.floatwindowMycutsVtwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageImageBack.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerStick(
    view: View,
    position: Int,
    item: StickRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "FLOATWINDOW_MYCUTS_VTWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, FloatwindowMycutsVtwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
