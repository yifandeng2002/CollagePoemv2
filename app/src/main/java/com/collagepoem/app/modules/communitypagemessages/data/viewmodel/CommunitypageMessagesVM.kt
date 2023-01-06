package com.collagepoem.app.modules.communitypagemessages.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.communitypagemessages.`data`.model.CommunitypageMessagesModel
import com.collagepoem.app.modules.communitypagemessages.`data`.model.MessagesRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class CommunitypageMessagesVM : ViewModel(), KoinComponent {
  val communitypageMessagesModel: MutableLiveData<CommunitypageMessagesModel> =
      MutableLiveData(CommunitypageMessagesModel())

  var navArguments: Bundle? = null

  val messagesList: MutableLiveData<MutableList<MessagesRowModel>> =
      MutableLiveData(mutableListOf())
}
