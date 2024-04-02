package kr.co.kopo.classEx.inheritance_12_04w1;

public class Parent {
    private String id;
    private String name;

    Parent(){}
    Parent(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
