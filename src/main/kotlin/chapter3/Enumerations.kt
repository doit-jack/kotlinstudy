@file:Suppress("ktlint:standard:no-wildcard-imports")

package chapter3

import atomictest.eq
import chapter3.Direction.*

/**
 * enum을 만들면 enum의 이름에 해당하는 문자열을 돌려주는 toString()이 자동으로 생성됨
 */
enum class Level {
    Overflow,
    High,
    Medium,
    Low,
    Empty,
}

/**
 * - enum은 인스턴스 개수가 미리 정혀ㅐ져 있고 클래스 본문 안에 이 모든 인스턴스가 나열되어 있는 특별한 종류의 클래스
 * - 멤버 함수나 멤버 프로퍼티를 이넘에 정의할 수도 있음
 */

enum class Direction(val notation: String) {
    North("N"),
    South("S"),
    East("E"),
    West("W"),
    ;

    val opposite: Direction
        get() =
            when (this) {
                North -> South
                South -> North
                West -> East
                East -> West
            }
}

fun main() {
    Level.Medium eq "Medium"
    North.notation eq "N"
    North.opposite eq South
}
