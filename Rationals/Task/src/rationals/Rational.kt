package rationals

import java.math.BigInteger

fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b == BigInteger.ZERO) a else gcd(b, a.mod(b))
fun lcm(a: BigInteger, b: BigInteger) = (a.times(b)).div(gcd(a, b))

class Rational(val numerator: BigInteger, val denominator: BigInteger) {
    init {
        if (BigInteger.ZERO == denominator) {
            throw IllegalArgumentException("denominator must be not 0")
        }
    }

    operator fun plus(other: Rational): Rational {
        return if (denominator == other.denominator) {
            Rational(numerator + other.numerator, denominator)
        } else {
            val lcm = lcm(denominator, other.denominator)
            Rational(this.rebaseNumerator(lcm) + other.rebaseNumerator(lcm), lcm)
        }
    }

    private fun rebaseNumerator(lcm: BigInteger) = numerator * (lcm / denominator)

}


fun String.toRational(): Rational {

    if (Regex("""^\d+/\d+$""").matches(this)) {
        throw IllegalArgumentException("String must be formatted: 'numerator/denominator'");
    }

    val split = this.split('/')
    return Rational(split[0].toBigInteger(), split[1].toBigInteger())
}

infix fun BigInteger.divBy(denominator: BigInteger): Rational {
    return Rational(this, denominator)
}

infix fun Long.divBy(denominator: Long): Rational {
    return Rational(this.toBigInteger(), denominator.toBigInteger())
}

infix fun Int.divBy(denominator: Int): Rational {
    return Rational(this.toBigInteger(), denominator.toBigInteger())
}

fun main(args: Array<String>) {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}


