package com.tfnico.examples.guava.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class OptionalTest {
    private static Map<String, String> stateCapitals;

    @Before
    public void setUp() {
        final Map<String, String> tempStatesToCapitals = Maps.newHashMap();
        tempStatesToCapitals.put("Alaska", "Juneau");
        tempStatesToCapitals.put("Arkansas", "Little Rock");
        tempStatesToCapitals.put("Colorado", "Denver");
        tempStatesToCapitals.put("Idaho", "Boise");
        tempStatesToCapitals.put("Utah", "Salt Lake City");
        tempStatesToCapitals.put("Wyoming", "Cheyenne");
        stateCapitals = Collections.unmodifiableMap(tempStatesToCapitals);
    }

    @Test
    public void testOptionalAbsent() {
        assertEquals(Optional.fromNullable(stateCapitals.get("alaska")),
                Optional.absent());
        assertTrue(Optional.fromNullable(stateCapitals.get("Alaska"))
                .isPresent());
    }

    @Test
    public void testOptionalOr() {
        Optional<String> optional = Optional.fromNullable(stateCapitals
                .get("alaska"));
        assertEquals(optional.or("empty"), "empty");

        optional = Optional.fromNullable(stateCapitals.get("Alaska"));
        assertEquals(optional.get(), "Juneau");
        assertEquals(optional.or("empty"), "Juneau");
        assertEquals(optional, Optional.of("Juneau"));
        assertEquals(optional.getClass().getName(),
                "com.google.common.base.Present");
    }
}
