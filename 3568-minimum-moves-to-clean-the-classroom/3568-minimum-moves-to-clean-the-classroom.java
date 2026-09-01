class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Give every litter a bit number
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (classroom[i].charAt(j) == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        // bestEnergy[row][col][mask]
        // Maximum energy with which we have reached this state
        int[][][] bestEnergy =
                new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // row, col, collectedMask, remainingEnergy, moves
        queue.offer(new int[]{
                startRow, startCol, 0, energy, 0
        });

        bestEnergy[startRow][startCol][0] = energy;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int mask = current[2];
            int remainingEnergy = current[3];
            int moves = current[4];

            // Cannot make another move
            if (remainingEnergy == 0) {
                continue;
            }

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                char cell = classroom[newRow].charAt(newCol);

                // Cannot pass obstacle
                if (cell == 'X') {
                    continue;
                }

                int newEnergy = remainingEnergy - 1;
                int newMask = mask;

                // Collect litter
                if (cell == 'L') {
                    int index = litterIndex[newRow][newCol];
                    newMask |= (1 << index);
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                // All litter collected
                if (newMask == allCollected) {
                    return moves + 1;
                }

                // Only continue if this state has better energy
                if (newEnergy > bestEnergy[newRow][newCol][newMask]) {

                    bestEnergy[newRow][newCol][newMask] = newEnergy;

                    queue.offer(new int[]{
                            newRow,
                            newCol,
                            newMask,
                            newEnergy,
                            moves + 1
                    });
                }
            }
        }

        return -1;
    }
}