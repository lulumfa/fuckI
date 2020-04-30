 // Basic idea: In a while loop, calculate min for the current root and update the closest value when necessary. Depending on whether root node is smaller or larger than the target, go to the right or the left branch.

// Time complexity : O(H) since here one goes from root down to a leaf.
// space O(1)

 public int closestValue(TreeNode root, double target) {
    double closest = Integer.MAX_VALUE;
    int value = 0;
    TreeNode current = root;
    while (current != null) {
        if (closest > Math.abs(current.val-target)) {
            closest = Math.abs(current.val-target);
            value = current.val;
        }
        
        if (current.val < target) {
            current = current.right;
        } else if (current.val > target) {
            current = current.left;
        } else {
            break;
        }
    }
    return value;
}

class Solution {
 public int closestValue(TreeNode root, double target) {
    if (root == null) return Integer.MIN_VALUE; // or something interviewer wants us to return
    int val, closest = root.val;
    while (root != null) {
      val = root.val;
      closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
      if (closest == target) return closest; // return faster, in java int + double, java would create a temp double for the int and do the calculatation with two double, e.g. 1 == 1.1 -> 1.0 == 1.1 wont be the same 
        
      root =  target < root.val ? root.left : root.right;
    }
    return closest;
  }
}
