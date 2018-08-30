// http://massivealgorithms.blogspot.com/2018/05/leetcode-751-ip-to-cidr.html
// complexity? constant anyway? need better analysis later

class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        List<String> cidrs = new ArrayList<String>();
        Integer num = ipToNumber(ip);
        if (num == null || n < 1) return cidrs;
        while(n > 0) {
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
