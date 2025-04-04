package week_1.ipv4

/*
Invalid IPv4 String:
    - the string is null ==> can't happen in my code
    - the string is empty
    - the IP doesn't contain 3 octets, for example: 2 octets or 5 octets
    - doesn't contain dots
    - contains spaces or any character instead of the dot '.', for example: '+'
    - just contains dots with no numbers

Invalid IPv4 Octets:
    - the section is null ==> can't happen in my code
    - the section is empty
    - section size is bigger than 3 (maximum "255" is of size 3)
    - contains an invalid character, for example: ':' or 'a'
    - contains spaces
    - contains leading zeros
    - contains a number not in the range 0..255
*/

fun main() {
    println("String Tests:")
    // region Invalid IPv4 String
    check(
        name = "Given an empty string, when validating, then it should return false",
        result = isIPv4Valid(""),
        correctResult = false
    )

    check(
        name = "Given an IP with octets less than 4, when validating, then it should return false",
        result = isIPv4Valid("192.168.1"), // 3 octets
        correctResult = false
    )

    check(
        name = "Given an IP with octets more than 4, when validating, then it should return false",
        result = isIPv4Valid("192.168.1.1.50"), // 5 octets
        correctResult = false
    )

    check(
        name = "Given an IP with no dots, when validating, then it should return false",
        result = isIPv4Valid("19216811"),
        correctResult = false
    )

    check(
        name = "Given an IP with spaces instead of dots, when validating, then it should return false",
        result = isIPv4Valid("192 168 1 1"),
        correctResult = false
    )

    check(
        name = "Given an IP with special character instead of dots, when validating, then it should return false",
        result = isIPv4Valid("192+168+1+1"),
        correctResult = false
    )

    check(
        name = "Given an IP with only dots, when validating, then it should return false",
        result = isIPv4Valid("..."),
        correctResult = false
    )

    // endregion


    println("\nOctets Tests:")
    // region Invalid Octets
    check(
        name = "Given an IP with an empty octet, when validating, then it should return false",
        result = isIPv4Valid("192..1.1"), // The second octet is empty
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains more than 3 digits, when validating, then it should return false",
        result = isIPv4Valid("19202.168.1.1"), // The first octet has 5 digits
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains a special character, when validating, then it should return false",
        result = isIPv4Valid("19:.168.1.1"), // The first octet contains ':'
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains a character, when validating, then it should return false",
        result = isIPv4Valid("19a.168.1.1"), // The first octet contains 'a' character
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains a space, when validating, then it should return false",
        result = isIPv4Valid("1 9.168.1.1"), // The first octet contains a space
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains leading zeros, when validating, then it should return false",
        result = isIPv4Valid("001.168.1.1"), // The first octet contains leading zeros
        correctResult = false
    )

    check(
        name = "Given an IP with an octet contains a number bigger than 255, when validating, then it should return false",
        result = isIPv4Valid("333.168.1.1"), // The first octet contains 333
        correctResult = false
    )

    // endregion


    println("\nValid IPv4 Tests:")
    // region Valid IPv4
    check(
        name = "Given a valid IPv4, when validating, then it should return true",
        result = isIPv4Valid("0.0.0.0"),
        correctResult = true
    )

    check(
        name = "Given a valid IPv4, when validating, then it should return true",
        result = isIPv4Valid("255.255.255.255"),
        correctResult = true
    )

    check(
        name = "Given a valid IPv4, when validating, then it should return true",
        result = isIPv4Valid("0.1.255.15"),
        correctResult = true
    )

    check(
        name = "Given a valid IPv4, when validating, then it should return true",
        result = isIPv4Valid("192.168.1.1"),
        correctResult = true
    )

    // endregion
}

private fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Succeed - $name")
    } else {
        println("❌ Failed - $name")
    }
}
