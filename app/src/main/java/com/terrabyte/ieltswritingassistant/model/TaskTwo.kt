package com.terrabyte.ieltswritingassistant.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TaskTwo(

    var imageUrl: String?,
    var date: String?,
    var sample: String?,
    var question: String?,
    var vocabulary: String?,
    var author: String?,
    var score: String?,
    var grammar: String?
) : Parcelable {
}