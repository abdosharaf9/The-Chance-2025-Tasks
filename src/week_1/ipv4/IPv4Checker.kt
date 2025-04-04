package week_1.ipv4

fun main() {
    println("Is \"192.168.1.1\" a valid IPv4 address? ${validateIPv4("192.168.1.1")}")
    println("Is \"192.333.1.1\" a valid IPv4 address? ${validateIPv4("192.333.1.1")}")
    println("Is \"-1.0.1.1\" a valid IPv4 address? ${validateIPv4("-1.0.1.1")}")
}


/**
 * Validate if a given IP address is an IPv4 address or not.
 *
 * @param ipAddress The IP address you need to validate.
 *
 * @return Returns true if the IP is a valid IPv4 address, and false if not.
 * */
fun validateIPv4(ipAddress: String): Boolean {
    if (ipAddress.isEmpty()) {
        return false
    }

    val octets = ipAddress.split(".")
    if (octets.size != 4) {
        return false
    }

    for (octet in octets) {
        if (!validateOctet(octet)) {
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
private fun validateOctet(octet: String): Boolean {
    if (octet.isEmpty() || octet.length > 3) {
        return false
    }

    if (octet.length > 1 && octet[0] == '0') {
        return false
    }

    val value = octet.toIntOrNull()
    return value != null && value in 0..255
}