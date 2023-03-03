package com.collagepoem.app.modules.mainpage.`data`.model


import android.graphics.drawable.Drawable
import android.media.Image
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import java.sql.Date

import kotlin.String

data class CardRowModel(
//    var txtBookname: String? = MyApp.getInstance().resources.getString(R.string.lbl_bookname),
//
//    var txtRemainingtime: String? = MyApp.getInstance().resources.getString(R.string.lbl_remainingtime)

    var TimeEN: String? = null,
    var TimeCN: String? = null,
    var Order: String? = null,
    var Descrip: String? = null,
    var imageCardView: Drawable? = null
)
