package com.example.controller;

import com.example.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/add")
    public int addNumbers(int firstNumber, int secondNumber) {
        return calculatorService.addNumbers(firstNumber, secondNumber);
    }

    @GetMapping("/subtract")
    public int subtractNumbers(int firstNumber, int secondNumber) {
        return calculatorService.subtractNumbers(firstNumber, secondNumber);
    }

    @GetMapping("/divide")
    public int divideNumbers(int firstNumber, int secondNumber) {
        return calculatorService.divideNumbers(firstNumber, secondNumber);
    }

    @GetMapping("/multiply")
    public int multiplyNumbers(int firstNumber, int secondNumber) {
        return calculatorService.multiplyNumbers(firstNumber, secondNumber);
    }
}
