package io.navendra.retrofitkotlindeferred.ui.repository

import java.time.LocalDate

data class NewsItemDetailsTable(

    val itemId: String,

    val body: String?,

    val createdAt: LocalDate?,

    val context: String?,

    var shortHeadline: String?

)
