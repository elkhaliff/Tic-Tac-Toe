fun main() {
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    
    val l = n - 1
    println("${inputArray[0][0]} ${inputArray[0][l]}")
    println("${inputArray[l][0]} ${inputArray[l][l]}")

}
