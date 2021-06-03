fun main() {
    // put your code here
    val arrChar = readLine()!!
    var n = 0
    var m = 0

    for (c in 'a'..'z') {
        n = 0
        for (a in arrChar) {
            if (c == a) n++
            if (n == 2) break
        }
        if (n == 1) m++
    }
    println(m)
}