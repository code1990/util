import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author issuser
 * @date 2019-06-26 16:56
 */
public class RegexUtilTest {

    @Test
    public void testIsNumber(){
//        assertEquals(2, 2);
//        assertTrue(true);
        System.out.println(Pattern.matches("^\\d{1,3}$", "1"));
        System.out.println(Pattern.matches("^\\d{1,3}$", "1234"));
        System.out.println(Pattern.matches("^\\d{1,3}$", "12"));
        System.out.println(Pattern.matches("^\\d{1,3}$", "134"));
//        System.out.println(Pattern.matches("^\\d$", "1234"));
        System.out.println(Pattern.matches("^[0-9]*$", "1234"));
    }
}
