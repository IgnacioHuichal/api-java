package cl.lupoconecta.apijava.junit;

public class Calculator {
    private int result;

    public int add(int a, int b) {
        result = a + b;
        return result;
    }

    public int subtract(int a, int b) {
        result = a - b;
        return result;
    }
}
