
import fr.epita.quiz.services.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJUnit {



    @Test
    public void testFactorialWithZero(){
        //given
        Integer number = 0;

        //when
        Integer result = fr.epita.quiz.services.MathService.factorial(number);


        //then
        Assertions.assertEquals(1, result);
    }
    @Test
    public void testFactorial(){
        //given
        Integer number = 5;

        //when
        Integer result = MathService.factorial(number);


        //then
        Assertions.assertEquals(120, result);
    }

    @Test
    public void testFactorialNegativeInput(){
        //given
        Integer number = -1;
        Exception e = null;
        //when
        try {
            Integer result = MathService.factorial(number);
        }catch (Exception encounteredException){
            e = encounteredException;
        }

        //then
        Assertions.assertNotNull(e);
        Assertions.assertTrue(e instanceof IllegalArgumentException);
    }

}
