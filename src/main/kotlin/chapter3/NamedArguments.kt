package chapter3

import atomictest.eq

//fun color(red: Int, green: Int, blue: Int) = "($red, $green, $blue)"

/**
 * 이름 붙은 인자와 일반 인자를 섞어서 사용할 수 있지만, 인자 순서를 변경하면 나머지 인자도 모두 이름 붙은 인자를 사용해야 함
 */
fun main() {
    color(1, 2, 3) eq "(1, 2, 3)"
    color(green = 1, red = 2, blue = 3) eq "(2, 1, 3)" // 해당 CASE
    color(1, 2, blue = 3) eq "(1, 2, 3)"
    color(1, blue = 3) eq "(1, 0, 3)"
}

// default 인자를 추가하는 것도 가능
fun color(red: Int = 0, green: Int = 0, blue: Int = 0) = "($red, $green, $blue)"
