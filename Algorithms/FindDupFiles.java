import java.io.*;
import java.util.*;
import java.security.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");

    for (String string : strings) {
      System.out.println(string);
    }
    
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    System.out.println(stack.pop());
  }
  
  public List<List<String>> findDup(String path){
    List<List<String>> res = new ArrayList<List<String>>();
    if(path == null || ! (new File(path).isDirectory())) return res;
    List<String> paths = new ArrayList<String>();
    traverseDirectory(path, paths);
    if(paths.size() == 0) return res;
    
    // filter by file length
    HashMap<Integer, List<String>> map = new Hashmap<Integer, List<String>>();
    for(String path: paths){
      int len = new File(path).length();
      if(map.containsKey(len)){
        map.get(len).add(path);
      } else {
        List<String> list = new ArrayList<String>();
        list.add(path);
        map.put(len, list);
      }
    }
    
    // filter by file content hash
    List<List<String>> hashFiltered = new ArrayList<List<String>>();
    for(List<String> list: map.values()){
      HashMap<String, List<String>> map = new HashMap<String, List<String>>;
      if(list.size() >1) {
        for(String path: list){
          String hash = MD5Hash(new File(path).b)
        }
      }
    }
    
  }
  
  public void traverseDirectory(String dir, List<String> paths){
    File[] files = new File(dir).listFiles();
    for(File file: files){
      if(file.isDirectory()){
        traverseDirectory(file.path);
      } else {
        paths.add(file.path);
      }
    }
  }
  
  public string MD5Hash(File file){
    MessageDigest md = MessageDigest.getInstance("MD5");
    m.reset();
    m.update(file.get)
  }
}
