package hello.core.singleton;

public class StatefulService {

    private int price;

    public void order(String name, int price){
//        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 문제. 다른 사용자가 수정한 값이 공유되기 때문
        // return price; // 로 문제를 해결한다.
    }

    public int getPrice(){
        return price;
    }
}
