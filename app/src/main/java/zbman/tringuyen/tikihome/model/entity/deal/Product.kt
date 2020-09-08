package zbman.tringuyen.tikihome.model.entity.deal

data class Product(
    val badges: List<Any>,
    val discount: Int,
    val id: Int,
    val inventory: Inventory,
    val is_flower: Boolean,
    val is_fresh: Boolean,
    val is_gift_card: Boolean,
    val is_visible: Boolean,
    val list_price: Int,
    val master_id: Int,
    val name: String,
    val order_count: Int,
    val price: Int,
    val price_prefix: String,
    val rating_average: Int,
    val review_count: Int,
    val seller_product_id: Int,
    val sku: Any,
    val thumbnail_url: String,
    val url_attendant_input_form: String,
    val url_path: String
)