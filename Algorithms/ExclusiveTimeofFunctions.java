// O(n), space stack O(n/2)
class Solution {
    /**
        We simulate the logs by utilizing stack. And we use int[n] to keep each function's total time.
        And we keep a prevTimeStamp. So that we can calculate the duration (prev - current)
        Now every time we have a start log, we first need to add the passed duration from last log to this log to the prev function - which is the current top stack funcID. 
		Then push this current funcID to stack. Update our prevTimestamp
        If we have an end log, we need to pop the top stack funcID, and add the duration to this funcID. 
		Then update the prevTimeStamp = timeStamp + 1 -- this is important because timeStamp now is endTime, next startTime should be endTime + 1
    */
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> funcStack = new Stack<>();
        int [] ans = new int [n];
        int prevTimeStamp = 0;
        for(String log : logs){
            String [] logSplit = log.split(":");
            int funcID = Integer.valueOf(logSplit[0]);
            int timeStamp = Integer.valueOf(logSplit[2]);
            if(logSplit[1].equals("start")){
                if(!funcStack.isEmpty())
                    ans[funcStack.peek()] += timeStamp - prevTimeStamp;
                funcStack.push(funcID);
                prevTimeStamp = timeStamp;
            }
            else{
                ans[funcStack.pop()] += timeStamp - prevTimeStamp + 1;
                prevTimeStamp = timeStamp + 1;
            }
        }
        return ans;
    }
}
