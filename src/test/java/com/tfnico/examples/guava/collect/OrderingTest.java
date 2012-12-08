package com.tfnico.examples.guava.collect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderingTest {
    List<Integer> nums;
    List<String> strings;
   private static Ordering<String> byLength;

    @Before
    public void setUp() {
        nums = Arrays.asList(2, 3, 5, null, 8, 9);
        strings = Arrays.asList("xxx", "Z", null, "22", "A", "33", "11");

        byLength = new Ordering<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Ints.compare(s1.length(), s2.length());
            }
        };
    }

    @Test(expected = NullPointerException.class)
    public void testNullValueList() {
        Collections.sort(nums, Ordering.natural());
    }

    @Test(expected = NullPointerException.class)
    public void testNullValueList2() {
        Collections.sort(nums, Ordering.natural().reverse());
    }

    @Test
    public void testNUllFirst() {
        Collections.sort(nums, Ordering.natural().nullsFirst());
        assertThat(nums, equalTo(Arrays.asList(null, 2, 3, 5, 8, 9)));
    }

    @Test
    public void testNUllLast() {
        Collections.sort(nums, Ordering.natural().nullsLast());
        assertThat(nums, equalTo(Arrays.asList(2, 3, 5, 8, 9, null)));
    }

    @SuppressWarnings("static-access")
    @Test(expected = NullPointerException.class)
    public void testStringByLength() {
        Collections.sort(strings, byLength.natural());
    }

    @SuppressWarnings("static-access")
    @Test
    public void testStringWithNull() {
        Collections.sort(strings, byLength.natural().nullsFirst());
        assertThat(strings,
                equalTo(Arrays.asList(null, "Z", "A", "22", "33", "11", "xxx")));
    }
}
