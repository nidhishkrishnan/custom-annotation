package com.example.services;

import com.example.calculator.annotations.Calculate;
import com.example.calculator.processor.CalculatorProcessor;

import static com.example.calculator.domains.Method.ADD;
import static com.example.calculator.domains.Method.DIV;
import static com.example.calculator.domains.Method.MUL;
import static com.example.calculator.domains.Method.SUB;

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
