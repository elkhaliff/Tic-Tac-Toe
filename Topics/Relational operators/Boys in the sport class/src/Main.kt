fun main() {
    // put your code here
    val scan = java.util.Scanner(System.`in`)
    var num1 = scan.nextInt()
    var num2 = scan.nextInt()
    var num3 = scan.nextInt()
    var boll = when {
        num1 >= num2  && num2 >= num3 -> true
        num1 <= num2 && num2 <= num3 -> true
        else -> false
    }
    println (boll)    
}
