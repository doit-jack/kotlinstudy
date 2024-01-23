package chapter3

import atomictest.eq

/**
 * # 함수의 시그니처 3 요소
 * 1) 함수 이름
 * 2) 파라미터 목록
 * 3) 반환 타입
 * - 함수 오버로딩 할 때는 함수 파라미터 리스트를 서로 다르게 만든다.(반환 타입은 오버로딩 대상 X)
 * - 클래스 안에 확장 함수와 시그니처가 같은 멤버 함수가 있으면, 멤버 함수를 우선시 함
 * - but, 확장 함수를 가지고 멤버 함수를 오버로딩 할 수는 있음
 */
class Overloading {
    fun f() = 0
    fun f(n: Int) = n + 2
}

fun main() {
    val o = Overloading()
    o.f() eq 0
    o.f(11) eq 13
}