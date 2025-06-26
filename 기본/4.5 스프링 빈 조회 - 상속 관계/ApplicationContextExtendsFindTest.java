⛓️‍💥test - beanfind - ApplicationContextExtendsFindTest
📌ApplicationContextExtendsFindTest.java

package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }
    
    @Configuration
    static class TestConfig {
        
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}

/* ⭐️ 출력 결과 ⭐️
key = rateDiscountPolicyvalue = hello.core.discount.RateDiscountPolicy@1951b871
key = fixDiscountPolicyvalue = hello.core.discount.FixDiscountPolicy@5c18016b
 */


/* ⭐️ Object 출력 결과 ⭐️
key = org.springframework.context.annotation.internalConfigurationAnnotationProcessorvalue = org.springframework.context.annotation.ConfigurationClassPostProcessor@61f05988
key = org.springframework.context.annotation.internalAutowiredAnnotationProcessorvalue = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@7ca33c24
key = org.springframework.context.annotation.internalCommonAnnotationProcessorvalue = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@fade1fc
key = org.springframework.context.event.internalEventListenerProcessorvalue = org.springframework.context.event.EventListenerMethodProcessor@67c2e933
key = org.springframework.context.event.internalEventListenerFactoryvalue = org.springframework.context.event.DefaultEventListenerFactory@41dd05a
key = applicationContextExtendsFindTest.TestConfigvalue = hello.core.beanfind.ApplicationContextExtendsFindTest$TestConfig$$SpringCGLIB$$0@613a8ee1
key = rateDiscountPolicyvalue = hello.core.discount.RateDiscountPolicy@178213b
key = fixDiscountPolicyvalue = hello.core.discount.FixDiscountPolicy@7103cb56
key = environmentvalue = StandardEnvironment {activeProfiles=[], defaultProfiles=[default], propertySources=[PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]}
key = systemPropertiesvalue = {java.specification.version=17, sun.jnu.encoding=UTF-8, java.class.path=/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar:/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit5-rt.jar:/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit-rt.jar:/Users/hiin/study/core/out/test/classes:/Users/hiin/study/core/out/production/classes:/Users/hiin/study/core/out/production/resources:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-test/3.5.0/d973979fde1282d9368d086deb7d82997079d61b/spring-boot-starter-test-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter/3.5.0/f8b02201e89d294514420a586624aa7338d61f4f/spring-boot-starter-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-test-autoconfigure/3.5.0/7316f805ca7db725b54e3d3902ca0b6ebafc50bc/spring-boot-test-autoconfigure-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-test/3.5.0/1c5c19ae32d1c4b8532dcab8a8e92094ef864bda/spring-boot-test-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-test/6.2.7/77af0b9d6b218d149f77e63ff5ec6349b5d8abdd/spring-test-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/6.2.7/525a228b8b4323568b7aa9e49c1e1df27838adf1/spring-core-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/com.jayway.jsonpath/json-path/2.9.0/37fe2217f577b0b68b18e62c4d17a8858ecf9b69/json-path-2.9.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/jakarta.xml.bind/jakarta.xml.bind-api/4.0.2/6cd5a999b834b63238005b7144136379dc36cad2/jakarta.xml.bind-api-4.0.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/net.minidev/json-smart/2.5.2/95d166b18f95907be0f46cdb9e1c0695eed03387/json-smart-2.5.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.assertj/assertj-core/3.27.3/31f5d58a202bd5df4993fb10fa2cffd610c20d6f/assertj-core-3.27.3.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.awaitility/awaitility/4.3.0/f0c0bc1e404e500bab3f498b922eaedeae1c0207/awaitility-4.3.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.hamcrest/hamcrest/3.0/8fd9b78a8e6a6510a078a9e30e9e86a6035cfaf7/hamcrest-3.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter/5.12.2/64d3ec64ae06517bc14b7faaaec7c996b0d05bea/junit-jupiter-5.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.mockito/mockito-junit-jupiter/5.17.0/4399e19e3fb5b55230027583323e4c883d5da07d/mockito-junit-jupiter-5.17.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.mockito/mockito-core/5.17.0/f5fe5a2f94eb65f4f83ca0607bfe13ec0d9c6f3b/mockito-core-5.17.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.skyscreamer/jsonassert/1.5.3/aaa43e0823d2a0e106e8754d6a9c4ab24e05e9bc/jsonassert-1.5.3.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.xmlunit/xmlunit-core/2.10.1/292f3b22389f67bd7ab541783561672e71603eaf/xmlunit-core-2.10.1.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/3.5.0/a7f8ba40022dd75f20ac0eafe117437a1a7f3935/spring-boot-autoconfigure-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/3.5.0/ec86afc460265a0219bfafe187acb6c596ac2de1/spring-boot-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-logging/3.5.0/7db0461d8895f00a9841bf5558501fe90e7eba4c/spring-boot-starter-logging-3.5.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/jakarta.annotation/jakarta.annotation-api/2.1.1/48b9bda22b091b1f48b13af03fe36db3be6e1ae3/jakarta.annotation-api-2.1.1.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/2.4/e0666b825b796f85521f02360e77f4c92c5a7a07/snakeyaml-2.4.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/6.2.7/16b5f3446a6fcf097f4910c935086d08db657653/spring-jcl-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/jakarta.activation/jakarta.activation-api/2.1.3/fa165bd70cda600368eee31555222776a46b881f/jakarta.activation-api-2.1.3.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/net.minidev/accessors-smart/2.5.2/ce16fd235cfee48e67eda33e684423bba09f7d07/accessors-smart-2.5.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/net.bytebuddy/byte-buddy/1.17.5/88450f120903b7e72470462cdbd2b75a3842223c/byte-buddy-1.17.5.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-params/5.12.2/ff1434a66d1fb84c2c709cde2e2d56d5c8f4fad3/junit-jupiter-params-5.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.12.2/6de3a3256c5d90f4a439edcb6c2e8dc5180907b0/junit-jupiter-api-5.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/net.bytebuddy/byte-buddy-agent/1.17.5/58f9507f5f28d1d3e7adfb4ec2fe37c29d332c9b/byte-buddy-agent-1.17.5.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/com.vaadin.external.google/android-json/0.0.20131108.vaadin1/fa26d351fe62a6a17f5cda1287c1c6110dec413f/android-json-0.0.20131108.vaadin1.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/6.2.7/e0f3850bba7ff52016429e8d2fa39b5107f1fde6/spring-context-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.5.18/fc371f3fc97a639de2d67947cffb7518ec5e3d40/logback-classic-1.5.18.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.24.3/da1143e2a2531ee1c2d90baa98eb50a28a39d5a7/log4j-to-slf4j-2.24.3.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/2.0.17/524cb6ccc2b68a57604750e1ab8b13b5a786a6aa/jul-to-slf4j-2.0.17.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.ow2.asm/asm/9.7.1/f0ed132a49244b042cd0e15702ab9f2ce3cc8436/asm-9.7.1.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.apiguardian/apiguardian-api/1.1.2/a231e0d844d2721b0fa1b238006d15c6ded6842a/apiguardian-api-1.1.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.platform/junit-platform-commons/1.12.2/b89e88a89ee009937374a4c96912e007b34c8e35/junit-platform-commons-1.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.opentest4j/opentest4j/1.3.0/152ea56b3a72f655d4fd677fc0ef2596c3dd5e6e/opentest4j-1.3.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/6.2.7/3df3ee9217cb62e65382718351e56f1eeb76e74c/spring-aop-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/6.2.7/64119950b73943f67196705e43acddd7bf714c45/spring-beans-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/6.2.7/fa525916c85df246fcb70c771d039523dd6f12d9/spring-expression-6.2.7.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-observation/1.15.0/1bcd42cb00aead4d441038fa86b0e6b41922e254/micrometer-observation-1.15.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.5.18/6c0375624f6f36b4e089e2488ba21334a11ef13f/logback-core-1.5.18.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/2.0.17/d9e58ac9c7779ba3bf8142aff6c830617a7fe60f/slf4j-api-2.0.17.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.24.3/b02c125db8b6d295adf72ae6e71af5d83bce2370/log4j-api-2.24.3.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-commons/1.15.0/a796f0418b2e0fe42988a1ca6e240d8b3fb5e954/micrometer-commons-1.15.0.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.platform/junit-platform-launcher/1.12.2/79d49ad13017c67a8bf76e10ba5ac8937d811772/junit-platform-launcher-1.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.platform/junit-platform-engine/1.12.2/8519ece93f91b8115705d36be2c36d49808935cd/junit-platform-engine-1.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-engine/5.12.2/b8df7575b8cd3a94dbe27b481d1cd52c57864559/junit-jupiter-engine-5.12.2.jar:/Users/hiin/.gradle/caches/modules-2/files-2.1/org.objenesis/objenesis/3.3/1049c09f1de4331e8193e579448d0916d75b7631/objenesis-3.3.jar, java.vm.vendor=Oracle Corporation, kotlinx.coroutines.debug.enable.mutable.state.flows.stack.trace=true, sun.arch.data.model=64, idea.test.cyclic.buffer.size=1048576, java.vendor.url=https://java.oracle.com/, user.timezone=Asia/Seoul, java.vm.specification.version=17, os.name=Mac OS X, sun.java.launcher=SUN_STANDARD, user.country=KR, sun.boot.library.path=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/lib, sun.java.command=com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 hello.core.beanfind.ApplicationContextExtendsFindTest, http.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, jdk.debug=release, sun.cpu.endian=little, user.home=/Users/hiin, user.language=ko, java.specification.vendor=Oracle Corporation, java.version.date=2025-04-15, java.home=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home, file.separator=/, java.vm.compressedOopsMode=Zero based, line.separator=
, java.vm.specification.vendor=Oracle Corporation, java.specification.name=Java Platform API Specification, intellij.debug.agent=true, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, ftp.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, java.runtime.version=17.0.15+9-LTS-241, user.name=hiin, path.separator=:, kotlinx.coroutines.debug.enable.creation.stack.trace=false, os.version=15.5, java.runtime.name=Java(TM) SE Runtime Environment, file.encoding=UTF-8, java.vm.name=Java HotSpot(TM) 64-Bit Server VM, java.vendor.url.bug=https://bugreport.java.com/bugreport/, java.io.tmpdir=/var/folders/v3/l5vy0885067cxc5v89j3ltym0000gn/T/, java.version=17.0.15, jboss.modules.system.pkgs=com.intellij.rt, user.dir=/Users/hiin/study/core, os.arch=aarch64, java.vm.specification.name=Java Virtual Machine Specification, native.encoding=UTF-8, kotlinx.coroutines.debug.enable.flows.stack.trace=true, java.library.path=/Users/hiin/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:., java.vm.info=mixed mode, sharing, java.vendor=Oracle Corporation, java.vm.version=17.0.15+9-LTS-241, java.specification.maintenance.version=1, sun.io.unicode.encoding=UnicodeBig, debugger.agent.enable.coroutines=true, socksNonProxyHosts=local|*.local|169.254/16|*.169.254/16, java.class.version=61.0}
key = systemEnvironmentvalue = {PATH=/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin:, __CFBundleIdentifier=com.jetbrains.intellij.ce, SHELL=/bin/zsh, JAVA_MAIN_CLASS_1230=com.intellij.rt.junit.JUnitStarter, JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home, OLDPWD=/, USER=hiin, TMPDIR=/var/folders/v3/l5vy0885067cxc5v89j3ltym0000gn/T/, COMMAND_MODE=unix2003, SSH_AUTH_SOCK=/private/tmp/com.apple.launchd.7fBPe3t20V/Listeners, XPC_FLAGS=0x0, setJava17=export JAVA_HOME=$(/usr/libexec/java_home -v 17), __CF_USER_TEXT_ENCODING=0x1F5:0x3:0x33, setJava11=export JAVA_HOME=$(/usr/libexec/java_home -v 11), alias=, LOGNAME=hiin, LC_CTYPE=ko_KR.UTF-8, PWD=/Users/hiin/study/core, XPC_SERVICE_NAME=application.com.jetbrains.intellij.ce.588064.588918, HOME=/Users/hiin}
key = applicationStartupvalue = org.springframework.core.metrics.DefaultApplicationStartup@31000e60
key = org.springframework.context.annotation.ConfigurationClassPostProcessor.importRegistryvalue = []
key = messageSourcevalue = Empty MessageSource
key = applicationEventMulticastervalue = org.springframework.context.event.SimpleApplicationEventMulticaster@1d470d0
key = lifecycleProcessorvalue = org.springframework.context.support.DefaultLifecycleProcessor@24d09c1
key = rateDiscountPolicyvalue = hello.core.discount.RateDiscountPolicy@aeab9a1
key = fixDiscountPolicyvalue = hello.core.discount.FixDiscountPolicy@40f70521
 */
