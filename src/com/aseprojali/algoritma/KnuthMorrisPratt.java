package com.aseprojali.main;

/**
 *
 * @author Avew
 */
public class KnuthMorrisPratt {

    private char[] p, t; // pattern, text
    private int m, n; // panjang pattern, panjang text
    private String posisisama; // string of match positions
    private char[] kemiripan;// char array that shows matches
    private int[] b;

    public void search(String grid, String word) {
        n = grid.length();
        t = grid.toCharArray();
        posisisama = "";
        kemiripan = new char[n];
        for (int i = 0; i < n; i++) {
            kemiripan[i] = ' ';
        }
        m = word.length();
        p = word.toCharArray();
        b = new int[m + 1];
        KMPSearch();
    }

    //Algoritma KMP
    private void KMPSearch() {

        // Hitung pinggiran (m, p, b)
        int k = 0, q = -1;
        b[k] = q;
        while (k < m) {
            while (q >= 0 && p[k] != p[q]) {
                q = b[q];
            }
            k++;
            q++;
            b[k] = q;
        }
        // KMPSearch
        int o = 0, j = 0;
        while (o < n) {
            while (j >= 0 && t[o] != p[j]) {
                j = b[j];
            }
            o++;
            j++;
            if (j == m) // a match is found
            {
                posisisama += (o - m) + " ";
                kemiripan[(o - m)] = '^';
                j = b[j];
            }
        }
    }

    //kembalikan posisi sama.
    public String getPosisiSama() {
        return posisisama;
    }
}
