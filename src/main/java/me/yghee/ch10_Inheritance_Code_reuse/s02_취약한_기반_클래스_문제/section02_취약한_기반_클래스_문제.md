- 취약한 기반 클래스 문제
  - 상속이라는 문맥 안에서 결합도가 초래하는 문제점을 가리키는 용어.
  - 캡슐화를 약화시키고 결합도를 높인다. -> 자식 클래스가 부모 클래스의 구현 세부사항에 의존한다.
- 상속은 코드의 재사용을 위해 캡슐화의 장점을 희석시키고 구현에 대한 결합도를 높인다.

# 불필요한 인터페이스 상속 문제
- 인터페이스 설계는 제대로 쓰기엔 쉽게, 엉터리로 쓰기엔 어렵게 만들어야 한다.
- Stack과 Properties는 퍼블릭 인터페이스에 대한 고려 없이 단순히 코드 재사용을 위해 상속을 이용하는 잘못된 예시다.
- 객체지향의 핵심은 객체들의 협력이다. 단순히 코드를 재사용하기 위해 불필요한 오퍼레이션이 인터페이스에 스며들도록 방치해서는 안 된다.
- 상속을 위한 경고 2
  - 상속 받은 부모 클래스의 메서드가 자식 클래스의 내부 구조에 대한 규칙을 깨트릴 수 있다.

# 메서드 오버라이딩의 오작용 문제
- 상속을 위한 경고 3
  - 자식 클래스가 부모 클래스의 메서드를 오버라이딩할 경우 부모 클래스가 자신의 메서드를 사용하는 방법에 자식 클래스가 결합될 수 있다.

# 부모 클래스와 자식 클래스의 동시 수정 문제
```java
public class Song {
    private String singer;
    private String title;
    
    public Song(String singer, String title) {
        this.singer = singer;
        this.title = title;
    }
    
    public String getSinger() {
        return singer;
    }
    
    public String getTitle() {
        return title;
    }
}
```

```java
import java.util.ArrayList;
import java.util.List;

public class Playlist {
  public List<Song> tracks = new ArrayList<>();
  
  public void append(Song song) {
      getTracks().add(song);
  }
  
  public List<Song> getTracks() {
      return tracks;
  }
}
```

- 플레이리스트에서 노래를 삭제할 수 있는 기능이 추가
```java
public class PersonalPlaylist extends Playlist {
    public void remove(Song song) {
        getTracks().remove(song);
    }
}
```

- `Playlist`에서 노래의 목록뿐만 아니라 가수별 노래의 제목도 함께 관리해야 한다고 가정

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Playlist {
  public List<Song> tracks = new ArrayList<>();
  public Map<String, String> singers = new HashMap<>();

  public void append ( Song song ) {
    tracks.add( song );
    singers.put( song.getSinger(), song.getTitle() );
  }

  public List<Song> getTracks () {
    return tracks;
  }
  
  public Map<String, String> getSingers() {
      return singers;
  }
}
```
- `Playlist`의 요구사항 변경에 대한 수정에 의해 `PersonalPlaylist`도 함께 수정이 발생한다.
```java
public class PersonalPlaylist extends Playlist {
    public void remove(Song song) {
        getTracks().remove(song);
        getSingers().remove(song.getSinger());
    }
}
```

- 서브클래스는 올바른 기능을 위해 슈퍼클래스의 내부적인 구현에 의존한다.
- 상속을 위한 경고 4
  - 클래스를 상속하면 결합도로 인해 자식 클래스와 부모 클래스의 구현을 영원히 변경하지 않거나, 자식 클래스와 부모 클래스를 동시에 변경하거나 둘 중 하나를 선택할 수 밖에 없다.