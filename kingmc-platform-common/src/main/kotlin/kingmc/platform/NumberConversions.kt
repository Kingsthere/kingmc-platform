package kingmc.platform

import kotlin.math.abs

/**
 * Utils for casting number types to other number types
 */
object NumberConversions {
    @JvmStatic
    fun floor(num: Double): Int {
        val floor = num.toInt()
        return if (floor.toDouble() == num) floor else floor - (java.lang.Double.doubleToRawLongBits(num) ushr 63).toInt()
    }

    @JvmStatic
    fun ceil(num: Double): Int {
        val floor = num.toInt()
        return if (floor.toDouble() == num) floor else floor + (java.lang.Double.doubleToRawLongBits(num)
            .inv() ushr 63).toInt()
    }

    @JvmStatic
    fun round(num: Double): Int {
        return floor(num + 0.5)
    }

    @JvmStatic
    fun square(num: Double): Double {
        return num * num
    }

    @JvmStatic
    fun toInt(`object`: Any): Int {
        if (`object` is Number) {
            return `object`.toInt()
        }
        try {
            return `object`.toString().toInt()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0
    }

    @JvmStatic
    fun toFloat(`object`: Any): Float {
        if (`object` is Number) {
            return `object`.toFloat()
        }
        try {
            return `object`.toString().toFloat()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0f
    }

    @JvmStatic
    fun toDouble(`object`: Any): Double {
        if (`object` is Number) {
            return `object`.toDouble()
        }
        try {
            return `object`.toString().toDouble()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0.0
    }

    @JvmStatic
    fun toLong(`object`: Any): Long {
        if (`object` is Number) {
            return `object`.toLong()
        }
        try {
            return `object`.toString().toLong()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0
    }

    @JvmStatic
    fun toShort(`object`: Any): Short {
        if (`object` is Number) {
            return `object`.toShort()
        }
        try {
            return `object`.toString().toShort()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0
    }

    @JvmStatic
    fun toByte(`object`: Any): Byte {
        if (`object` is Number) {
            return `object`.toByte()
        }
        try {
            return `object`.toString().toByte()
        } catch (_: NumberFormatException) {
        } catch (_: NullPointerException) {
        }
        return 0
    }

    @JvmStatic
    fun isFinite(d: Double): Boolean {
        return abs(d) <= Double.MAX_VALUE
    }

    @JvmStatic
    fun isFinite(f: Float): Boolean {
        return abs(f) <= Float.MAX_VALUE
    }

    @JvmStatic
    fun checkFinite(d: Double, message: String) {
        require(isFinite(d)) { message }
    }

    @JvmStatic
    fun checkFinite(d: Float, message: String) {
        require(isFinite(d)) { message }
    }
}