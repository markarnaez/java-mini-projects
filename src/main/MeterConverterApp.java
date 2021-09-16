package main;
import java.util.Scanner;

public class MeterConverterApp {

    public static void main(String[] args) {
        System.out.println("This is a meter unit coverter.");
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter meter value:");
        Double input = obj.nextDouble();
        obj.close();

        System.out.println("\nOutput:");
        System.out.println(input + " m");
        MeterConverter converter = new  MeterToMillimeter();
        converter.convert(input);
        converter = new MeterToCentimeter();
        converter.convert(input);
        converter = new MeterToKilometer();
        converter.convert(input);
        converter = new MeterToInch();
        converter.convert(input);
        converter = new MeterToYard();
        converter.convert(input);

    }
}

class MeterConverter {
    String outUnit;
    Double conversionFactor;

    MeterConverter (String outUnit, Double factor){
        this.outUnit = outUnit;
        this.conversionFactor = factor;
    }
    public void convert(Double input){
        Double output = input * conversionFactor;
        System.out.println(" = " + output + outUnit);
    }

}

class MeterToKilometer extends MeterConverter{
    final static Double factor = 0.001D;
    final static String outUnit = " km";

    MeterToKilometer(){
        super(outUnit,factor);
    }
}

class MeterToCentimeter extends MeterConverter{
    final static Double factor = 100D;
    final static String outUnit = " cm";

    MeterToCentimeter(){
        super(outUnit,factor);
    }
}

class MeterToMillimeter extends MeterConverter{
    final static Double factor = 1000D;
    final static String outUnit = " mm";

    MeterToMillimeter(){
        super(outUnit,factor);
    }
}

class MeterToInch extends MeterConverter{
    final static Double factor = 39.37D;
    final static String outUnit = " inch";

    MeterToInch(){
        super(outUnit,factor);
    }
}

class MeterToYard extends MeterConverter{
    final static Double factor = 1.093D;
    final static String outUnit = " yard";

    MeterToYard(){
        super(outUnit,factor);
    }
}

