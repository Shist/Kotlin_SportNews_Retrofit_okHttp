package domain

import java.time.LocalDate

data class NewsItem(

    val itemId: String,

    val altText: String?,

    val createdAt: LocalDate?,

    val context: String?,

    var shortHeadline: String?

)