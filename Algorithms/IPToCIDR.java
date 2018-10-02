// http://massivealgorithms.blogspot.com/2018/05/leetcode-751-ip-to-cidr.html
// complexity? constant anyway? need better analysis later
// 由于题目中要求尽可能少的使用CIDR块，那么在n确定的情况下，CIDR块能覆盖的越多越好。根据我们前面的分析，当CIDR块斜杠后面的数字越小，
// 该块覆盖的ip地址越多。那么就是说相同前缀的个数越少越好，但是我们的ip地址又不能小于给定的ip地址，所以我们只能将0变为1，而不能将1变为0。
// 所以我们的选择就是有将最低位1后面的0进行变换，比如"255.0.0.8"末尾有3个0，可以变换出8个不同的地址。那么我们只要找出末尾1的位置，
// 就知道能覆盖多少个地址了。找末尾1有个trick，就是利用 x & -x 来快速找到，这个trick在之前做的题中也有应用。知道了最多能覆盖地址的数量，
// 还要考虑到n的大小，不能超过n，因为题目只要求覆盖n个。确定了覆盖的个数，我们就可以进行生成CIDR块的操作了，之前我们为了求 x & -x，
// 将ip地址转为了一个十进制的数，现在我们要把每一块拆分出来，直接按对应位数量进行右移并与上255即可，斜杠后的数字计算通过覆盖的个数进行log2运算，
// 再被32减去即可，

// runtime, constant, but if really want to analyze, all constatns, 
// and the while is doing bit manipulations and takes O(lgn), as most 64 bits, so still constant
import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
public static void main(String[] args) {
    IpCIDRConverter converter = new IpCIDRConverter();
    System.out.println(converter.ipToCIDR("255.0.0.7", 10));
    // 0000 0111 (7), 32
    // 0000 1000 (8), 29 (32-3)
    // 0001 0000 (16), 32
  }

}

class IpCIDRConverter {
    public List<String> ipToCIDR(String ip, int n) {
        List<String> cidrs = new ArrayList<String>();
        Integer num = ipToNumber(ip);
        if (num == null || n < 1) return cidrs;
        while(n > 0) {
// identify the location of first 1's from lower bit to higher
// bit of start IP
// e.g. 00000001.00000001.00000001.01101100, return 4 (100)
            int bit = num & -num;
            while (bit > n) {
                bit /=2;
            }
            cidrs.add(numberToIp(num, bit));
            n -= bit;
            num += bit;
        }
        return cidrs;
    }
    
    private String numberToIp(int num, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 24; i >=0; i-= 8) {
            sb.append(String.valueOf((num >>> i) & 255));
            if (i == 0) {
                sb.append("/");
            } else {
                sb.append(".");
            }
        }
        int bit = 0;
        while(count > 0) {
            count >>>= 1;
            bit++;
        }
        sb.append(33 - bit);
        return sb.toString();
    }
    
    private Integer ipToNumber(String ip) {
        if (ip == null) return null;
        String[] bytes = ip.split("\\.");
        int x = 0;
        for (String b : bytes) {
            x = (x << 8) + Integer.valueOf(b);
        }
        return x;
    }
}




