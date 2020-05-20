// O(n), space O(1)

public boolean isStrobogrammatic(String num) {
    HashMap map = new HashMap();
    map.put('0', '0');
    map.put('1', '1');
    map.put('6', '9');
    map.put('8', '8');
    map.put('9', '6');
    for (int i = 0; i < num.length(); i++) {
        if (!map.containsKey(num.charAt(i)) || (char)map.get(num.charAt(i)) != num.charAt(num.length() - i - 1)) {
            return false;
        }
    }
    return true;
}

// probably not right, in the case "4", it did not check middle digit
public boolean isStrobogrammatic(String num) {
        // number's pair. 0 - 0, 1 - 1, 6 - 9, 8 - 8, others -1
        char[] map = new char[]{'0', '1', 'n', 'n', 'n', 'n', '9', 'n', '8', '6'};
        
        for (int i = 0; i < num.length(); i++) {
            if (map[num.charAt(i) - '0'] != num.charAt(num.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

