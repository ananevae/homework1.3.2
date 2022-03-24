package ru.netology

const val limit = 75_000_00
const val perc1 = 0.6
const val perc2 = 0.75
const val fix1 = 20_00
const val min2 = 35_00
const val limVk = 40_000_00
const val oneLimVk = 15_000_00
const val limOther = 600_000_00
const val oneLimOther = 150_000_00

fun calculateCommission(
    typeCard: String,
    previousTransfers: Int = 0,
    amount: Int
): Int {

    if (typeCard == "Vk Pay") {
        return if (previousTransfers + amount > limVk) {
            -1
        } else if (amount > oneLimVk) {
            -2
        } else 0
    } else {
        return if (previousTransfers + amount > limOther) {
            -1
        } else if (amount > oneLimOther) {
            -2
        } else {
            when (typeCard) {
                "Mastercard", "Maestro" -> {
                    return if (previousTransfers > limit) {
                        (perc1 / 100 * amount + fix1).toInt()
                    } else 0
                }
                "Visa", "Mир" -> {
                    return if ((perc2 / 100 * amount) > min2) {
                        (perc2 / 100 * amount).toInt()
                    } else {
                        min2
                    }
                }
                else -> return -3
            }
        }
    }
}

fun main() {
    val amount = 6_000_00
    val typeCard = "Visa"
    val previousTransfers = 80_000_00

    val totalCommission = calculateCommission(typeCard, previousTransfers, amount)
    if (totalCommission == -1) {
        println("Превышен месячный лимит на переводы")
    } else if (totalCommission == -2) {
        println("Превышена максимальная сумма перевода")
    } else if (totalCommission == -3) {
        println("Перевод с этой карты недоступен")
    } else {
        println("Total Commission: " + totalCommission / 100 + " руб. " + totalCommission % 100 + " коп.")
    }

}
