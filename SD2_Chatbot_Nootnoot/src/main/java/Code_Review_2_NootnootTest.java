import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class Code_Review_2_NootnootTest {
	
	@Test
	public void testWelcomeMessage() {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(byteArrayOutputStream));
	    Code_Review_2_Nootnoot.welcomeMessage();
	    String expected = "Hey there, Welcome to Weather ChatBot! \nMy name's Nootnoot, nice to meet you!\n";
	    assertEquals(expected, byteArrayOutputStream.toString());
	}
	
    @Test
    public void testInputLocation() {
        // test 1
        String expected = "Dublin";
        ByteArrayInputStream in1 = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in1);
        assertEquals(expected, Code_Review_2_Nootnoot.inputLocation());

        // test 2 - testing, weather the function arises any exception, when given an invalid input.
        expected = "Seattle";
	    String input = "Seattle";
	    System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
	    String actual = Code_Review_2_Nootnoot.inputLocation();
	    assertEquals(expected, actual);
    }
//	
//	@Test
//	public void testEndConversationQuit() {
//	    String input = "quit";
//	    String inputTwo = "q";
//	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(input));
//	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(inputTwo));
//	}
//
//
//    @Test
//    public void test_getWeatherCondition() {
//        String expected = "Rainy";
//        String actual = Code_Review_2_Nootnoot.getWeatherCondition(16);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void test_suggestClothing() {
//        String expected = "It's quite warm, you can wear shorts and a t-shirt.";
//        String actual = Code_Review_2_Nootnoot.suggestClothing("23.5");
//        assertEquals(expected, actual);
//    }

}

