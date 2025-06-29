⛓️‍💥test - singleton - StatefulService
📌StatefulService.java (싱글톤 문제 해결)
  
  package hello.core.singleton;

public class StatefulService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
