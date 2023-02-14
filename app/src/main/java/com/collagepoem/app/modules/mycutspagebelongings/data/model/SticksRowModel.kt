package com.collagepoem.app.modules.mycutspagebelongings.`data`.model


import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import java.sql.Date

import kotlin.String

data class SticksRowModel(
//    var txtBookname: String? = MyApp.getInstance().resources.getString(R.string.lbl_bookname),
//
//    var txtRemainingtime: String? = MyApp.getInstance().resources.getString(R.string.lbl_remainingtime)

    var txtBookname: String? = null,
    var txtRemainingtime: Date? = null
)
