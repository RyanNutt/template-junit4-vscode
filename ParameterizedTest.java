
import java.lang.reflect.Field;
import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@SuppressWarnings("unchecked")
public class ParameterizedTest {

    private String a;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public ParameterizedTest(String a) {
        this.a = a;
    }

    @Parameters
    public static List<String[]> params() {
        return Arrays.asList(new String[][] { { "Chicken" } });
    }

    @Test(timeout = 250)
    public void test() throws Exception {

    }

    private static List<String[]> toList(String words) {
        List<String[]> list = new ArrayList<>();
        String[] ray = words.split(",");
        for (String s : ray) {
            list.add(new String[] { s });
        }
        return list;
    }

    /**
     * Turns on stdOut output capture
     */
    private void captureOut() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Turns on stdErr output capture
     */
    private void captureErr() {
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Turns off stdOut capture and returns the contents that have been captured
     *
     * @return
     */
    private String getOut() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        return outContent.toString().replaceAll("\r", "");

    }

    /**
     * Turns off stdErr capture and returns the contents that have been captured
     *
     * @return
     */
    private String getErr() {
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        return errContent.toString().replaceAll("\r", "");
    }

    private <T> T getField(Object instance, String fieldName) throws Exception {
        Field fld = instance.getClass().getDeclaredField(fieldName);
        fld.setAccessible(true);
        return (T) fld.get(instance);
    }

    private void setField(Object instance, String fieldName, Object value) throws Exception {

        Field fld = instance.getClass().getDeclaredField(fieldName);
        fld.setAccessible(true);
        if (value instanceof Integer) {
            fld.setInt(instance, (int) value);
        } else {
            fld.set(instance, value);
        }

    }

}