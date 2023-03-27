package com.khusinov.aacademysamples.model

import java.util.*

class TaskTwo {
    constructor(
        body: String?,
        imageUrl: String?,
        date: Date?,
        sample: String?,
        description: String?,
        vocabulary: String?,
        ideas: String?,
        author: String?,
        score: String?,
        sort: Int?,
        type: Int?
    ) {
        this.body = body
        this.imageUrl = imageUrl
        this.date = date
        this.sample = sample
        this.description = description
        this.vocabulary = vocabulary
        this.ideas = ideas
        this.author = author
        this.score = score
        this.sort = sort
        this.type = type
    }

    constructor()
    constructor(
        body: String?,
        imageUrl: String?,
        date: Date?,
        sample: String?,
        description: String?,
        vocabulary: String?,
        ideas: String?,
        author: String?
    ) {
        this.body = body
        this.imageUrl = imageUrl
        this.date = date
        this.sample = sample
        this.description = description
        this.vocabulary = vocabulary
        this.ideas = ideas
        this.author = author
    }


    var body: String? = null
    var imageUrl: String? = null
    var date: Date? = null
    var sample: String? = null
    var description: String? = null
    var vocabulary: String? = null
    var ideas: String? = null
    var author: String? = null
    var score: String? = null
    var sort: Int? = null
    var type: Int? = null

}