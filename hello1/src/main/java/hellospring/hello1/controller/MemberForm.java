package hellospring.hello1.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    웹 등록화면에서 데이터를 전달 받을 폼객체  -  회원 등록 컨트롤러
}
