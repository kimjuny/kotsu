# Kotsu
A Spring Web MVC extension.

* Tired of modeling inputs for every request? 

* Don't want to remember every specific parameter validation restrictions? 

* Looking for a highly flexible & reusable & customizable replacement for Spring/Hibernate Validator? 

This little Spring extention may bring you a lot help!

### Kotsu Components:

1. Extractor: Automatically extracts parameter according to "content-type".

2. Validator: Validates input parameter by simple strategies.

3. Assembler: Assemble and return the result.

Each components are reusable & customizable.

### Basic Usage:

```java
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(value = "",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void api(@Absent("PAGE_IDX") Optional<Integer> pageIdx,
                    @Absent @VInteger(min = 0, max = 10) Optional<Integer> pageMax,
                    @Required("MODE") Long mode,
                    @Required @VString(min = 1, max = 32, regex = "*") String userName) {

    }

}
```

### Requirements:

1. Java 8
2. Spring Web MVC or Spring Boot
3. Configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="ek.kotsu" />
    
    <mvc:annotation-driven>
        <mvc:argument-resolvers>
            <bean class="ek.kotsu.resolver.AbsentParamResolver" />
            <bean class="ek.kotsu.resolver.RequiredParamResolver" />
        </mvc:argument-resolvers>
    </mvc:annotation-driven>

</beans>
```

### Wiki

For a better knowledge of Kotsu usage and requirements please refer to [Kotsu-Wiki](https://github.com/ronankim/kotsu/wiki).