package com.collagepoem.app.noteinfo

import android.util.Log
import com.collagepoem.app.utils.JDBCUtils
import com.collagepoem.app.entity.Note
import com.collagepoem.app.entity.Note1
import com.collagepoem.app.modules.mycutspagebelongings.data.model.SticksRowModel
import java.lang.Exception
import java.util.HashMap

class NoteInfo {
    fun showNote(note:Note1): Note1? {
        val mapnote = HashMap<String, Any>()
        // 根据数据库名称，建立连接
        val connection = JDBCUtils.conn
        //var note: Note? = null
        try {
            val sql = "select * from noteinfo "
            if (connection != null) { // connection不为null表示与数据库建立了连接
                val ps = connection.prepareStatement(sql)
                if (ps != null) {
                    //ps.setInt(0, state)
                    val rs = ps.executeQuery()
                    var count = 0
                    while (rs.next()) {
                        var author = rs.getString(1)
                        var title= rs.getString(2)
                        var collecttime = rs.getDate(3)
                        var country= rs.getString(4)
                        var state = rs.getInt(5)
                        var mentioned = rs.getInt(6)
                        var cited = rs.getInt(7)
                        //var image= rs.getBlob(8)
                        if(author != null){
                            note.author.add(author)
                            note.cited.add(cited)
                            note.title.add(title)
                            note.collecttime.add(collecttime)
                            note.country.add(country)
                            note.state.add(state)
                            note.mentioned.add(mentioned)
                        }

//                        note = Note(
//                            author,
//                            title,
//                            collecttime,
//                            country,
//                            state,
//                            mentioned,
//                            cited
//                            //image
//                        )
//                        count = count +1
//                        Log.i("name",note.title.toString())
                        Log.i("test",note.author.toString())
                        Log.i("count",count.toString())

                    }
                }
                connection.close()
                ps.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("note", "异常" )
            return null
        }
        Log.i("note",note.title.toString())
        return note

    }
}
