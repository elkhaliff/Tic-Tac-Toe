fun main() {
    val cube: Array<Array<Array<Int>>> = Array(3, { 
        Array(3, {
            Array(3, { 0 })
            })
        })

    println(cube.contentDeepToString())
}
