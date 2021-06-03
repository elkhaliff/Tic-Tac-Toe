fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var i = 0

    for (item in strings) {
        if (item.equals(str)) {
            i++
        }
    }
    return i
}