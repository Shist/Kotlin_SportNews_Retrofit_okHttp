package ui.repository

import java.time.LocalDate

data class NewsItemDetails(

    val itemId: String,

    val body: String?,

    val createdAt: LocalDate?,

    val context: String?,

    var shortHeadline: String?

)
