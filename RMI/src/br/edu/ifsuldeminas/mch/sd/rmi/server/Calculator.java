package br.edu.ifsuldeminas.mch.sd.rmi.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

public class Calculator implements Operations {
    private List<String> lastOperations = new ArrayList<String>();

    public Number sum(Number x, Number y) {
        Number result = x.doubleValue() + y.doubleValue();
        log(x, "+", y, result);
        return result;
    }

    public Number sub(Number x, Number y) {
        Number result = x.doubleValue() - y.doubleValue();
        log(x, "-", y, result);
        return result;
    }

    public Number mul(Number x, Number y) {
        Number result = x.doubleValue() * y.doubleValue();
        log(x, "*", y, result);
        return result;
    }

    public Number div(Number x, Number y) {
        Number result = Double.NaN;
        if (y.doubleValue() != 0)
            result = x.doubleValue() / y.doubleValue();
        log(x, "/", y, result);
        return result;
    }

    public Number sqrt(Number x, Number index) {
        Number result = Math.pow(x.doubleValue(), 1.0 / index.doubleValue());
        log(x, "√[" + index + "]", null, result);
        return result;
    }

    public Number pow(Number x, Number y) {
        Number result = Math.pow(x.doubleValue(), y.doubleValue());
        log(x, "^", y, result);
        return result;
    }

    public Number mod(Number x, Number y) {
        Number result = x.doubleValue() % y.doubleValue();
        log(x, "%", y, result);
        return result;
    }

    public Number percent(Number x, Number y) {
        Number result = (x.doubleValue() * y.doubleValue()) / 100;
        log(x, "%", y, result);
        return result;
    }

    public Number factorial(Number x) {
        Number result = 1;
        try {
            int n = x.intValue();
            if (n < 0) throw new IllegalArgumentException("Fatorial de número negativo não é definido.");
            for (int i = 2; i <= n; i++) {
                result = result.doubleValue() * i;
            }
            log(x, "!", null, result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            log(x, "!", null, Double.NaN);
        }
        return result;
    }

    public List<String> lastOperations(int howMany) {
        if (lastOperations.size() < howMany)
            return lastOperations();
        return new ArrayList<String>(lastOperations.subList(lastOperations.size() - howMany, lastOperations.size()));
    }

    public List<String> lastOperations() {
        return lastOperations;
    }

    private void log(Number operatorOne, String operation, Number operatorTwo, Number result) {
        String formattedOperation = String.format("%s %s %s = %s", operatorOne != null ? operatorOne : "", operation,
                operatorTwo != null ? operatorTwo : "", result.toString());
        lastOperations.add(formattedOperation);
        System.out.printf("%s at %s\n", formattedOperation, new Date());
    }
}
