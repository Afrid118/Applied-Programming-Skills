class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] parent = new int[n]; 
        int[] size = new int[n];   
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> acc = accounts.get(i);
            for (int j = 1; j < acc.size(); j++) {
                String email = acc.get(j);
                if (!map.containsKey(email)) {
                    map.put(email, i);
                } else {
                    unionbysize(i, map.get(email), parent, size);
                }
            }
        }
        HashMap<Integer, List<String>> comp = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int i = entry.getValue();
            int u = findupar(i, parent); 
            String email = entry.getKey();
            comp.computeIfAbsent(u, k -> new ArrayList<>()).add(email);
        }
        List<List<String>> result = new ArrayList<>();
        for (int i : comp.keySet()) {
            List<String> emails = comp.get(i);
            Collections.sort(emails); 
            emails.add(0, accounts.get(i).get(0));
            result.add(emails);
        }
        return result;
    }
    int findupar(int node, int[] parent) {
        if (node == parent[node]) return node;
        return parent[node] = findupar(parent[node], parent);
    }
    void unionbysize(int u, int v, int[] parent, int[] size) {
        int ulp_u = findupar(u, parent); 
        int ulp_v = findupar(v, parent); 

        if (ulp_u == ulp_v) return; 
        if (size[ulp_u] >= size[ulp_v]) {
            size[ulp_u] += size[ulp_v];
            parent[ulp_v] = ulp_u;
        } else {
            size[ulp_v] += size[ulp_u];
            parent[ulp_u] = ulp_v;
        }
    }
}