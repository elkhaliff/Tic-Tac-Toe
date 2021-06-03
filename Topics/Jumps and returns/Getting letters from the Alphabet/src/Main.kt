fun main() {
    // put your code here
    val char = readLine()!![0]
    for (c in 'a'..'z') {
        if (char == c) { break }
        print(c)
    }
}