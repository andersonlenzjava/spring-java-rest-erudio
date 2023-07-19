package br.com.erudio.springjavaerudio.math;

public class SimpleMath {

    public Double sum(Double nuberOne, Double nuberTwo) {
        return nuberOne + nuberTwo;
    }

    public Double subtraction(Double nuberOne, Double nuberTwo) {
        return nuberOne - nuberTwo;
    }

    public Double multiplication(Double nuberOne, Double nuberTwo) {
        return nuberOne * nuberTwo;
    }

    public Double division(Double nuberOne, Double nuberTwo) {
        return nuberOne / nuberTwo;
    }

    public Double mean(Double nuberOne, Double nuberTwo) {
        return (nuberOne + nuberTwo) / 2;
    }

    public Double sqrt(Double nuber) {
        return Math.sqrt(nuber);
    }


}
