package com.collagepoem.app.modules.canvas.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.canvas.`data`.model.CanvasModel
import org.koin.core.KoinComponent

class CanvasVM : ViewModel(), KoinComponent {
  val canvasModel: MutableLiveData<CanvasModel> = MutableLiveData(CanvasModel())

  var navArguments: Bundle? = null
}
