/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aseprojali.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Avew
 */
public class Matrix {

    char[][] matrix;
    char[][] matrixFull;
    String selectedWords;
    int ROWS;
    int COLS;

    public Matrix(String[] wordList, int ro, int co) {
        this.ROWS = ro;
        this.COLS = co;

        matrix = new char[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = '+';
            }
        }
        //sort
        Arrays.sort(wordList, new StringLengthComparator());
        //aggiungi
        for (String word : wordList) {
            if (add(word, matrix)) {
                this.selectedWords += " " + word;
            }
        }

        matrixFull = fill(matrix);
    }

    public class StringLengthComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return -1;
            } else if (o1.length() > o2.length()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("game:\n");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ret.append(matrixFull[i][j]);
            }
            ret.append("\n");
        }

        ret.append("\n");

        ret.append("words pos:\n");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ret.append(matrix[i][j]);
            }
            ret.append("\n");
        }

        return ret.toString();
    }

    public String getPuzzle() {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ret.append(matrixFull[i][j]);
            }
            ret.append("\n");
        }

        return ret.toString();
    }

    public String getSolution() {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ret.append(matrix[i][j]);
            }
            ret.append("\n");
        }

        return ret.toString();
    }

    private boolean add(String word, char[][] puzzle) {
        word = word.toUpperCase();
        Random r = new Random();
        int tent = r.nextInt(12 - word.length()) + 1;
        char[][] origPuzzle = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                origPuzzle[i][j] = puzzle[i][j];
            }
        }

        for (int tries = 0; tries < tent; tries++) {

            int ori = r.nextInt(2); // 0 = norm,   1 = back
            int dir = r.nextInt(14); // probabilità:   5= - 5= |  2= \  2= /

            if (ori == 1) {
                word = back(word);
            }
            if (dir <= 4) {
                dir = 0;
            } else {
                if (dir <= 9) {
                    dir = 1;
                } else {
                    if (dir <= 11) {
                        dir = 2;
                    } else {
                        dir = 3;
                    }
                }
            }

            int row = r.nextInt(ROWS - word.length());
            int col = r.nextInt(COLS - word.length());

            if (dir == 3) {
                row = ROWS - 1 - row;
            }

            int i;
            for (i = 0; i < word.length(); i++) {
                if (puzzle[row][col] == '+') {
                    puzzle[row][col] = word.charAt(i);

                    if (dir == 0) {
                        col++;
                    }
                    if (dir == 1) {
                        row++;
                    }
                    if (dir == 2) {
                        col++;
                        row++;
                    }
                    if (dir == 3) {
                        row--;
                        col++;
                    }
                } else {
                    for (int j = i; j > 0; j--) {
                        if (dir == 0) {
                            col--;
                        }
                        if (dir == 1) {
                            row--;
                        }
                        if (dir == 2) {
                            col--;
                            row--;
                        }
                        if (dir == 3) {
                            row++;
                            col--;
                        }
                        puzzle[row][col] = origPuzzle[row][col];
                    }
                    break;
                }
            }
            if (--i > 0) {
                return true;
            }
        }
        return false;
    }

    private String back(String in) {
        StringBuilder ret = new StringBuilder();
        for (int i = in.length() - 1; i >= 0; i--) {
            ret.append(in.charAt(i));
        }
        return ret.toString();
    }

    private char[][] fill(char[][] puzzle) {
        char[][] ret = new char[ROWS][COLS];
        RandomKarakter rchr = new RandomKarakter();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (puzzle[i][j] != '+') {
                    ret[i][j] = puzzle[i][j];
                } else {
                    ret[i][j] = rchr.nextChar();
                }
            }
        }

        return ret;
    }
}
