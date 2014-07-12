/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aseprojali.main;

import java.util.Random;

/**
 *
 * @author Avew
 */
public class RandomKarakter {

    private final Random ran = new Random();

    private final char[] chr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'};

    public char nextChar() {
        int c = ran.nextInt(26); // 26 karakter

        return chr[c];
    }
}
