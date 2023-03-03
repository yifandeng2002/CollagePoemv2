package com.collagepoem.app.entity

import android.graphics.drawable.Drawable
import android.widget.ImageView

class Poem {
    var TimeEN: String? = null
    var TimeCN: String? = null
    var Order: String? = null
    var Descrip: String? = null
    var imageCardView: Drawable? = null
//    var byte: ByteArray? = null
    //var image: Blob? = null

    constructor() {}
    constructor(
        author: String,
        title: String,
        collecttime: String,
        country: String,
        imageCardView: Drawable,
        //image:Blob
    ) {
        this.TimeEN = author
        this.TimeCN = title
        this.Order = collecttime
        this.Descrip = country
        this.imageCardView = imageCardView
        //this.image = image
    }

//    fun getPhoto(blob: Blob): Drawable? {
////        byte = blob
////        var bais = ByteArrayInputStream(blob)
////        var photo =  Drawable.createFromStream(bais, "photo")//把图片设置到ImageView对象中
////        return photo
//    }
}