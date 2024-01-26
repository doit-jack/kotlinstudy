# Kotlin Study

## Kotlin Type

- 코틀린은 primitive Type과 Wrapper Type을 따로 구분하지 않는다.
- 그럼 항상 객체로 표현하는가? -> Nope
- 코틀린의 타입은 컴파일 시 자동 변환되는데, 예를 들어 Int타입의 경우 Collection이나 Generic을 사용하는 경우 Wrapper로 변경, 아니면 primitive Type으로 변경
- cf) java의 경우 primitive type은 null을 가질 수 없다. 기본값이 있기 때문. bool = false, 대부분 = 0
- null이 될 수 있는 타입의 경우 자바에서는 표현할 수 없기 때문에, Wrapper Type으로 컴파일
- Generic 클래스의 경우에도 wrapper type 사용, ex) listOf(1, 2, 3)
- **JVM은 타입 파라미터로 원시 타입을 허용하지 않는다!**

### 숫자 변환

- 코틀린은 한 타입의 숫자를 다른 타입의 숫자로 자동 변환하지 않는다.

```kotlin
val i = 1
val l: Long = i // Error: type mismatch 컴파일 에러 발생
val l: Long = i.toLong()
```

### 최상위 타입: Any, Any?

- 자바에서 Object가 클래스 계층의 최상위 타입이듯, 코틀린에서는 Any가 모든 타입의 최상위 타입
- Any 타입은 컴파일 시에 java.lang.Object로 변환됨
- Any가 최상위 타입이더라도 null은 허용하지 않기 때문에 null이 들어가야 하는 곳에 Safe call을 사용한 Any?를 사용해야함

### Unit 타입 = Java void

### Nothing 타입: 이 함수는 정상적으로 끝나지 않는다.

- Nothing 타입은 이 함수가 정상적으로 끝나지 않는다는 걸 명시적으로 표현하는 타입
- nothing 함수는 엘비스 연산자의 우항에 사용해서 전제 조건을 검사하는데 주로 사용
- fail: Nothing 반환 함수

```kotlin
val storeList = product.store ?: fail("no Store Info)
```
