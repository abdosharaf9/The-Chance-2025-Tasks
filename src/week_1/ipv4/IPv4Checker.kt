package week_1.ipv4

fun main() {
    println("Is \"192.168.1.1\" a valid IPv4 address? ${isIPv4Valid("192.168.1.1")}")
    println("Is \"192.333.1.1\" a valid IPv4 address? ${isIPv4Valid("192.333.1.1")}")
    println("Is \"-1.0.1.1\" a valid IPv4 address? ${isIPv4Valid("-1.0.1.1")}")
}

//fun isIPv4Valid(ip: String): Boolean {
//    return true
//}


/**
 * Validate if a given IP address is an IPv4 address or not.
 *
 * @param ip The IP address you need to validate
 *
 * @return Returns true if the IP is a valid IPv4 address, and false if not.
 * */
fun isIPv4Valid(ip: String): Boolean {
    // Check if the IP is empty
    if (ip.isEmpty()) {
        return false
    }

    // Check if it has exactly 4 octets
    val octets = ip.split(".")
    if (octets.size != 4) {
        return false
    }

    // For each octet, check if it is a valid octet
    for (octet in octets) {
        if (!isOctetValid(octet)) {
            return false
        }
    }

    return true
}

/**
 * Validate if a given octet is an IPv4 octet or not.
 *
 * @param octet The octet you need to validate
 *
 * @return Returns true if the octet is a valid IPv4 octet, and false if not.
 * */
private fun isOctetValid(octet: String): Boolean {
    // Check octet size is between 1 and 3
    if (octet.isEmpty() || octet.length > 3) {
        return false
    }

    // Check if contains leading zeros
    if (octet.length > 1 && octet[0] == '0') {
        return false
    }

    // Check if it can be converted into a number (doesn't contain any character),
    // and the value is in range 0..255
    val value = octet.toIntOrNull()
    return value != null && value in 0..255
}