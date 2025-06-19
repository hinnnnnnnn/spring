⛓️‍💥hello.core - order - OrderService (인터페이스 생성)
📌OrderService.java

package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); //회원 ID, 상품명, 상품 가격을 입력받아 주문 객체를 생성하는 기능을 선언
}
