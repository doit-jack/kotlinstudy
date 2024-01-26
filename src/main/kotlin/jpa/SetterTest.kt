package jpa

import java.util.UUID

class SetterTest {
    val id: UUID = UUID.randomUUID()
    val name: String = ""
}

fun main() {
    val s = SetterTest()
    s.name = "a"
    println(s.name)
}
