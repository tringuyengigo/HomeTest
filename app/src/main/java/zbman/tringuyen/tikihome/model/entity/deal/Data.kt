package zbman.tringuyen.tikihome.model.entity.deal

data class Data(
    val discount_percent: Int,
    val from_date: String,
    val product: Product,
    val progress: Progress,
    val special_from_date: Int,
    val special_price: Int,
    val special_to_date: Int,
    val status: Int,
    val tags: String,
    val url: String
)