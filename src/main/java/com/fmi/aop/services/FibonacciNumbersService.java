package com.fmi.aop.services;

import com.fmi.aop.aspects.Countable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FibonacciNumbersService {

    @Autowired
    private FibonacciNumbersService fibonacciNumbersService;

    @Countable(maxDepth = 10)
    public void printAFibNumber(int fib, int fibb) {
        int temp = fib;
        fib = fib + fibb;
        fibb = temp;
        System.out.println(fib + ", ");
        fibonacciNumbersService.printAFibNumber(fib, fibb);
        return;
    }
}
