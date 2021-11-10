package data.model

import com.google.gson.annotations.SerializedName

// Этот класс нам пока что не нужен, но может пригодится в будущем

data class NewsItemAuthorJson(
    @SerializedName("@id") val featuredMediaAuthorId: String?,
    @SerializedName("@type") val featuredMediaAuthorType: String?,
    @SerializedName("name") val featuredMediaAuthorName: String?
)
