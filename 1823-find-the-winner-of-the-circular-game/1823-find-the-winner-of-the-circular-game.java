class Solution {
    public int findTheWinner(int n, int k) {
//         ArrayList<Integer> list = new ArrayList<>();

//         for (int i = 1; i <= n; i++) {
//             list.add(i);
//         }

//         int index = 0;

//         while (list.size() > 1) {
//             index = (index + k - 1) % list.size();
//             list.remove(index);
//         }

//         return list.get(0);
//     }
// }


 int winner = 0;

        for (int i = 2; i <= n; i++) {
            winner = (winner + k) % i;
        }

        return winner + 1;
    }
}