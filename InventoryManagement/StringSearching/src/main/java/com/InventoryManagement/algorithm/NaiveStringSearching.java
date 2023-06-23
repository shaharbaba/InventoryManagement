package com.InventoryManagement.algorithm;

public class NaiveStringSearching implements IAlgoStringSearching {
    public boolean search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return true; // match found
            }
        }

        return false; // no match found
    }
}