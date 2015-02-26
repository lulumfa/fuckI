//reference: http://blog.csdn.net/linhuanmars/article/details/24063271
// 。时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。
// 而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)）。
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        if(words==null || words.length==0) return res;
        
        int last = 0;
        int count = 0;
        int partition =0;
        int extra = 0;
        for(int i=0; i<words.length; i++) {
            if(count + words[i].length() + i-last>L) {
                if(i-last-1>0) {
                    partition = (L-count)/(i-last-1);
                    extra = (L-count)%(i-last-1);
                }
                StringBuilder str = new StringBuilder();
                for(int j = last; j < i; j++) {
                    str.append(words[j]);
                    if(i-j>1) {
                        for(int k = 0; k<partition; k++) {
                            str.append(" ");
                        }
                        if(extra!=0) {
                            str.append(" ");
                            extra--;
                        }
                    }
                    
                }
                for(int j = str.length(); j<L; j++) {
                    str.append(" ");
                }
                res.add(str.toString());
                count = 0;
                last = i;
            } 
            count +=words[i].length();
        }
        StringBuilder str = new StringBuilder();
        for(int i = last; i<words.length; i++) {
            str.append(words[i]);
            if(str.length()<L) str.append(" ");
        }
        for(int i = str.length(); i<L; i++) {
            str.append(" ");
        }
        res.add(str.toString());
        return res;
    }
}
