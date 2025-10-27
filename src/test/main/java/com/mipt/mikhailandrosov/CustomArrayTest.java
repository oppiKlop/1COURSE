package com.mipt.mikhailandrosov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCustomArrayList {
    @Test
    void addGet() {
        CustomArrayList<String> arr = new CustomArrayList<>();
        arr.add("Test");
        assertEquals("Test", arr.get(0));
    }
    @Test
    void emptySize() {
        CustomArrayList<Integer> arr = new CustomArrayList<>();
        assertTrue(arr.isEmpty());
        assertEquals(0, arr.size());

        arr.add(1);
        assertFalse(arr.isEmpty());
        assertEquals(1, arr.size());
    }

    @Test
    void remove() {
        CustomArrayList<Integer> arr = new CustomArrayList<>();
        for (int i = 0; i < 5; ++i) {
            arr.add(i);
        }
        arr.remove(0);
        assertEquals(4, arr.size());
        for (int i = 1; i < 4; ++i) {
            assertEquals(i, arr.get(i-1));
        }
    }
    @Test
    void throwsNull() {
        CustomArrayList<Integer> arr = new CustomArrayList<>();
        assertThrows(NullPointerException.class, () -> arr.add(null));
    }
}
