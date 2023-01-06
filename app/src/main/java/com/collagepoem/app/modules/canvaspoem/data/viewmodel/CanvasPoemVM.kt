package com.collagepoem.app.modules.canvaspoem.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.canvaspoem.`data`.model.CanvasPoemModel
import org.koin.core.KoinComponent

class CanvasPoemVM : ViewModel(), KoinComponent {
  val canvasPoemModel: MutableLiveData<CanvasPoemModel> = MutableLiveData(CanvasPoemModel())

  var navArguments: Bundle? = null
}
