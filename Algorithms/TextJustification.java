// 。时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。
// 而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)）。

// a little bit shorter but less readable due to combination of 2 cases
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if(words == null) return null;
        
        List<String> res = new ArrayList<String>();
        
        int wordsLength = 0, n = words.length;
        
        for(int i = 0; i < n; i++) {
            int start = i;
            wordsLength = words[i].length();
            
            while((i+ 1) < n && (wordsLength + words[i+1].length() + i + 1 -start) <= maxWidth) {
                i++;
                wordsLength += words[i].length();
            }
            
            int spaces = maxWidth - wordsLength;
            int segs = (i < n -1 && i - start > 0) ? spaces/(i - start) : 1;
            int extra = i < n -1 ? spaces - segs * (i-start) : 0;
            
            StringBuilder sb = new StringBuilder();
            for(int j = start; j <=i; j++) {
                sb.append(words[j]);
                for(int k = 0;k < segs && spaces > 0; k++) {
                    sb.append(" ");
                }
                spaces -= segs;
                if(spaces > 0 && extra-- > 0) {
                    sb.append(" ");
                    spaces--;
                }
            }
            
            while(spaces-- > 0) {
                sb.append(" ");
            }
            
            res.add(sb.toString());
        }
        
        return res;
    }
}

// my latest own way
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        
        if(words == null || words.length == 0) return res;
        
        for(int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            int wordCount = 1;
            int start = i;
            int width = words[i].length();
            while((i+1) < words.length && (width + words[i+1].length() + wordCount) <= maxWidth) {
                width += words[i+1].length();
                wordCount++;
                i++;
            }
            
            if(i < words.length -1 && wordCount > 1) {
                int extra = maxWidth - width;
                int extraPerSpace = extra/(wordCount-1);
                int extraExtra = extra % (wordCount -1);
                for(int j = start; j <= i; j++) {
                    sb.append(words[j]);
                    if(j != i) {
                        for(int k = 0; k < extraPerSpace; k++) {
                            sb.append(" ");
                        }
                        if(extraExtra-- > 0) sb.append(" ");
                    }
                }
            } else {
               for(int j = start; j <= i; j++) {
                   sb.append(words[j]);
                   if(j != i) sb.append(" ");
               } 
               for(int j = sb.length(); j < maxWidth; j++) {
                   sb.append(" ");
               }
            }
            
            res.add(sb.toString());
        }
        return res;
    }
}

// latest

public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> lines = new ArrayList<String>();
        
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }
            
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.setLength(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        
        return lines;
    }
}

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
