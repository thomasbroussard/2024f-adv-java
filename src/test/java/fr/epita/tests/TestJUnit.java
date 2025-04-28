package fr.epita.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJUnit {


    @Test
    public void testFactorial(){
        //given
        Integer number = 5;

        //when
        Integer result = MathService.factorial(number);


        //then
        Assertions.assertEquals(120, result);
    }

}
