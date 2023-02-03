package com.collagepoem.app.modules.logincheckpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.logincheckpage.data.model.LoginCheckpageModel
import org.koin.core.KoinComponent

class LoginCheckpageVM : ViewModel(), KoinComponent {
  val loginCheckpageModel: MutableLiveData<LoginCheckpageModel> = MutableLiveData(LoginCheckpageModel())

  var navArguments: Bundle? = null
}
