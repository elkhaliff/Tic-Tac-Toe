fun main() {
    // put your code here
    val char = readLine()!!
    for (c in char) {
        if (c.isDigit()) {
            print(c)
            break
        } else continue

    }
}