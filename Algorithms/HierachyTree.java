//O(n), space O(n)

package Snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HierachyTree {
	public static void main(String[] args) throws ParseException {
		JSONParser parser = new JSONParser();
		String input = "[{\"employee\": \"1\", \"manager\": \"2\"}, {\"employee\": \"0\",\"manager\": \"0\"}, {\"employee\": \"2\",\"manager\": \"0\"}, {\"employee\": \"3\",\"manager\": \"0\"}]";
    
		HashMap<Integer, List<Integer>> managerToEmployee = new HashMap<Integer, List<Integer>>();
		int root = -1;
    	JSONArray a = (JSONArray) parser.parse(input);
        for (Object o : a)
        {
          JSONObject person = (JSONObject) o;

          int employee = Integer.valueOf((String) person.get("employee"));

          int manager = Integer.valueOf((String) person.get("manager"));
          if(employee == manager) {
        	  root = manager;
        	  continue;
          }
          if(managerToEmployee.containsKey(manager)) {
        	  managerToEmployee.get(manager).add(employee);
          } else {
        	  List<Integer> list = new ArrayList<Integer>();
        	  list.add(employee);
        	  managerToEmployee.put(manager, list);
          }
        }
        if(root == -1) {
        	System.out.println("No ceo in this company");
        	return;
        }
        TreeNode ceo = buildTree(managerToEmployee, root);
        printTreeWithTabs(ceo, 0);
	}
	
	private static void printTreeWithTabs(TreeNode root, int numberOfTabs) {
		for(int i = 0; i < numberOfTabs; i++) {
			System.out.print("\t");
		}
		System.out.println(root.val);
		for(TreeNode child : root.nodes) {
			printTreeWithTabs(child, numberOfTabs + 1);
		}
	}
	
	private static TreeNode buildTree(HashMap<Integer, List<Integer>> map, int boss) {
		TreeNode root = new TreeNode(boss);
		if(map.containsKey(boss)) {
			for(Integer employee: map.get(boss)) {
				root.nodes.add(buildTree(map, employee));
			}			
		}
		
		return root;
	}
}

class TreeNode {
	int val;
	List<TreeNode> nodes;
	
	public TreeNode(int val) {
		this.val = val;
		this.nodes = new ArrayList<TreeNode>();
	}
}
