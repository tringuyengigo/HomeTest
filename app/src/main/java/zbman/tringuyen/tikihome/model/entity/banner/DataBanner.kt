package zbman.tringuyen.tikihome.model.entity.banner

import com.google.gson.annotations.SerializedName


data class DataBanner(
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("mobile_url")
    val mobile_url: String,
    @SerializedName("ratio")
    val ratio: Double,
    @SerializedName("thumbnail_url")
    val thumbnail_url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)