package achkasov.pwd.crypto

import java.security.MessageDigest


fun String.toMd5HexString(): String {
    val digest = MessageDigest.getInstance("MD5")
    val encodedHash = digest.digest(this.toByteArray(Charsets.UTF_8))
    return encodedHash.toHexString()
}

fun ByteArray.toHexString(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }