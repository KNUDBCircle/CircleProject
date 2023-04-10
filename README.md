# readme.txt

목차

- 인코딩 설정
- [DBhelper.java](http://DBhelper.java) setting 설정필요
- table create 및 insert data  관련 sql 재제출
- ojdbc8.jar 경로 재 설정 필요
- 프로그램 사용 방법 소개

---

# 인코딩 설정

이클립스에서 인코딩을 utf-8로 설정해주어야 합니다.

---

# [DBhelper.java](http://DBhelper.java) 설정 필요

ConsoleProject 패키지 내의 DBHelper은 데이터베이스의 연결과 쿼리 실행을 담당하는 클래스로 singleton pattern을 적용하였습니다. **해당 클래스 내의 URL, USER_ID, USER_PASSWD를 수정하여 데이터베이스의 연결정보를 설정할 수 있습니다.**

---

# table create 및 insert data  관련 sql 재제출

changedDB.sql를 사용하여  다시 테이블 생성 및 data를 삽입시켜주세요.

꼭 하셔야됩니다.

---

# ojdbc8.jar 경로 재 설정 필요

경로를 다시 재설정 해주세요.

---

# 프로그램 사용 방법 소개

주의사항 : 선택지에서 선택하실때 숫자만 적어주시기 바랍니다 1 (O) 1번 (X)

### Step 1. 회원가입을 한다.

### Step 2. 로그인을 한다.

### Step 3.   1에서 3번 까지 고르기 (입력 값 : 1번 (x) 1 (0))

1. User Config : 회원정보 수정 기능

2.Go to circle page

when you click 1 to 4

1. create circle :동아리 생성하기
    
    프로그램에서 요구하는 대로 입력을 하게되면 동아리가 생성이된다. 생성된다는 말은 커뮤니티 생성과 같은 말이다. 생성된 동아리(4를 입력하세요) 에 들어가서 게시글도 올릴 수 있고 동아리 관련 스케줄도 조회 및 등록 가능하다.
    
2. [search](http://2.search) circle: 동아리 정보 조회 
    
    DB에 등록되어있는 모든 동아리 제목과 id가 나열된다.
    
    id 번호를 입력하면 해당 동아리에 대한 간략한 설명을 볼 수 있다.
    
3. submit circle: 생성된 동아리 목록 보고 동아리 신청하기
    
    가입하고 싶은 동아리 id를 입력하면 동아리 신청이 완료된다. 
    
4. enter circle: 자신이 속한 동아리 들어가기
    
    내가 속해 있는 동아리 목록들이 뜨고 그 중에 선택해서 들어 갈 수있다.
    
1. Log out: 로그아웃 

### <추가 설명>

1. **enter circle: 자신이 속한 동아리 들어가기**
    
    ID가 1인 파도타기 동아리를 예로 들어보자.
  
    
    ![2](https://user-images.githubusercontent.com/68631158/230806464-d4190f8f-bdd1-4ee9-9636-6ea77f36a129.png)

    
    자유게시판과 스케줄 정보가 보인다.
    
    현재 접속한 user는 manager가 아니므로  <tab 추가하기> 선택지가 안 보인다.
    
    ![1](https://user-images.githubusercontent.com/68631158/230806451-c71b9a65-fb91-42fa-8184-3dbd5fe60007.png)

    
    즉 manager만 TAB을 추가할 수 있다.
    
    - **원하는 TAB으로 이동시**
        
        
        ![3](https://user-images.githubusercontent.com/68631158/230806478-d0d19964-dde2-4025-95b0-341bc185fb4d.png)

        
        게시물 올리기(1) 
        
        - 프로그램이 요구하는 대로 입력값을 적어주면 게시글이 등록된다.
        
        게시물 조회하기(2) 
        
        - 해당 게시판에 적힌 모든 게시물이 보여진다. 게시물에 댓글도 달 수 있다.
        
        게시물 관리하기(3)  
        
        - 게시물 관리하기에 들어가면 내가 작성한 게시물에 대해 수정 및 삭제가 가능하다
    
    - **스케줄 정보 TAB으로 이동시**
        
        ![4](https://user-images.githubusercontent.com/68631158/230806355-6a1394f2-2526-4243-aa97-d51f50af36b9.png)
        
        스케줄 올리기(1)  
        
        - 동아리 회원 누구나 스케줄을 등록할 수 있다.
        
        스케줄 조회하기(2)
        
        - 동아리 내 생성된 스케줄 조회가 가능하다.
        - 자신이 작성한 스케줄이라면 **writer:me** 라고 뜬다.
        - 자신이 작성한 스케줄이 있다면 삭제 여부를 묻고 삭제 할 수 있다.
    
2. **관리자 계정으로 가입시 보다 많은 질의 가능**
    
    ID : admin으로 가입(PW: 상관없음)하면  다양한 질의가 가능하다.
    
    > 저희 서버로 접속했을경우 admin/admin
    20개의 쿼리에 접근할 수 있습니다
    > 
    
    phase2에서 작성한 쿼리들을 실제로 전부 사용했다.
    
    phase2의 쿼리에서 2-2 5-1 5-2 10-1 부분을 변경함
    
    **admin으로 접속하면 다른 기능은 사용하실 수 없습니다.**
    
    ### 아래는 admin으로 접속했을때 확인할 수 있는 쿼리의 예시 변수들입니다.
    
    1. user id = fviocs501
    2. category id = 4
    3. color = red
    4. category id = 1
    5. count = 2
    6. dep name = 경영학부
    7. dep name = 컴퓨터학부
    8. user id = xdpzkm748
    9. did = 50
    10. category_id = 1
    11. color = red
    12. dep name = 컴퓨터학부
    13. count = 8
    14. count = 5
    15. dep name = 컴퓨터학부
    16. cname = 창작뮤지컬 DDD
    17. date = 0716
    18. min = 20 max = 30
    19. cid = 5
    20. max person = 50
