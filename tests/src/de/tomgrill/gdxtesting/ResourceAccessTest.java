package de.tomgrill.gdxtesting;

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
@RunWith(GdxTestRunner.class)
public class ResourceAccessTest {

    @Test
    public void TestsHaveAssets(){
        assertTrue(Gdx.files.internal("../core/assets/image/Roboticon111.png").exists());
    }
}
