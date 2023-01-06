package com.collagepoem.app.modules.driftcutspageone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.driftcutspageone.`data`.model.DriftcutspageoneModel
import com.collagepoem.app.modules.driftcutspageone.`data`.model.Works3RowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class DriftcutspageoneVM : ViewModel(), KoinComponent {
  val driftcutspageoneModel: MutableLiveData<DriftcutspageoneModel> =
      MutableLiveData(DriftcutspageoneModel())

  var navArguments: Bundle? = null

  val worksList: MutableLiveData<MutableList<Works3RowModel>> = MutableLiveData(mutableListOf())
}
