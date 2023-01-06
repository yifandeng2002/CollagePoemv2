package com.collagepoem.app.modules.mycutspagebelongings.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.MycutspageBelongingsModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class MycutspageBelongingsVM : ViewModel(), KoinComponent {
  val mycutspageBelongingsModel: MutableLiveData<MycutspageBelongingsModel> =
      MutableLiveData(MycutspageBelongingsModel())

  var navArguments: Bundle? = null

  val sticksList: MutableLiveData<MutableList<SticksRowModel>> = MutableLiveData(mutableListOf())
}
