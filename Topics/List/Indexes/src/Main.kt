fun solution(products: List<String>, product: String) {
    // put your code here
    var i = 0

    for (item in products) {
        if (item.equals(product)) {
            print("$i ")
        }
        i++
    }
}