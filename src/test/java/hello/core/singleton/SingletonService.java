package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 갖고 있는다
    // 자바 프로젝트가 실행될 때, static 영역에 객체를 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 객체 인스턴스가 필요할 때 이를 통해서만 조회한다. 항상 같은 인스턴스 사용.
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private 으로 두면 클래스 외부에서 new 로 인스턴스를 생성할 수 없다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
