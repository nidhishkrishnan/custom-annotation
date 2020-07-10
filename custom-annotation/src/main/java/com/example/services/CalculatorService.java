package com.example.services;

import com.example.calculator.annotations.Calculate;

import static com.example.calculator.domains.Method.*;

public interface CalculatorService {

   @Calculate(method = ADD)
   int addNumbers(int firstNumber, int secondNumber);

   @Calculate(method = SUB)
   int subtractNumbers(int firstNumber, int secondNumber);

   @Calculate(method = DIV)
   int divideNumbers(int firstNumber, int secondNumber);

   @Calculate(method = MUL)
   int multiplyNumbers(int firstNumber, int secondNumber);
}
