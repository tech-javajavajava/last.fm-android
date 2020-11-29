package technopark.andruxa.myapplication

import java.security.MessageDigest

object Utils {
    fun md5(str: String): String {
        val digest = MessageDigest.getInstance("MD5")

        val bytes = digest.digest(str.toByteArray(charset("UTF-8")))
        val b = StringBuilder(32)
        for (aByte in bytes) {
            val hex = Integer.toHexString(aByte.toInt() and 0xFF)
            if (hex.length == 1)
                b.append('0')
            b.append(hex)
        }
        return b.toString()
    }
}