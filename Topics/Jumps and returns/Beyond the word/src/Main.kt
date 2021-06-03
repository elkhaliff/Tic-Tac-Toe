fun main() {
    // put your code here
    val arrChar = readLine()!!
    for (c in 'a'..'z') {
        if (arrChar.contains(c)) { continue }
        print(c)
    }
}
