# Calculator Custom Java Annotation (@Calculate, @EnableCalculatorProcessor)

This example (calculator-annotation) implements the similar annotation mechanism of [Spring Data JPA Repositories](https://docs.spring.io/spring-data/jpa/docs/2.3.1.RELEASE/reference/html/#jpa.repositories).
That is, a classpath-scanner searches for specific interfaces and creates a proxy for all found interfaces. 
Every method should need two integer parameter and integer return type. This proxy can then be `@Autowired` or `DI` into your classes.

## Usage
For enabling this annotation, you simply need to add the following annotation to your configuration class:

```java
@Configuration 
@EnableCalculatorProcessor({"my.package"})
public class MyApplication {

  ...
} 
```

## Remark:
This will now scan `"my.package"` and all subpackages for interfaces that implement `com.example.calculator.processor.CalculatorProcessor` and create implementations for them.

```java
package my.package.service;

import com.example.calculator.processor.CalculatorProcessor;
import ...

public interface CalculatorService extends CalculatorProcessor {

   @Calculate(method = ADD)
   int addNumbers(int firstNumber, int secondNumber);

   @Calculate(method = SUB)
   int subtractNumbers(int firstNumber, int secondNumber);

   @Calculate(method = DIV)
   int divideNumbers(int firstNumber, int secondNumber);

   @Calculate(method = MUL)
   int multiplyNumbers(int firstNumber, int secondNumber);
}

```

A [`java.lang.reflect.Proxy`](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html) will be created for the 
found interface. Every method will be transformed into a calculate-processor-definition and will be compiled on startup.

- Only two integer parameters are supported
- Only integer return type is supported 
- `@Calculcate` annotation supports only four methods: `ADD` (Addition), `SUB` (Subtraction), `DIV` (Division), `MUL` (Multiplication).

## Example

**CalculatorApplication.java**

Add `@EnableCalculatorProcessor` for enabling the Calculator Annotation

```java
@SpringBootApplication
@EnableCalculatorProcessor("com.example.services")
public class CalculatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}
}
```

**CalculatorService.java**

Create an interface which extends `CalculatorProcessor` and add `@Calculate` to the an abstract method

```java
public interface CalculatorService extends CalculatorProcessor {

   @Calculate(method = ADD)
   int addNumbers(int firstNumber, int secondNumber);
}
```
**CalculatorController.java**

Inject or Autowire the `CalculatorService` and call `addNumbers` directly passing the values

```java
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public int addNumbers(int firstNumber, int secondNumber) {
        return calculatorService.addNumbers(firstNumber, secondNumber);
    }
}
```

## License

See [LICENSE](LICENSE) file.
