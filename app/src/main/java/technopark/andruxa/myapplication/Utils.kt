package technopark.andruxa.myapplication

import java.security.MessageDigest

object Utils {
    fun md5(str: String): String {
        return MessageDigest.getInstance("MD5").digest(str.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}