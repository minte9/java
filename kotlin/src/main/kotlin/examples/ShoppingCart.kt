/**
 * Shopping Cart (List example)
 *
 * Kotlin uses interfaces from the start.
 * It returns a Mutable interface, backed by an ArrayList.
 */

package examples

fun main() {

    // Preferred: code to the interface
    var cart: MutableList<String> = mutableListOf()
    cart.add("milk")
    cart.add("bread")
    cart.add("eggs")
    println(cart)  // [milk, bread, eggs]

    // Remove item
    cart.remove("milk")
    println(cart)  // [bread, eggs]

    // Reassign to a read-only List (immutable)
    cart = listOf("water", "cereal").toMutableList()
    println(cart)  // [water, cereal]

    // Now cart is mutable again, because we convert it
    cart.add("butter")
    println(cart)  // [water, cereal]
}
