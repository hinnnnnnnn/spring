⛓️‍💥hello.core - order - order (클래스 생성)
📌order.java
  
package hello.core.order;

public class Order {

    private Long memberId; //주문한 회원의 ID를 저장하는 변수
    private String itemName; //주문한 상품명
    private int itemPrice; //상품의 원래 가격
    private int discountPrice; //적용된 할인 금액

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) { //주문 객체를 생성할 때, 필드를 초기화하는 생성자 ✅command + n : 생성자
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() { //할인된 최종 가격을 계산해 반환
        return itemPrice - discountPrice;
    }

    //command + n ; getter setter
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override // 객체 정보를 문자열로 보기 좋게 출력하기 위해 재정의한 메서드 ✅command + n : toString()
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
