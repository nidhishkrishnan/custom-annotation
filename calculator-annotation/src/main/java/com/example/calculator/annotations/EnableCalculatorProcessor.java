package com.example.calculator.annotations;

import com.example.calculator.scanner.CalculatorScanner;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * enable scanning of calculator services
 * 
 * @author Nidhish Krishnan (nidhishkrishnan@gmail.com)
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CalculatorScanner.class)
public @interface EnableCalculatorProcessor {

	
	/**
	 * short-notation for basePackages to be scanned for Calculator Services.
	 * Defaults to base-package of configuration class, if there is no package.
	 */
	String[] value() default {};
	
	/**
	 * type-safe notation for basePackages to be scanned. 
	 * Defaults to base-package of configuration class, if there is no package.
	 * @return
	 */
	String[] basePackage() default {};
}
