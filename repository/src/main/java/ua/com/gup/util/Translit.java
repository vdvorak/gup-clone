package ua.com.gup.util;


public final class Translit {

    private static String cyr2lat(char ch, boolean isFirstLetter) {

        if (isFirstLetter) {
            switch (ch) {
                case 'є':
                    return "ye";
                case 'ї':
                    return "yi";
                case 'й':
                    return "y";
                case 'ю':
                    return "yu";
                case 'я':
                    return "ya";
            }
        }


        switch (ch) {
            case 'а':
                return "a";
            case 'б':
                return "b";
            case 'в':
                return "v";
            case 'г':
                return "h";
            case 'ґ':
                return "g";
            case 'д':
                return "d";
            case 'е':
                return "e";
            case 'є':
                return "ie";//ye -  в начале слова, ie - в других случаях
            case 'ё':
                return "e";
            case 'ж':
                return "zh";
            case 'з':
                return "z";
            case 'и':
                return "i";
//            case 'i': return "i"; - because we have "i" in english variant
            case 'ї':
                return "i";//yi -  в начале слова, i - в других случаях
            case 'й':
                return "i";//y -  в начале слова, i - в других случаях
            case 'к':
                return "k";
            case 'л':
                return "l";
            case 'м':
                return "m";
            case 'н':
                return "n";
            case 'о':
                return "o";
            case 'п':
                return "p";
            case 'р':
                return "r";
            case 'с':
                return "s";
            case 'т':
                return "t";
            case 'у':
                return "u";
            case 'ф':
                return "f";
            case 'х':
                return "kh";
            case 'ц':
                return "ts";
            case 'ч':
                return "ch";
            case 'ш':
                return "sh";
            case 'щ':
                return "shch";
            case 'ъ':
                return "";
            case 'ы':
                return "y";
            case 'ь':
                return "";
            case 'э':
                return "e";
            case 'ю':
                return "iu";//yu -  в начале слова, iu - в других случаях
            case 'я':
                return "ia";//ya -  в начале слова, ia - в других случаях
            case '1':
                return "1";
            case '2':
                return "2";
            case '3':
                return "3";
            case '4':
                return "4";
            case '5':
                return "5";
            case '6':
                return "6";
            case '7':
                return "7";
            case '8':
                return "8";
            case '9':
                return "9";
            case '0':
                return "0";
            case 'a':
                return "a";
            case 'b':
                return "b";
            case 'c':
                return "c";
            case 'd':
                return "d";
            case 'e':
                return "e";
            case 'f':
                return "f";
            case 'g':
                return "g";
            case 'h':
                return "h";
            case 'i':
                return "i";
            case 'j':
                return "j";
            case 'k':
                return "k";
            case 'l':
                return "l";
            case 'm':
                return "m";
            case 'n':
                return "n";
            case 'o':
                return "o";
            case 'p':
                return "p";
            case 'q':
                return "q";
            case 'r':
                return "r";
            case 's':
                return "s";
            case 't':
                return "t";
            case 'u':
                return "u";
            case 'v':
                return "v";
            case 'w':
                return "w";
            case 'x':
                return "x";
            case 'y':
                return "y";
            case 'z':
                return "z";
            case '-':
                return " ";
            case ' ':
                return " ";
            case '.':
                return " ";
            default:
                return "";
        }
    }


    public static String replacespaceToDash(String s) {
        return s.trim().replaceAll(" +", "-");
    }

    public static String makeTransliteration(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length() * 2);

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i - 1] == ' ') {
                sb.append(cyr2lat(arr[i], true));
            } else {
                sb.append(cyr2lat(arr[i], false));
            }
        }

        return replacespaceToDash(sb.toString());
    }
}
