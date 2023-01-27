package com.collagepoem.app.modules.mycutspagebelongings.`data`.model


import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class SticksRowModel(
    /**
     * TODO Replace with dynamic value
     */
    var txtBookname: String? = MyApp.getInstance().resources.getString(R.string.lbl_bookname)
    ,
    /**
     * TODO Replace with dynamic value
     */
    var txtRemainingtime: String? =
        MyApp.getInstance().resources.getString(R.string.lbl_remainingtime)

)
