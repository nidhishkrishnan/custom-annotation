package com.example.calculator.factory;

import com.example.calculator.annotations.Calculate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class CalculatorProcessorProxy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		int result = 0;
		if (method.isAnnotationPresent(Calculate.class)) {
			Annotation annotation = method.getAnnotation(Calculate.class);
			Calculate calculate = (Calculate) annotation;

			int firstNumber = parseInt(valueOf(args[0]));
			int secondNumber = parseInt(valueOf(args[1]));
			switch (calculate.method()) {
				case ADD: result = firstNumber + secondNumber;
						  break;
				case DIV: result = firstNumber / secondNumber;
						  break;
				case MUL: result = firstNumber * secondNumber;
						  break;
				case SUB: result = firstNumber - secondNumber;
						  break;
				default:  break;
			}
		}
		return result;
	}

}
