// my solution
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas==null || cost==null || gas.length!= cost.length) return -1;
        int index = 0, total = 0, sum = 0;
        for(int i=0; i< gas.length; i++) {
            sum+=gas[i]-cost[i];
            if(sum<0){
                sum=0;
                index = i+1;
            }
            total +=gas[i]-cost[i];
        }
        return total>=0 ? index: -1;
    }
}


// if total>0. there must exists one starting point. separate the loop into positive and negative part to prove
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas==null||cost==null||(gas.length!=cost.length)) return -1;
        int total=0;
        int sum=0;
        int len=gas.length;
        int index=-1;
        for(int i=0;i<len;i++){
            sum+= gas[i]-cost[i];
            total+=gas[i]-cost[i];
            if(sum<0) {
                index=i;
                sum=0;
            }
        }
        return total>=0 ? index+1 : -1;
    }
}
