# 개선할 만한 부분

- 생성자에 들어있는 로직들을 별도 메서드로 분리해내기(생성자는 생성만 하고 끝마치도록)

# 구현 피드백

- PersistenceContext 의 상태값 중에 entitiesByKey, entitySnapshotsByKey 가 별도로 있는 이유 <- 필요할 때 도입
- merge/detach https://edu.nextstep.camp/s/JMAAwqKL/ls/99iS45z3

# 커밋 전에 확인할 것

* import 정리
* else 키워드를 줄여보기
* 테스트 코드는 충분한가?
* TODO 를 잘 마무리했는가?
* 생성자에 접근자를 잘 적용했나?
* 일관되게 작성했나? (비슷한 일을 하는 클래스가 여러 스타일로 작성됐는가?)
* 불필요하게 쪼개져있진 않은가?
* public/private modifier 기준 소트
* 객체가 할만한 일은 객체에게 맡기기
* 