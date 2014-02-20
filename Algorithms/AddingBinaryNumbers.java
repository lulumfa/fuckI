// The two input Strings, containing the binary representation of the two values:


String input0 = "1010";
String input1 = "10";

if Allowed

// Use as radix 2 because it's binary    
int number0 = Integer.parseInt(input0, 2);
int number1 = Integer.parseInt(input1, 2);

int sum = number0 + number1;

// extra step
Integer.toBinaryString(sum);



public  String addBinary(String a, String b) {
        int i = a.length() - 1;		// i指向a的末尾
        int j = b.length() - 1;		// j指向b的末尾
        int c = 0;		// carry 进位
        // 先把String转为char数组便于处理
        char[] achar = a.toCharArray();
        char[] bchar = b.toCharArray();
        // 结果数组
        char[] reschar = new char[Math.max(achar.length, bchar.length)+2];
        int k = 0;		// k指向结果数组的开头
        
        while(true){
        	if(i<0 && j<0 && c==0){
        		break;
        	}
        	
        	int aint = 0;
        	int bint = 0;
        	
        	if(i >= 0){
        		aint = achar[i] - '0';
        	}
        	if(j >= 0){
        		bint = bchar[j] - '0';
        	}
        	if(aint + bint + c > 1){
        		reschar[k] = (char) ('0' + aint + bint + c - 2);
        		c = 1;
        	}else{
        		reschar[k] = (char) ('0' + aint + bint + c);
        		c = 0;
        	}
        	k++;
        	i--;
        	j--;
        }
         // char数组转string，然后翻转
        return new StringBuffer(new String(reschar, 0, k)).reverse().toString();
    }
