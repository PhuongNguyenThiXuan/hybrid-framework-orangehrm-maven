package javaSDET;

import com.google.common.collect.*;
import org.testng.annotations.Test;

//import static org.assertj.core.util.Lists.newArrayList;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

//import static org.assertj.core.api.Assertions.*;

public class Topic_15_Assert {
    @Test
    public void testHamcrest() {
        Biscuit theBiscuit = new Biscuit("Ginger");
        Biscuit myBiscuit = new Biscuit("Ginger");
        //assertThat(theBiscuit, equalTo(myBiscuit));
    }

    @Test
    public void testAssertJ() {
//        assertThat("The Lord of the Rings").isNotNull()
//                .startsWith("The")
//                .contains("Lord")
//                .endsWith("Rings");
    }

}
