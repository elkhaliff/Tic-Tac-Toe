fun solution(numbers: List<Int>) {
    // put your code here
    numbers.forEach {
        it -> if (it % 2 == 0) print("$it ")
    }
}
