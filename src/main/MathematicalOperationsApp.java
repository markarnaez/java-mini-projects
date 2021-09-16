package main;
import java.util.Scanner;

public class MathematicalOperationsApp {
    public static void main(String[] args) throws Exception {
        System.out.println("This is a simple demonstration basic mathematical operations.");
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter first number:");
        Long num1 = obj.nextLong();
        System.out.println("Enter second number:");
        Long num2 = obj.nextLong();

        obj.close();

        System.out.println("\nOutput:");
        MathematicalOperators math = new MathematicalOperators(num1, num2);

        //Addition
        math.add();

        //Subtraction
        math.subtract();

        //Multiplication
        math.multiply();

        //Division
        math.divide();

    }
}

class MathematicalOperators {
    protected Long num1;
    protected Long num2;

    public MathematicalOperators(Long num1, Long num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public Long add() {
        Long result = num1 + num2;
        printResult(" + ", num1, num2, result);
        return result;
    }

    public Long subtract() {
        Long result = this.num1 - this.num2;
        printResult(" - ", num1, num2, result);
        return result;
    }

    public Long multiply() {
        Long result = this.num1 * this.num2;
        printResult(" x ", num1, num2, result);
        return result;
    }

    public Long divide() {
        Long result = 0L;
        try {
            result = this.num1 / this.num2;
            printResult(" / ", num1, num2, result);
            return result;

        } catch (Exception e) {
            if (num2 == 0) {
                System.out.println("Cannot divide by 0");
            } else {
                System.out.println(e.getMessage());
            }
            
            return null;
        }
    }

    public void printResult(String operation, Long num1, Long num2, Long result){
        System.out.println(num1 + operation + num2 + " = "  + result);
    }



}
