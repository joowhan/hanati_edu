package kr.co.kopo.classEx.api_18_04w1;

public class Member {
    public String id;
    public Member(String id){
        this.id = id;
    }
    @Override
    public boolean equals(Object object){
        if(object ==this){
            return true;
        }
        if(object instanceof Member){
            Member memeber = (Member) object;
            if(this.id.equals(memeber.id))
                return true;
        }
        return false;
    }
}
