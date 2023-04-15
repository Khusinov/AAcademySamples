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
    var description: String?,
    var vocabulary: String?,
    var ideas: String?,
    var author: String?,
    var score: String?,
    var sort: Int?,
    var type: Int?,
    var grammar:String?
): Parcelable {
}
