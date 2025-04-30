package fr.epita.quiz.services;

public class MathService {
    public static Integer factorial(Integer number) {
        if (number < 0){
            throw new IllegalArgumentException("the input number was negative");
        }
        if (number == 0){
            return 1;
        }
        int result = 1;
        for (int i = number ; i > 1; i--){
            result = i * result;
            //result *= i;
        }
        return result;
    }
}
