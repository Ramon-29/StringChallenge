import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringChallengeTest {



    @Test
    @DisplayName("Test Detect Wrong String")
    public void test_wrongString(){
        assertEquals(StringChallenge.Challenge("+++++* abcdehhhhhh"),"false");
    }

    @Test
    @DisplayName("Test Detect Right String")
    public void test_correctString(){
        assertEquals(StringChallenge.Challenge("$**+*{2} 9mmmrrrkbb"),"true");
    }

    @Test
    @DisplayName("Test Detect Wrong Pattern")
    public void test_wrongPattern(){
        Exception exception = assertThrows(Exception.class,()->{
            StringChallenge.Challenge("$**+*{2}");
        });

        String expectedMessage = "Patron no valido";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
