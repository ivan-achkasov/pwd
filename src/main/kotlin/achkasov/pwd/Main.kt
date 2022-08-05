package achkasov.pwd

import achkasov.pwd.crypto.aes.decryptByAesEcb
import achkasov.pwd.crypto.aes.encryptByAesEcb
import achkasov.pwd.crypto.toHexString
import achkasov.pwd.crypto.toMd5HexString


fun main(args: Array<String>) {
    val initValue = ""
    val password = ""
    val encodedPassword = password.toMd5HexString()

    val encodedArray = initValue.encryptByAesEcb(encodedPassword)
    println(encodedArray.toHexString())
    println(encodedArray.decryptByAesEcb(encodedPassword).toString(Charsets.UTF_8))

}
