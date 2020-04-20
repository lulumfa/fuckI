//https://www.tutorialspoint.com/design_pattern/filter_pattern.htm
//https://leetcode.com/discuss/interview-question/369272/Amazon-or-Onsite-or-Linux-Find-Command
//  single responsibility principle
//I agree with the other parts like n-ary tree and DFS/BFS operations.
We can use different subclass to represent different requirements with the Liskov substitution principle.




package amazon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  use two seperate classes File and Directory for single responsibility principle.
// We can use different subclass to represent different requirements with the Liskov substitution principle.

public class FileSy// stem {
  FileNode root;

  public FileSystem() {
    FileNodeFactory fileNodeFactory = new FileNodeFactory();

    root = fileNodeFactory.getFileNode(FileNodeType.DIRECTORY);
  }

  public List<File> findFilesByPath (String path) {
    //deserialize the path and
    // DFS/BFS
    return null;
  }

  //TODO
  public List<File> findFilesByOthers () {
    // DFS/BFS
    return null;
  }

  private void dfsFindFiles (List<File> res, FileNode node) {

  }

  public static void main(String[] args) {

  }
}

enum FileNodeType {
  FILE,
  DIRECTORY
}

class FileNodeFactory {
  public FileNode getFileNode(FileNodeType fileNodeType) {
    if (fileNodeType == null) {
      return null;
    }
    switch (fileNodeType) {
      case FILE:
        return new File();
      case DIRECTORY:
        return new Directory();
      default:
        return null;
    }
  }
}

abstract class FileNode {
  String name;
  Timestamp lastModifedTime;
}

class File extends FileNode {
  int size;
  String type; // jgp, txt,etc
}

class Directory extends FileNode {
  Map<String, FileNode> subFileNodesMap;

  public Directory() {
    subFileNodesMap = new HashMap<>();
  }
}

interface Criteria {
  public List<FileNode> meetCriteria(List<FileNode> nodes);

//  public List<FileNode> meetCriteria(FileSystem fileSystem);
}

class CriteriaImageSize implements Criteria {
  int size;
  String type;

  public CriteriaImageSize(int size, String type) {
    this.size = size;
    this.type = type;
  }

  @Override
  public List<FileNode> meetCriteria(List<FileNode> nodes) {
    List<FileNode> imageNodes = new ArrayList<FileNode>();

    for (FileNode node : nodes) {
      if (node instanceof File && ((File) node).size >= size && ((File) node).type.equals(type) ) {
        imageNodes.add(node);
      }
    }
    return imageNodes;
  }
}

class AndCriteria implements Criteria {

  private Criteria criteria;
  private Criteria otherCriteria;

  public AndCriteria(Criteria criteria, Criteria otherCriteria) {
    this.criteria = criteria;
    this.otherCriteria = otherCriteria;
  }

  @Override
  public List<FileNode> meetCriteria(List<FileNode> nodes) {

    List<FileNode> firstCriteriaNodes = criteria.meetCriteria(nodes);
    return otherCriteria.meetCriteria(firstCriteriaNodes);
  }
}

class OrCriteria implements Criteria {

  private Criteria criteria;
  private Criteria otherCriteria;

  public OrCriteria(Criteria criteria, Criteria otherCriteria) {
    this.criteria = criteria;
    this.otherCriteria = otherCriteria;
  }

  @Override
  public List<FileNode> meetCriteria(List<FileNode> nodes) {

    List<FileNode> firstCriteriaItems = criteria.meetCriteria(nodes);
    List<FileNode> otherCriteriaItems = otherCriteria.meetCriteria(nodes);

    for (FileNode node : otherCriteriaItems) {
      if(!firstCriteriaItems.contains(node)){
        firstCriteriaItems.add(node);
      }
    }
    return firstCriteriaItems;
  }
}
