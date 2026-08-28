class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int halfLength = n / 2;

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                middle = (char) ('a' + i);
            }

            count[i] /= 2;
        }

        if (oddCount > 1) {
            return "";
        }

        StringBuilder half = new StringBuilder();

        int matched = 0;

        while (matched < halfLength) {

            int index = target.charAt(matched) - 'a';

            if (count[index] == 0) {
                break;
            }

            half.append(target.charAt(matched));
            count[index]--;

            matched++;
        }

        if (matched == halfLength) {

            String palindrome = buildPalindrome(
                half.toString(),
                middle,
                oddCount == 1
            );

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        int pos = Math.min(matched, halfLength - 1);

        while (pos >= 0) {

            if (pos < half.length()) {

                char ch = half.charAt(pos);

                count[ch - 'a']++;

                half.setLength(pos);
            }

            int targetChar = target.charAt(pos) - 'a';

            for (int j = targetChar + 1; j < 26; j++) {

                if (count[j] > 0) {

                    StringBuilder answerHalf = new StringBuilder(half);

                    answerHalf.append((char) ('a' + j));

                    count[j]--;

                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            answerHalf.append((char) ('a' + k));
                            count[k]--;
                        }
                    }

                    return buildPalindrome(
                        answerHalf.toString(),
                        middle,
                        oddCount == 1
                    );
                }
            }

            pos--;
        }

        return "";
    }


    private String buildPalindrome(
            String half,
            char middle,
            boolean hasMiddle
    ) {

        StringBuilder result = new StringBuilder();

        result.append(half);

        if (hasMiddle) {
            result.append(middle);
        }

        result.append(
            new StringBuilder(half).reverse()
        );

        return result.toString();
    }
}