import java.io.*;
import java.util.*;
import java.security.*;
import java.nio.file.*;
import java.math.*;
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
  
  public List<List<String>> findDup(String rootPath){
    List<List<String>> res = new ArrayList<List<String>>();
    if(rootPath == null || ! (new File(rootPath).isDirectory())) return res;
    List<String> paths = new ArrayList<String>();
    traverseDirectory(rootPath, paths);
    if(paths.size() == 0) return res;
    
    // filter by file length
    HashMap<Long, List<String>> map = new HashMap<Long, List<String>>();
    for(String path: paths){
      long len = new File(path).length();
      if(map.containsKey(len)){
        map.get(len).add(path);
      } else {
        List<String> list = new ArrayList<String>();
        list.add(path);
        map.put(len, list);
      }
    }
    
    // filter by file content hash and byte[]
    for(List<String> sameLenList: map.values()){
      HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
      if(sameLenList.size() >1) {
        for(String path: sameLenList){
          byte[] bytes = readFirstNBytes(path, 100);
          String hash = MD5Hash(bytes);
          if(hashMap.containsKey(hash)){
            hashMap.get(hash).add(path);
          } else {
            List<String> list = new ArrayList<String>();
            list.add(path);
            hashMap.put(hash, list);
          }
        }
      }
      // carefully compare byte by byte
      HashMap<byte[], List<String>> byteMap = new HashMap<byte[], List<String>>();
      for(List<String> sameHashList: hashMap.values()){
        if(sameHashList.size() > 1){
          for(String path: sameHashList){
            byte[] bytes = readAllBytes(path);
            // assuming built-in containsKey does not actually compare array elments
            boolean existed = false;
            for(byte[] key: byteMap.keySet()){
              if(Arrays.equals(key, bytes)){
                byteMap.get(key).add(path);
                existed = true;
              }
            }
            if(!existed){
                List<String> list = new ArrayList<String>();
                list.add(path);
                byteMap.put(bytes, list);
            }
          }
        }
      }
      
      // push all duplicated paths to result list
      for(List<String> sameBytesList: byteMap.values()){
        if(sameBytesList.size() > 1){
          res.add(sameBytesList);
        }
      }
    }
    return res;          
  }
  
  private void traverseDirectory(String dir, List<String> paths){
    File[] files = new File(dir).listFiles();
    for(File file: files){
      if(file.isDirectory()){
        traverseDirectory(file.getPath(), paths);
      } else {
        paths.add(file.getPath());
      }
    }
  }
  
  private byte[] readFirstNBytes(String file, int n) {
    byte[] buffer = new byte[n];
    // in this loop way, even the bytes is fewer than n, it is ok
    try{
      InputStream is = new FileInputStream(file);
      is.read(buffer);
      is.close();
    } catch(IOException e){
      return buffer;
    }

    return buffer;
  }                              
  
  private byte[] readAllBytes(String path){
    try{
      Path p = Paths.get(path);
      return Files.readAllBytes(p);
    } catch(IOException e){
      return new byte[0];
    }
  }
  
  private String MD5Hash(byte[] bytes){
    try{
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.reset();
      md.update(bytes);
      byte[] digested = md.digest();
      //Translates the sign-magnitude representation of a BigInteger into a BigInteger.
      BigInteger bigInt = new BigInteger(1, digested);
      return  bigInt.toString(16);
    }catch (NoSuchAlgorithmException e){
      return "";
    }
  }
}
  
