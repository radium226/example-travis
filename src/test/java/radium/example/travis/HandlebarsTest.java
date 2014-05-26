package radium.example.travis;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandlebarsTest {
    
    private String expectedHTML;
    
    public HandlebarsTest() {
        super();
    }
    
    @Before
    public void setUp() throws IOException {
        expectedHTML = Resources.toString(Resources.getResource("testHandlebars.html"), Charsets.UTF_8);
    }
    
    @After
    public void tearDown() {
    
    }

    @Test
    public void testHandlebars() throws IOException {
        PrintStream oldSystemOutput = System.out;
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        TryHandlebar.main(new String[0]);
        outputStream.flush();
        System.setOut(oldSystemOutput);
        
        String text = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertEquals(text.trim(), expectedHTML);
    }
}
