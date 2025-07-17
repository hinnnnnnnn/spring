📌 NetworkClient.java 추가

package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient ⭐️implements InitializingBean, DisposableBean⭐️ { //⭐️추가⭐️

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //⭐️이 밑으로 다 추가⭐️
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}


/* 출력 결과
생성자 호출, url = null
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지
close: http://hello-spring.dev
 */
