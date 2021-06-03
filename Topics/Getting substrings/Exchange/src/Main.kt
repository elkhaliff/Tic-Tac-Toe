fun main() {
    // put your code here
    var str = readLine()!!
    val first = str.first()
    str = str.replaceFirst(first, str.last())
    println(str.substring(0, str.length - 1) + first)
}