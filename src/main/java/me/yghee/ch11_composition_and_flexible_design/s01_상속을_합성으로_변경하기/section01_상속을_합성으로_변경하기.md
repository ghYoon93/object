- 코드 재사용을 위해 상속을 남용했을 때 직면할 수 있는 세 가지 문제점
  1. 불필요한 인터페이스 상속 문제
  2. 메서드 오버라이딩 오작용 문제
  3. 부모 클래스와 자식 클래스의 동시 수정 문제

# 불필요한 인터페이스 상속 문제: java.util.Properties와 java.util.Stack

Properties에 hashtable 오퍼레이션 제거
```java
import java.util.Hashtable;

public class Properties {
  private Hashtable<String, String> properties = new Hashtable<>();

  public String setProperty(String key, String value) {
    return properties.put(key, value);
  }

  public String getProperty(String key) {
    return properties.get(key);
  }
}

```

```
Properties properties = new Properties();
properties.put( "Dennis Ritchie", 67 ); // 에러!
```

Stack에 Vector 오퍼레이션 제거
```java
public class Stack <E> {
    private Vector<E> elements = new Vector<>();
    public E push(E item) {
        elements.addElement( item );
        return item;
    }

    public E pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove( elements.size() - 1 );
    }
}
```

# 메서드 오버라이딩의 오작용 문제: InstrumentedHashSet

# 부모 클래스와 자식 클래스의 동시 수정 문제: PersonalPlaylist
