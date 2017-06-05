package ii_collections

fun example6() {
    val sum = listOf(1, 5, 3).sum()
}

fun Customer.getTotalOrderPrice(): Double {
    // Return the sum of prices of all products that a customer has ordered.
    // Note: a customer may order the same product for several times.
    /*
    var totalPrice: Double = 0.0
    for (order in orders) totalPrice += order.products.sumByDouble { it.price }
    return totalPrice
    */
    return this.orders.map { it.products }.flatMap { it }.sumByDouble { it.price }
}
