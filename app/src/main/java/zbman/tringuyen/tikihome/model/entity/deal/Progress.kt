package zbman.tringuyen.tikihome.model.entity.deal

data class Progress(
    val percent: Float,
    val qty: Int,
    val qty_ordered: Int,
    val qty_remain: Int,
    val status: String
)