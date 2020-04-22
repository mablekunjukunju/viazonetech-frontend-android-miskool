package com.zone.android.miskool;

import com.zone.android.miskool_Util.Constants;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /*@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    */
    @Test
    public void userName_Valid() throws Exception{

        assertThat(Constants.isUserNameValid(""),is(false));
    }
}