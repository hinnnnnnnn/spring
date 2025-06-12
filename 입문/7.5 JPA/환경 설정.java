⚙️ build.gradle 파일에 JPA, h2 데이터베이스 관련 라이브러리 추가
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
//	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa' ⭐️여기⭐️
	runtimeOnly 'com.h2database:h2'
}




⚙️ 스프링 부트에 JPA 설정 추가
📌`resources - templates - application.properties`
spring.datasource.username=sa
spring.jpa.show.sql=true
spring.jpa.hibernate.ddl-auto=none
