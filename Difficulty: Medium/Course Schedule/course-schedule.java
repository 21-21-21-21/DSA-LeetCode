//{ Driver Code Starts
//Initial template for JAVA

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int n = Integer.parseInt(st[0]);
            int m = Integer.parseInt(st[1]);

            for (int i = 0; i < n; i++)
                list.add(i, new ArrayList<Integer>());

            ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                list.get(v).add(u);
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(u);
                pair.add(v);
                prerequisites.add(pair);
            }

            int[] res = new Solution().findOrder(n, m, prerequisites);
            
            if(res.length==0)
                System.out.println("No Ordering Possible");
            else
            {
                if (check(list, n, res) == true)
                    System.out.println("1");
                else
                    System.out.println("0");
            }
        }
    }
    static boolean check(ArrayList<ArrayList<Integer>> list, int V, int[] res) {
        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res[i]] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : list.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution{
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {
        
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0;i<m;i++){
           adjList.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
        }
        
        //perfrom topo sort.
        int[]indeg = new int[n];
        for(int i = 0;i<n;i++){
            for(int it:adjList.get(i)){
                indeg[it] ++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<n;i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        //to store the topo sort create array.
        int[]topo = new int[n];
        int i = 0;
        while(!q.isEmpty()){
            int currNode = q.peek();
            q.remove();
            topo[i++] = currNode;
            
            for(int it: adjList.get(currNode)){
                indeg[it] -- ;
               if(indeg[it] == 0){
                   q.add(it);
               }
            }
        }
        
        if(i == n){
            return topo;
        }
        return new int[]{};
    }
}