package com.khusinov.aacademysamples.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Tip(
    var tipBody: String?,
    var imageUrl: String?
) : Parcelable {
}