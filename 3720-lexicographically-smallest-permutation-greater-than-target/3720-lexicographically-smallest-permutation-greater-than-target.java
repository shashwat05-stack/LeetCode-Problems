class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        int n = s.length();
        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < n) {

            int current = target.charAt(i) - 'a';

            if (count[current] > 0) {
                result.append(target.charAt(i));
                count[current]--;
                i++;
            } else {
                break;
            }
        }

        for (int pos = i; pos >= 0; pos--) {
            if (pos < result.length()) {
                char ch = result.charAt(pos);
                count[ch - 'a']++;
                result.setLength(pos);
            }
            if (pos < n) {
                int start = target.charAt(pos) - 'a' + 1;

                for (int j = start; j < 26; j++) {
                    if (count[j] > 0) {

                        result.append((char) ('a' + j));
                        count[j]--;

                        for (int k = 0; k < 26; k++) {
                            while (count[k] > 0) {
                                result.append((char) ('a' + k));
                                count[k]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }
        }

        return "";
    }
}