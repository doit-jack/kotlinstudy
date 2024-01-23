package chapter3

import atomictest.eq
import java.util.*

fun String.singleQuote() = "'$this'"
fun String.doubleQuote() = "\"$this\""


/**
 * this 키워드를 사용해 멤버 함수나 다른 확장에 접근 가능
 *
 * 클래스 내부에서 this를 생략하듯이 확장 함수 안에서도 this를 생략 할 수 있음
 *
 * But, 확장 함수는 확장 대상 타입의 public 원소에만 접근 가능.
 */
fun String.strangeQuote() = this.singleQuote().singleQuote()
fun String.tooManyQuote() = doubleQuote().doubleQuote()

fun eq(A: Objects, B: Objects) = A.equals(B)
fun main() {
    "Hi".singleQuote() eq  "'Hi'"
    "Double".doubleQuote() eq "\"Double\""

    "Hi".strangeQuote() eq "''Hi''"
    "Double".tooManyQuote() eq "\"\"Double\"\""
}