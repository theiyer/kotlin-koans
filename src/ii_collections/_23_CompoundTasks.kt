package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return customers.filter { product in it.orders.flatMap { it.products } }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return orders.filter { it.isDelivered }.flatMap { it.products }.toSet().maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    val customersWhoBought: Set<Customer> = getCustomersWhoOrderedProduct(product)
    var nOrders: Int = 0
    for (customer in customersWhoBought) {
        nOrders += customer.orders.flatMap { it.products }.count { product == it }
    }
    return nOrders
}
