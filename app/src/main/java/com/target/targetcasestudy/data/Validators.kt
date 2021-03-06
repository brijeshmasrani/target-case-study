package com.target.targetcasestudy.data

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun <T> T.log(): T {
  println(this); return this
}

fun validateCreditCard(creditCardNumber: String): Boolean {
  if (creditCardNumber.length in 13..19) {
    val lastDigit = creditCardNumber.last().digitToInt()
    val resultFromLuhnFormula = creditCardNumber
      .dropLast(1)
      .reversed()
      .mapIndexed { index, element ->
        if (index % 2 != 0) {
          element.digitToInt().times(2)
        } else {
          element.digitToInt()
        }
      }
      .map {
        if (it > 9) { it - 9 }
        else { it }
      }
      .reduce { acc, value -> acc + value }
      .div(10)

    return resultFromLuhnFormula == lastDigit
  }
  return false
}