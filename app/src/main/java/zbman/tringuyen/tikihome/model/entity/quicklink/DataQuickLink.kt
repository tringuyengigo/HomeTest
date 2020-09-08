package zbman.tringuyen.tikihome.model.entity.quicklink
import com.google.gson.annotations.SerializedName


data class DataQuickLink (

	@SerializedName("title") val title : String,
	@SerializedName("content") val content : String,
	@SerializedName("url") val url : String,
	@SerializedName("authentication") val authentication : Boolean,
	@SerializedName("web_url") val web_url : String,
	@SerializedName("image_url") val image_url : String
)