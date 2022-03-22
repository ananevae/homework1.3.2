package ru.netology

fun calculateCommission(
    typeCard: String = "Vk Pay",
    previousTransfers: Int = 0,
    amount: Int,
    limit: Int,
    perc1: Double,
    perc2: Double,
    fix1: Int,
    min2: Int
): Int {
    when (typeCard){
        "Mastercard", "Maestro" -> {
            return if (previousTransfers > limit) {
                (perc1 * amount + fix1).toInt()
            } else 0
        }
        "Visa", "Mир" -> {
            return if ((perc2 * amount) > min2) {
                (perc2 * amount).toInt()
            } else {
                min2
            }
        }
        else -> return 0
    }
}

fun main() {
    val amount = 6_000_00
    val typeCard = "Visa"
    val previousTransfers = 10_000_00
    val limit = 75_000_00
    val perc1 = 0.6
    val perc2 = 0.75
    val fix1 = 20_00
    val min2 = 35_00
    val limVk = 40_000_00
    val oneLimVk = 15_000_00
    val limOther = 600_000_00
    val oneLimOther = 150_000_00

    if (typeCard == "Vk Pay") {
        if (previousTransfers + amount > limVk) {
            println("Превышен месячный лимит на переводы Vk Pay")
        }
        else if (amount > oneLimVk) {
            println("Превышена максимальная сумма перевода")
        }
        else println("Total Commission: 0 руб. 0 коп.")
    }
    else {
        if (previousTransfers + amount > limOther) {
            println("Превышен месячный лимит перевода на карту $typeCard")
        }
        else if (amount > oneLimOther) {
            println("Превышена максимальная сумма перевода")
        }
        else {
            val totalCommission =
                calculateCommission(typeCard, previousTransfers, amount, limit, perc1, perc2, fix1, min2)
            println("Total Commission: " + totalCommission / 100 + " руб. " + totalCommission % 100 + " коп.")
        }
    }

}
