// most straightforward way, my own way

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length !=9) return false;
        boolean[] check;
        for(int i = 0; i< 9; i++) {
            check = new boolean[9];
            for(int j = 0; j< 9; j++) {
                if(board[i][j] != '.' && check[board[i][j] -'1']) return false;  
                else if(board[i][j] != '.') check[board[i][j] -'1'] = true;
            }
        }
        
        for(int i = 0; i< 9; i++) {
            check = new boolean[9];
            for(int j = 0; j< 9; j++) {
                if(board[j][i] != '.' && check[board[j][i] -'1']) return false;  
                else if(board[j][i] != '.') check[board[j][i] -'1'] = true;
            }
        }
        
        // first locate the lefttop point
        for(int i = 0; i< 9; i+=3) {
            for(int j = 0; j < 9; j+=3) {
                check = new boolean[9];
                for(int k = 0; k< 9; k++) {
                    if(board[i + k/3][j + k%3] != '.' && check[board[i + k/3][j + k%3] -'1']) return false;  
                    else if(board[i + k/3][j + k%3] != '.') check[board[i + k/3][j + k%3] -'1'] = true;
                }
            }
        }
        return true;
    }
}

// my own 

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length !=9) return false;
        for(int i = 0; i< 9; i++){
            boolean[] digits = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.' && digits[board[i][j] - '1']) return false;
                else if(board[i][j] != '.') digits[board[i][j]- '1'] = true;
            }
        }

        for(int i = 0; i< 9; i++){
            boolean[] digits = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[j][i] != '.' && digits[board[j][i]- '1']) return false;
                else if(board[j][i] != '.') digits[board[j][i]- '1'] = true;
            }
        }
        
        for(int i = 0; i< 9; i++){
            int x = i/3 *3;
            int y = i%3 * 3;
            boolean[] digits = new boolean[9];

            for(int m = 0; m< 3; m++){
                for(int n =0; n < 3; n++){
                    if(board[x+m][y+n] != '.' && digits[board[x+m][y+n]- '1']) return false;
                    else if(board[x+m][y+n] != '.') digits[board[x+m][y+n]- '1'] = true;
                }
            }
        }
        return true;
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20748171

public boolean isValidSudoku(char[][] board) {
    if(board==null || board.length!=9 || board[0].length!=9)
        return false;
    for(int i=0;i<9;i++)
    {
        boolean[] map = new boolean[9];
        for(int j=0;j<9;j++)
        {
            if(board[i][j] != '.')
            {
                if(map[(int)(board[i][j]-'1')])
                {
                    return false;
                }
                map[(int)(board[i][j]-'1')] = true;
            }
        }
    }
    for(int j=0;j<9;j++)
    {
        boolean[] map = new boolean[9];
        for(int i=0;i<9;i++)
        {
            if(board[i][j] != '.')
            {
                if(map[(int)(board[i][j]-'1')])
                {
                    return false;
                }
                map[(int)(board[i][j]-'1')] = true;
            }
        }
    }        
    for(int block=0;block<9;block++)
    {
        boolean[] map = new boolean[9];
        for(int i=block/3*3;i<block/3*3+3;i++)
        {
            for(int j=block%3*3;j<block%3*3+3;j++)
            {
                if(board[i][j] != '.')
                {
                   if(map[(int)(board[i][j]-'1')])
                   {
                      return false;
                   }
                   map[(int)(board[i][j]-'1')] = true;    
                }
            }
        }
    }
    return true;
}


// my own

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9  || board[0].length != 9) return false;
        for(int i = 0; i < 9; i++){
            boolean[] valid = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    if(valid[board[i][j] -'1']) {
                        return false;
                    } else {
                        valid[board[i][j] -'1'] = true;
                    }
                }
            }
        }
        for(int i = 0; i < 9; i++){
            boolean[] valid = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[j][i] != '.'){
                    if(valid[board[j][i] -'1']){
                        return false;
                    } else {
                        valid[board[j][i] -'1'] = true;
                    }
                }
            }
        }
        
        for(int block = 0 ; block < 9; block++){
            boolean[] valid = new boolean[9];
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board[block/3*3 + i][block%3*3 + j] != '.'){
                        if(valid[board[block/3*3 + i][block%3*3 + j] -'1']) {
                            return false;
                        } else {
                            valid[board[block/3*3 + i][block%3*3 + j] -'1'] = true;
                        }
                    }
                }
            }
        }
        return true;
        
    }
}
