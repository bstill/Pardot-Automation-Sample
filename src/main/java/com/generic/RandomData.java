package com.generic;

public class RandomData {
    public String getRandomStringAlphaNumeric(Integer len) {
        String[] Chars = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        String str = "";

        for (int i = 0; i < len; i++) {
            int rnd = new java.util.Random().nextInt(Chars.length);
            str = str + Chars[rnd];
        }

        return str;
    }

    public String getRandomStringAlpha(Integer len) {
        String[] Chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        String str = "";

        for (int i = 0; i < len; i++) {
            int rnd = new java.util.Random().nextInt(Chars.length);
            str = str + Chars[rnd];
        }

        return str;
    }

    public String getRandomStringAlphaUc(Integer len) {
        String[] Chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        String str = "";

        for (int i = 0; i < len; i++) {
            int rnd = new java.util.Random().nextInt(Chars.length);
            str = str + Chars[rnd];
        }

        return str;
    }

    public String getRandomStringAlphaLc(Integer len) {
        String[] Chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        String str = "";

        for (int i = 0; i < len; i++) {
            int rnd = new java.util.Random().nextInt(Chars.length);
            str = str + Chars[rnd];
        }

        return str;
    }

    public String getRandomStringNumeric(Integer len) {
        String[] Chars = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
        String str = "";

        for (int i = 0; i < len; i++) {
            int rnd = new java.util.Random().nextInt(Chars.length);
            str = str + Chars[rnd];
        }

        return str;
    }

    public Integer getRandomNumber(Integer num) {
        int rnd = new java.util.Random().nextInt(num);

        return rnd;
    }
}
