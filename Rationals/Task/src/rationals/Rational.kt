package rationals

import java.math.BigInteger

fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b == BigInteger.ZERO) a else gcd(b, a.mod(b))
fun lcm(a: BigInteger, b: BigInteger) = (a.times(b)).div(gcd(a, b))
class Rational(
        num: BigInteger,
        den: BigInteger
):Comparable<Rational> {

    val numerator: BigInteger
    val denominator: BigInteger

    init {
        if (BigInteger.ZERO == den) {
            throw IllegalArgumentException("denominator must be greater than 0")
        }

        if(BigInteger.ZERO==num){
            numerator = BigInteger.ZERO
            denominator=den.abs()
        }else {
            val gcd = gcd(num.abs(), den.abs())

            denominator = den.div(gcd).abs()
            numerator = if ((num > BigInteger.ZERO && den> BigInteger.ZERO) || (num< BigInteger.ZERO && den< BigInteger.ZERO)) {
                num.div(gcd).abs()
            }else{
                num.div(gcd).abs().negate()

            }
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

    operator fun minus(other: Rational): Rational {
        return if (denominator == other.denominator) {
            Rational(numerator + other.numerator, denominator)
        } else {
            val lcm = lcm(denominator, other.denominator)
            Rational(this.rebaseNumerator(lcm) - other.rebaseNumerator(lcm), lcm)
        }
    }

    operator fun times(other: Rational): Rational {
        val n = this.numerator * other.numerator
        val d = this.denominator * other.denominator
        val gcd = gcd(n, d)
        return Rational(n.div(gcd), d.div(gcd))
    }

    operator fun div(other: Rational): Rational {
        val n = this.numerator * other.denominator
        val d = this.denominator * other.numerator
        val gcd = gcd(n, d)
        return Rational(n.div(gcd), d.div(gcd))
    }

    operator fun unaryMinus(): Rational {
        return Rational(this.numerator.unaryMinus(), this.denominator)
    }

    override operator fun compareTo(other: Rational): Int {
        return if (denominator == other.denominator) {
            numerator.compareTo(other.numerator)
        } else {
            val lcm = lcm(denominator, other.denominator)
            this.rebaseNumerator(lcm).compareTo(other.rebaseNumerator(lcm))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is Rational) {
            return false
        }

        if (this === other) {
            return true
        }

        if (this.denominator == other.denominator) {
            return this.numerator == other.numerator
        }

        val lcm = lcm(this.denominator, other.denominator)
        return this.rebaseNumerator(lcm) == other.rebaseNumerator(lcm)
    }



    override fun toString(): String {
        if(BigInteger.ONE == denominator) return numerator.toString()
        return numerator.toString() + "/" + denominator.toString()
    }
}


fun String.toRational(): Rational {

    if(this.contains("/")) {
        val split = this.split('/')
        return Rational(split[0].toBigInteger(), split[1].toBigInteger())
    }
    return Rational(BigInteger(this), BigInteger.ONE)
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



