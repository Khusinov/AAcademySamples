package com.khusinov.aacademysamples.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class TaskOne(
    var body: String?,
    var imageUrl: String?,
    var date: String?,
    var sample: String?,
    var question: String?,
    var vocabulary: String?,
    var author: String?,
    var score: String?,
    var grammar:String?
): Parcelable {
}
