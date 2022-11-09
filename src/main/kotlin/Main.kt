package cinema


val cinema = createSits()
var currentIncome = 0
fun main() {
    showMenu(cinema)
}

fun showMenu(cinema: MutableList<Array<String>>) {
    println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit")
    var command = readln().toInt()
    while (command != 0) {
        when (command) {
            1 -> showCinema(cinema)
            2 -> buyTicket(cinema)
            3 -> showStats()
            else -> println("Wrong input!")
        }
        println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit")
        command = readln().toInt()
    }
}

fun showStats() {
    var pt = 0
    for (row in cinema) {
        row.forEach { if (it == "B") pt++ }
    }
    val percentage = 100.0 / ((cinema.size - 1) * (cinema[0].size - 1)) * pt
    val formatPercentage = "%.2f".format(percentage)

    println("Number of purchased tickets: $pt")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $${countPrices(cinema.size - 1, cinema[0].size - 1)}")
}

fun buyTicket(cinema: MutableList<Array<String>>) {
    val rows = cinema.size
    val columns = cinema[0].size

    try {
        println("Enter a row number:")
        val rowNumber = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNumber = readln().toInt()
        if (cinema[rowNumber][seatNumber] != "S") {
            println("That ticket has already been purchased!")
            buyTicket(cinema)
        } else {
            cinema[rowNumber][seatNumber] = "B"
            println(
                "Ticket price: $${
                    if (rows * columns <= 60) {
                        currentIncome += 10
                        10
                    } else {
                        if (rowNumber <= 4) {
                            currentIncome += 10
                            10
                        } else {
                            currentIncome += 8
                            8
                        } }}")}
    } catch (ex: Exception) {
        println("Wrong input!")
        buyTicket(cinema)
    }
}


fun createSits(): MutableList<Array<String>> {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()
    val sits = mutableListOf<Array<String>>()
    for (r in 0..rows) {
        val row = mutableListOf<String>()
        for (c in 0..columns) {
            if (r == 0) {
                row.add(if (c == 0) " " else c.toString())
            } else {
                row.add(if (c > 0) "S" else r.toString())
            }
        }
        sits.add(row.toTypedArray())
    }
    return sits
}


fun countPrices(rows: Int = 10, columns: Int = 8): Int {
    return if (rows * columns < 60) {
        rows * columns * 10
    } else {
        val firstHalf = rows / 2
        val secondHalf = rows / 2 + rows % 2

        (firstHalf * columns * 10) + (secondHalf * columns * 8)
    }
}


fun showCinema(cinema: MutableList<Array<String>>) {
    println("Cinema:")
    cinema.forEach { it ->
        it.forEach { print("$it ") }
        println()
    }
}