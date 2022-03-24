package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommission_mastercard_maestro_over_limit() {
        //arrange
        val typeCard = "Maestro"
        val previousTransfers = 80_000_00
        val amount = 6_000_00

        //act
        val result = calculateCommission(
        typeCard = typeCard,
        previousTransfers = previousTransfers,
        amount = amount
        )

        //assert
        assertEquals(56_00, result)
    }

    @Test
    fun calculateCommission_mastercard_maestro_less_limit() {
        //arrange
        val typeCard = "Maestro"
        val previousTransfers = 10_000_00
        val amount = 6_000_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calculateCommission_visa_mir_over_min() {
        //arrange
        val typeCard = "Visa"
        val previousTransfers = 80_000_00
        val amount = 6_000_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(45_00, result)
    }

    @Test
    fun calculateCommission_visa_mir_less_min() {
        //arrange
        val typeCard = "Visa"
        val previousTransfers = 1_000_00
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(35_00, result)
    }

    @Test
    fun calculateCommission_other_over_month_limit() {
        //arrange
        val typeCard = "Visa"
        val previousTransfers = 650_000_00
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun calculateCommission_other_over_one_limit() {
        //arrange
        val typeCard = "Visa"
        val previousTransfers = 5_000_00
        val amount = 160000_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(-2, result)
    }

    @Test
    fun calculateCommission_vk_pay() {
        //arrange
        val typeCard = "Vk Pay"
        val previousTransfers = 1_000_00
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calculateCommission_vk_pay_over_month_limit() {
        //arrange
        val typeCard = "Vk Pay"
        val previousTransfers = 50_000_00
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun calculateCommission_vk_pay_over_one_limit() {
        //arrange
        val typeCard = "Vk Pay"
        val previousTransfers = 5_000_00
        val amount = 16000_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            previousTransfers = previousTransfers,
            amount = amount
        )

        //assert
        assertEquals(-2, result)
    }

    @Test
    fun calculateCommission_no_previous() {
        //arrange
        val typeCard = "Vk Pay"
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            amount = amount
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calculateCommission_unknown_type_card() {
        //arrange
        val typeCard = "Union Pay"
        val amount = 100_00

        //act
        val result = calculateCommission(
            typeCard = typeCard,
            amount = amount
        )

        //assert
        assertEquals(0, result)
    }
}