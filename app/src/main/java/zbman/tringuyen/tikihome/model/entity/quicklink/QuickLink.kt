import com.google.gson.annotations.SerializedName
import zbman.tringuyen.tikihome.model.entity.quicklink.DataQuickLink


data class QuickLink (

	@SerializedName("data") val data : List<List<DataQuickLink>>
)