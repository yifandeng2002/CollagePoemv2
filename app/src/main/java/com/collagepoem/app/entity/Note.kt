package com.collagepoem.app.entity

import java.sql.DatabaseMetaData
import java.sql.Date
import java.sql.Time

class Note {
    var author : String? = null
    var title: String? = null
    lateinit var collecttime: Date
    var country: String? = null
    var state = 0
    var mentioned = 0
    var cited = 0
    //var image: Blob? = null

    constructor() {}
    constructor(
        author: String?,
        title: String?,
        collecttime: Date,
        country: String?,
        state: Int,
        mentioned: Int,
        cited: Int,
        //image:Blob
    ) {
        this.author = author
        this.title = title
        this.collecttime = collecttime
        this.country = country
        this.state = state
        this.mentioned = mentioned
        this.cited = cited
        //this.image = image
    }
}