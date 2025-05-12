package fr.epita.quiz.tests;

import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSPR1 {

    @Autowired
    String str;


    @Test
    public void test(){
        System.out.println(str);
    }


}
