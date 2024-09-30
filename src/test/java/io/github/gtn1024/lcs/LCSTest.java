package io.github.gtn1024.lcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LCSTest {

    @Test
    void lcs() {
        assertEquals(3, LCS.lcs("abcde", "ace"));
        assertEquals(3, LCS.lcs("abc", "abc"));
        assertEquals(0, LCS.lcs("abc", "def"));
    }
}