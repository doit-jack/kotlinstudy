package atomictest

import kotlin.math.abs
import kotlin.reflect.KClass

const val ERROR_TAG = "[Error]: "

private fun <L, R> test(
    actual: L, expected: R, checkEquals: Boolean = true, predicate: () -> Boolean
) {
    println("Actual: $actual")
    if (!predicate()) {
        println(ERROR_TAG)
        println("$actual " + (if (checkEquals) "!=" else "==") + " $expected")
    }
}

infix fun Any.eq(rval: String) {
    test(this, rval) { toString().trim() == rval.trimIndent() }
}

infix fun <T> T.eq(rval: T) {
    test(this, rval) { this == rval }
}

infix fun <T> T.neq(rval: T) {
    test(this, rval, checkEquals = false) { this != rval }
}

infix fun Double.eq(rval: Double) {
    test(this, rval) { abs(this - rval) < 0.000001 }
}


/**
 * Kclass: 클래스에 대한 런타임 정보를 제공하는 클래스
 * <*> : 와일드 카드 타입 매개변수 = 어떤 타입이든 허용
 * ?: nullable 타입
 * exceptionClass : Kclass 타입이며, 어떤 클래스의 런타임 정보든 받을 수 있으며 값이 없을 수 있음
 */
class CapturedException(
    private val exceptionClass: KClass<*>?,
    private val actualMessage: String
) {
    private val fullMessage: String
        get() {
            val className = exceptionClass?.simpleName ?: ""
            return className + actualMessage
        }

    infix fun eq(message: String) {
        fullMessage eq message
    }

    infix fun contains(parts: List<String>) {
        if (parts.any { it !in fullMessage }) {
            print(ERROR_TAG)
            println("Actual message: $fullMessage")
            println("Expected parts: $parts")
        }
    }

    override fun toString(): String = fullMessage
}

fun capture(f: () -> Unit): CapturedException =
    try {
        f()
        CapturedException(null, "$ERROR_TAG Expected an exception")
    } catch (e: Throwable) {
        CapturedException(e::class, (e.message?.let { ": $it" } ?: ""))
    }

/**
 * object: 예약어, 싱글톤 객체 정의
 * operator: 예약어, 특정 연산자에 대한 함수를 정의할 때 사용
 * ex. +, -, *, / 등의 연산자에 대해 정의된 함수를 오버로딩하고 싶을 때 operator 키워드 사용
 * operator fun invoke(obj: Any?)은 invoke 연산자에 대한 함수를 정의
 * 이렇게 하면 trace 객체가 함수처럼 호출될 수 있음 ex. trace(someObject)와 같이 호출
 * invoke 함수는 Kotlin에서 (), 즉 빈 괄호로 호출할 수 있는 특수한 함수, 해당 객체를 함수처럼 사용할 수 있도록 함
 * 이 코드에서는 invoke 함수가 trace 객체를 함수처럼 호출할 때 사용
 */
object trace {
    private val trc = mutableListOf<String>()
    operator fun invoke(obj: Any?) {
        trc += obj.toString()
    }

    infix fun eq(multiline: String) {
        val trace = trc.joinToString("\n")
        val expected = multiline.trimIndent().replace("\n", " ")
        test(trace, multiline) { trace.replace("\n", " ") == expected }
        trc.clear()
    }
}

