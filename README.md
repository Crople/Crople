# Crople

### 👉 git clone 후 프로젝트 실행 방법
1. crople 데이터베이스 생성     
2. 소스트리에 프로젝트 추가, 자기 브랜치 만들고 체크아웃하기     
3. application.properties에서 user, password 변경      
4. Place, PlaceImage 클래스 빼고 나머지 클래스, 인터페이스 다 삭제하기      
5. 실행, place, place_image 테이블 생성 확인      
6. 소스트리에서 삭제했던 페이지 되돌리기      
7. 실행, review, user 테이블 생성 확인, localhost:8080/place/list 잘 뜨나 확인     
8. UserRepositoryTests에서 insertUsers메소드 실행     
9. user 테이블에 testuser1 ~ testuser100 있는지 확인      
10. localhost:8080/place/list에서 장소 등록, 리뷰 작성해보기     


### ❗️ git 주의사항
자신의 이름과 동일한 브랜치 생성하기    
자신의 브랜치에 체크아웃되어있는지 확인하기   
master, origin 브랜치는 절대 NEVER 건들이지 말기(~~대재앙이 보고싶지 않다면~~)   
git에 대해 잘 모르겠다면 대면회의때 같이 해볼 것      
커밋 메시지는 아래 규칙에 따를 것(가능한 짧고 간결하게)    


### 💻 Commit Message
feat: 새로운 기능에 대한 커밋 ex) feat: 로그인 기능 추가   
fix: 버그 수정에 대한 커밋   
build: 빌드 관련 파일 수정에 대한 커밋   
chore: 그 외 자잘한 수정에 대한 커밋    
ci: CI관련 설정 수정에 대한 커밋   
docs: 문서 수정에 대한 커밋    
style: 코드 스타일 혹은 포맷 등에 관한 커밋    
refactor: 코드 리팩토링에 대한 커밋    
test: 테스트 코드 수정에 대한 커밋    


### 📱 디자인
Figma :


### ⏰ 일정관리
Trello :
