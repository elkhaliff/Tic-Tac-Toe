package tictactoe

class TicTacToe(val cells: Int) {
    val rows = cells // Количество строк
    val cols = cells // Количество столбцов
    var cntX = 0 // Общее количество крестиков
    var cntO = 0 // Общее количество ноликов

    /**
     * Инициализация массива рабочей области крестиков-ноликов
     */
    val fieldMap: Array<Array<String>> = Array(rows, { Array(cols, {" "}) })

    /**
     * Ход крестиков
     */
    fun setX(row: Int, col: Int) {
        fieldMap[row-1][col-1] = "X"
    }

    /**
     * Ход ноликов
     */
    fun setO(row: Int, col: Int) {
        fieldMap[row-1][col-1] = "O"
    }

    /**
     * Проверка на незаполненность
     */
    fun isEmpty(row: Int, col: Int) = (fieldMap[row-1][col-1] == " ")

    /**
     * Получение столбца данных
     */
    fun getCol(col: Int): String {
        var out = ""
        for (r in 1..rows) {
            out += fieldMap[r-1][col-1]
        }
        return out
    }

    /**
     * Получение строки данных
     */
    fun getRow(row: Int): String {
        var out = ""
        for (c in 1..cols) {
            out += fieldMap[row-1][c-1]
        }
        return out
    }

    /**
     * Получение правой диагонали
     */
    fun getRightDiagonal(): String {
        var out = ""
        for (c in 1..cells) {
            out += fieldMap[c-1][c-1]
        }
        return out
    }

    /**
     * Получение левой диагонали
     */
    fun getLeftDiagonal(): String {
        var out = ""
        var c = cells
        for (r in 1..cells) {
            out += fieldMap[r-1][--c]
        }
        return out
    }

    /**
     *  Заполнение переменной с массивом игровой доски - строкой с данными
     */
    fun setBoardFromStr(str: String) {
        for (r in 1..rows)
            for (c in 1..cols)
                fieldMap[r-1][c-1] = str[((r-1)*3)+c-1].toString()
        println(this) // печать полученной игровой области
    }

    /**
     * Формирование строки из игрового поля данного класса
     * (в частности - получаем возможность вывода на печать)
     */
    override fun toString(): String {
        val border = Array(cols*3, {'-'}).joinToString("")+"\n"
        var mapString = border

        fieldMap.forEach { row ->
            mapString += "| "
            row.forEach { c ->
                mapString += "$c "
            }
            mapString += "|\n"
        }

        mapString += border

        return mapString
    }

    /**
     * Статистика крестиков и ноликов
     */
    fun statXO(){
        cntX = 0
        cntO = 0
        fieldMap.forEach { row ->
            row.forEach { c ->
                when (c) {
                    "X" -> cntX++
                    "O" -> cntO++
                }
            }
        }
    }

    /**
     * Количество свободных ячеек (возможность хода)
     */
    fun isEmptyCell() = (rows*cols - (cntX + cntO))>0

    /**
     * Проверка поля на ничью и выигрыш одного из игроков
     */
    fun checkGame(): String {
        var isWinX = false // X wins
        var isWin0 = false // O wins
//        var isImposs = false // Impossible
        val winX = Array(cols, {'X'}).joinToString("")
        val win0 = Array(cols, {'O'}).joinToString("")

//        isImposs = abs(cntX - cntO) >=2
//        if (isImposs) return "Impossible"

        for (r in 1..rows) {
            isWinX = getRow(r).equals(winX) || isWinX
            isWin0 = getRow(r).equals(win0) || isWin0
        }
        for (c in 1..cols) {
            isWinX = getCol(c).equals(winX) || isWinX
            isWin0 = getCol(c).equals(win0) || isWin0
        }

        isWinX = getRightDiagonal().equals(winX) || isWinX
        isWin0 = getRightDiagonal().equals(win0) || isWin0

        isWinX = getLeftDiagonal().equals(winX) || isWinX
        isWin0 = getLeftDiagonal().equals(win0) || isWin0

//        isImposs = (isWin0 && isWinX) || isImposs

        when {
//            isImposs        -> return "Impossible"
            isWinX          -> return "X wins"
            isWin0          -> return "O wins"
            !isEmptyCell()  -> return "Draw"
        }
        return ""
    }

    /**
     * Проверка и установка нового значения (пока только ход крестиков)
     */
    fun setCoordinates(step: Char): Int {
        var isNumeric = true

        print("Enter the coordinates (${step}): ")
        val cells = readLine()!!.split(' ')

        isNumeric = (cells[0].matches("\\d+".toRegex())) &&
                (cells[1].matches("\\d+".toRegex()))
        if (!isNumeric) {
            return 1    // You should enter numbers!
        }

        val row = cells[0].toInt()
        val col = cells[1].toInt()

        if (!(row in 1..3) || !(col in 1..3)) {
            return 2    // Coordinates should be from 1 to 3!
        }

        if (!isEmpty(row, col)) {
            return 3    // This cell is occupied! Choose another one!
        }

        if (step == 'X') {
            setX(row, col)
        } else {
            setO(row, col)
        }

        return 0
    }

}

fun main(args: Array<String>) {
    val ttt = TicTacToe(3) // Инициализация экземпляра класса с заданной шириной поля
    println(ttt)
    var err = -1
    var step = 'X'
    var checkGameStr = ""

//    print("Enter cells: ")
//    val cells = readLine()!!
//    ttt.setBoardFromStr(cells)

    while (checkGameStr == "") { // Цикл получения координат - ожидание хода, проверка результатов
        err = ttt.setCoordinates(step) // Запрашиваем ход игрока, устанавливаем ход на доску
        when (err) {
            0 -> {
                step = if (step == 'O') 'X' else 'O'
                ttt.statXO() // Сбор статистики по заполненным клеткам
                checkGameStr = ttt.checkGame()
                println(ttt)
            }
            1 -> println("You should enter numbers!")
            2 -> println("Coordinates should be from 1 to 3!")
            3 -> println("This cell is occupied! Choose another one!")
        }
    }

    println(checkGameStr)
}