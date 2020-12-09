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

    fun millisecondsToString(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        val rest = seconds % 60
        var res = if (hours > 0) "$hours:" else ""
        res += if (minutes > 0) "$minutes:" else "0:"
        res += if (rest < 10) "0$rest" else rest.toString()
        return res
    }

    fun amountToString(amount: Int): String {
        if (amount > 1000000) {
            return if (amount % 1000000 / 100000 == 0) {
                (amount / 1000000).toString() + " млн"
            } else {
                (amount / 1000000).toString() + "," + (amount % 1000000 / 100000).toString() + " млн"
            }
        }
        if (amount > 1000) return (amount / 1000).toString() + " тыс."
        return amount.toString()
    }
}
