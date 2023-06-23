package com.InventoryManagement.algorithm;

import org.junit.Test;
import static org.junit.Assert.*;

public class IAlgoStringSearchingTest {
    @Test
    public void testNaiveStringSearching() {
        IAlgoStringSearching algo = new NaiveStringSearching();
        assertTrue(algo.search("hello world", "world"));
        assertTrue(algo.search("hello world", "hello"));
        assertFalse(algo.search("hello world", "hola"));
        assertFalse(algo.search("hello world", "worlds"));
    }

    @Test
    public void testKMPStringSearching() {
        IAlgoStringSearching algo = new KMPStringSearching();
        assertTrue(algo.search("hello world", "world"));
        assertTrue(algo.search("hello world", "hello"));
        assertFalse(algo.search("hello world", "hola"));
        assertFalse(algo.search("hello world", "worlds"));
    }
}