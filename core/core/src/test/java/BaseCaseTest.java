
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;



public class BaseCaseTest {
	@Test
    public void trueTest() {
        assertThat("1234",is("1234"));
    }

}
