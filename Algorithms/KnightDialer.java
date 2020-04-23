// 时间复杂度
// O(N*10), 因为针对拨号次数循环N次，然后每次步骤的时候需要拨10个号。

// 空间复杂度
// 总共只需要size是10的数组，所以空间复杂度为常数， O(1)
    
// http://www.noteanddata.com/leetcode-935-Knight%20Dialer-java-solution-note.html

 public int knightDialer(int n) {
    int[][] nextPosTable = {{4,6},{6,8},{7,9},{4,8},{3,9,0}, {},{1,7,0},{6,2}, {1,3},{4,2}};
    long[] countTable = new long[10];

    for(int i = 0; i < 10; ++i) {
        countTable[i] = 1;
    }
    
    for(int j = 1;j < n; ++j) {
        long[] nextCountTable = new long[10];
        for(int i = 0;i  < 10; ++i) {                                            
            int[] nextPosList = nextPosTable[i];
            for(int next : nextPosList) {
                nextCountTable[next] += countTable[i];
            }
        }            
        countTable = nextCountTable;
        for(int i = 0; i < 10; ++i) {
            countTable[i] %= 1_000_000_007;
        }
    }
    long sum = 0;
    for(int i = 0; i < 10; ++i ) {
        sum += countTable[i];
    }
    return (int)(sum%1_000_000_007);
}
