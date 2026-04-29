package com.back;

public class Day0429 {
    void run(){
//        전사 a전사 = new 전사();
//        a전사.이름 = "카니";
//        a전사.나이 = 122;
//        a전사.a무기 = new 칼();
//        a전사.공격();
//        // 출력 : 22살 전사 카니(이)가 칼(으)로 공격합니다.
//
//        a전사.이름 = "초코";
//        a전사.나이 = 123;
//        a전사.a무기 = new 활();
//        a전사.공격();
//        // 출력 : 22살 전사 초코(이)가 활(으)로 공격합니다.
//
//        로봇오리 a로봇오리 = new 로봇오리();
//        a로봇오리.날다();
//        // 출력 : 저는 못 날아요 ㅠㅠ
//        a로봇오리.수영하다();
//        // 출력 : 물갈퀴로 수영합니다.
        로봇오리 a로봇오리 = new 로봇오리();
        a로봇오리.날다();
        // 출력 : 저는 못 날아요 ㅠㅠ
        a로봇오리.수영하다();
        // 출력 : 물갈퀴로 수영합니다.

        // 이런식으로 유연하게 객체의 행동양식을 런타임에 변경할 수 있다.
        a로봇오리.set비행아이템(new 날개비행아이템());
        a로봇오리.날다();
    }

}
class Worrier{
    String name;
    int age;
    Worrier(){
        this("noName"); // 내가 할 일을 남에게 미룸 여기서 this 는 Worrier(String name)
    }
    Worrier(String name){
        this.name = name;
        age = 18;
    }
}

class 전사 {
    String 이름;
    int 나이;
    무기 a무기;

    void 공격() {
        a무기.작동(이름, 나이);
    }
}

abstract class 무기 {
    String 무기명;
    void 작동(String 사용자_이름, int 사용자_나이){
        System.out.println(사용자_나이 + "살 전사 " + 사용자_이름 + "(이)가 " + 무기명 + "(으)로 공격합니다.");
    }
}

class 칼 extends 무기 {
    칼(){
        무기명 = "칼";
    }
}

class 활 extends 무기 {
    활() {
        무기명 = "활";
    }

}

//내가 작성한 오리
//class 오리 {
//    boolean canFly =true;
//    boolean canSwim = true;
//    void 날다() {
//        if (canFly) System.out.println("오리가 날개로 날아갑니다.");
//        else System.out.println("저는 못 날아요 ㅠㅠ");
//    }
//
//    void 수영하다() {
//        if(canSwim) System.out.println("물갈퀴로 수영합니다.");
//        else System.out.println("물에 둥둥 뜹니다.");
//    }
//}
//
//class 고무오리 extends 오리 {
//    고무오리(){
//        canFly = false;
//        canSwim =false;
//    }
//}
//
//class 로봇오리 extends 오리 {
//    로봇오리(){
//        canFly =false;
//    }
//}

//선생님이 작성한 오리

interface 아이템{
    public void 작동();
}
interface 비행아이템  extends 아이템{
}
class 못나는비행아이템 implements 비행아이템{
    @Override
    public void 작동(){
        System.out.println("저는 못 날아요ㅠㅠ");
    }
}
class 날개비행아이템 implements 비행아이템{
    @Override
    public void 작동(){
        System.out.println("날개로 날아갑니다.");
    }
}
interface 수영아이템 extends 아이템{
}
class 둥둥수영아이템 implements 수영아이템 {
    @Override
    public void 작동() {
        System.out.println("물에 둥둥 뜹니다.");
    }
}
class 물갈퀴수영아이템 implements 수영아이템{
    @Override
    public  void 작동(){
        System.out.println("물갈퀴로 수영합니다.");
    }
}

abstract class 오리 {
    protected 비행아이템 a비행아이템;
    protected 수영아이템 a수영아이템;
    public void set비행아이템(비행아이템 a비행아이템){
        this.a비행아이템 = a비행아이템;
    }
    public void set수영아이템(수영아이템 a수영아이템){
        this.a수영아이템 = a수영아이템;
    }
    public void 날다() {
        a비행아이템.작동();
    }

    public void 수영하다() {
        a수영아이템.작동();
    }

}

class 고무오리 extends 오리 {
    public 고무오리() {
        a비행아이템 = new 못나는비행아이템();
        a수영아이템 = new 둥둥수영아이템();
    }
}

class 로봇오리 extends 오리 {
    public 로봇오리() {
        a비행아이템 = new 못나는비행아이템();
        a수영아이템 = new 물갈퀴수영아이템();
    }
}