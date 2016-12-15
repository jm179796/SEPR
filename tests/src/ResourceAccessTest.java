import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.junit.Test;
import com.mygdx.game.*;
import org.junit.runner.RunWith;
import java.util.Arrays;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;


/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class ResourceAccessTest extends TesterFile{

    @Test
    public void TestsHaveAssets(){
        assertTrue(Gdx.files.internal("image/Roboticon111.png").exists());
    }
}
