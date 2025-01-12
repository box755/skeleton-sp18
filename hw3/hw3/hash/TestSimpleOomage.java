package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        // i = 0, 0 < i < 255, i = 255.
        // i = 0, 155, 255
        // j = 0, 165, 255
        // k = 0, 135, 255

        SimpleOomage targetInstance1 = new SimpleOomage(0, 165, 255);
        SimpleOomage targetInstance2 = new SimpleOomage(255, 0, 135);
        SimpleOomage targetInstance3 = new SimpleOomage(255, 255, 0);

        for (int i = 0; i <= 255; i += 5) {
            for (int j = 0; j <= 255; j += 5) {
                for (int k = 0; k <= 255; k += 5) {
                    SimpleOomage testInstance = new SimpleOomage(i, j, k);

                    // 確保 hashCode 和 equals 的一致性
                    if (i == 0 && j == 165 && k == 255) {
                        assertEquals(targetInstance1.hashCode(), testInstance.hashCode());
                        assertEquals(targetInstance1, testInstance);
                    } else {
                        assertNotEquals(targetInstance1.hashCode(), testInstance.hashCode());
                        assertNotEquals(targetInstance1, testInstance);
                    }

                    if (i == 255 && j == 0 && k == 135) {
                        assertEquals(targetInstance2.hashCode(), testInstance.hashCode());
                        assertEquals(targetInstance2, testInstance);
                    } else {
                        assertNotEquals(targetInstance2.hashCode(), testInstance.hashCode());
                        assertNotEquals(targetInstance2, testInstance);
                    }

                    if (i == 255 && j == 255 && k == 0) {
                        assertEquals(targetInstance3.hashCode(), testInstance.hashCode());
                        assertEquals(targetInstance3, testInstance);
                    } else {
                        assertNotEquals(targetInstance3.hashCode(), testInstance.hashCode());
                        assertNotEquals(targetInstance3, testInstance);
                    }
                }
            }

        }

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
