package com.sonnguyen.common.util;

public class StrUtils {
    public static final String EMPTY = "";
    public static final String SLASH = "/";
    public static final String DOT = ".";
    public static final String UNDERLINE = "_";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String UNDERSCORE = "_";
    public static final String SLASHES = "-";
    public static final String DOLLAR = "$";
    public static final String BACKSLASH = "\\";

    public static String removeAccent(String str) {
        if (str == null) return null;
        String normalized = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static boolean isBlank(String str) {
        if (str == null) return true;
        str = str.replaceAll("\\s+", EMPTY);
        return str.length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static int calculateLevenshteinDistance(String s1, String s2) {
        // Xử lý trường hợp null
        if (s1 == null && s2 == null) {
            return 0; // Cả hai đều null, coi là giống nhau
        }
        if (s1 == null) {
            return s2.length(); // s1 null, khoảng cách là độ dài của s2 (cần chèn tất cả ký tự của s2)
        }
        if (s2 == null) {
            return s1.length(); // s2 null, khoảng cách là độ dài của s1 (cần xóa tất cả ký tự của s1)
        }

        // Khởi tạo ma trận dp với kích thước (s1.length() + 1) x (s2.length() + 1)
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // Điền hàng đầu tiên và cột đầu tiên
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        // Điền phần còn lại của ma trận
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1,      // Xóa
                                dp[i][j - 1] + 1),      // Chèn
                        dp[i - 1][j - 1] + cost);        // Thay thế
            }
        }

        // Giá trị cuối cùng ở góc dưới bên phải của ma trận là khoảng cách Levenshtein
        return dp[s1.length()][s2.length()];
    }
}
