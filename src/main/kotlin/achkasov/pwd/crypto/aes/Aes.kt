package achkasov.pwd.crypto.aes

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

private const val AES = "AES"
private const val CIPHER_MODE = "AES/ECB/NoPadding"
private val DEFAULT_CHARSET = Charsets.UTF_8
private const val BLOCK_SIZE_BYTE: Byte = 16

/**
 * Encrypt current string using AES, ECB mode in UTF-8 format.
 * Password is encoded using MD5 and used as string presentation in hex format.
 */
fun String.encryptByAesEcb(password: String): ByteArray {
    if (password.length != 16 && password.length != 32) {
        throw IllegalArgumentException("Key size should be 16 or 32.")
    }
    val byteArrayToEncrypt = this.adaptLength(BLOCK_SIZE_BYTE).toByteArray(DEFAULT_CHARSET)
    val keySpec = SecretKeySpec(password.toByteArray(DEFAULT_CHARSET), AES)
    val cipher = Cipher.getInstance(CIPHER_MODE).apply { this.init(Cipher.ENCRYPT_MODE, keySpec) }
    return cipher.doFinal(byteArrayToEncrypt)

}

fun ByteArray.decryptByAesEcb(password: String): ByteArray {
    if (password.length != 16 && password.length != 32) {
        throw IllegalArgumentException("Key size should be 16 or 32.")
    }
    val keySpec = SecretKeySpec(password.toByteArray(DEFAULT_CHARSET), AES)
    val cipher = Cipher.getInstance(CIPHER_MODE).apply { this.init(Cipher.DECRYPT_MODE, keySpec) }
    return cipher.doFinal(this)
}

private fun String.adaptLength(blockSize: Byte): String {
    val currentLength = this.length
    val delta = blockSize - (currentLength % blockSize)
    if (delta == 0) {
        return this
    }
    return this + " ".repeat(delta)
}