package GenericFraction;

import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<>(n3, 0);
        } catch (ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

class ZeroDenominatorException extends Exception {
    public ZeroDenominatorException() {
        super("Denominator cannot be zero");
    }
}

class GenericFraction<T extends Number, U extends Number> {
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if (denominator.doubleValue() != 0) {
            this.numerator = numerator;
            this.denominator = denominator;
        } else throw new ZeroDenominatorException();
    }

    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
        return new GenericFraction<>(
                this.numerator.doubleValue() * gf.denominator.doubleValue() +
                        this.denominator.doubleValue() * gf.numerator.doubleValue(),
                this.denominator.doubleValue() * gf.denominator.doubleValue());
    }
    static double greaterCommonDivisor(double a, double b){
        if(b == 0) return a;
        if(a < b) return greaterCommonDivisor(a, b - a);
        else return greaterCommonDivisor(b, a - b);
    }
    double getGCD(){
        return greaterCommonDivisor(numerator.doubleValue(), denominator.doubleValue());
    }
    double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        double gcd = getGCD();
        return String.format("%.2f / %.2f", this.numerator.doubleValue() / gcd,
                this.denominator.doubleValue() / gcd);
    }
}
