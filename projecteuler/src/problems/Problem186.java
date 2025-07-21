package problems;

import java.util.*;

// Connectedness of a Network.
public class Problem186 {

    private static int callLim = 20_000_000;
    private static int[] S = new int[callLim + 1];

    private static HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
    private static int PM = 524287;
    private static int USERS = 1_000_000;
    private static int THRESHOLD = 990_000;
    private static int[] unionFind = new int[USERS];
    private static int cachedPopeFriends = 0;
    private static boolean merged = false;

    public static void main(String[] args) {
        for (long k = 1; k <= 55; k++) {
            S[(int) k] = (int) ((100003L - 200003L * k + 300007L * k * k * k) % USERS);
        }
        for (int k = 56; k <= callLim; k++) {
            S[k] = (S[k - 24] + S[k - 55]) % USERS;
        }
        for (int i = 0; i < USERS; i++) {
            graph.put(i, new LinkedList<>());
            unionFind[i] = i;
        }
        int calls = 0;
        int misdials = 0;
        int beforePopeFirstFriendCall = 1840000;
        while (calls < beforePopeFirstFriendCall || countPMFriends() < THRESHOLD) {
            calls++;
            if (S[calls * 2 - 1] == S[calls * 2]) misdials++;
            addEdge(S[calls * 2 - 1], S[calls * 2]);
            // holy shit its union find
            // holy fucking shitter
//            if (calls % 1000 == 0 || calls > beforePopeFirstFriendCall) {
//                System.out.println("calls: " + calls + ", pope friends: " + countPMFriends());
//            }
            //calls: 1840580, pope friends: 1
            //calls: 1840581, pope friends: 972133
        }
//        System.out.println(calls);
//        System.out.println(misdials);
        int finalCalls = calls - misdials;
        System.out.println("The number of calls until the PM is connected to 99% of users is: " + finalCalls);
    }

    public static int countPMFriends(HashMap<Integer, LinkedList<Integer>> graph) {
        HashSet<Integer> processed = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(PM);
        while (!queue.isEmpty()) {
            int person = queue.poll();
            processed.add(person);
            for (int friend : graph.get(person)) {
                if (!processed.contains(friend)) {
                    queue.add(friend);
                }
            }
            processed.add(person);
        }
        return processed.size();
    }

    public static int countPMFriends() {
        if (!merged) return cachedPopeFriends;
        int popeFriends = 0;
        for (int i = 0; i < USERS; i++) {
            popeFriends += find(i) == PM ? 1 : 0;
        }
        cachedPopeFriends = popeFriends;
        merged = false;
        return popeFriends;
    }

    public static void addEdge(int p1, int p2) {
        if (find(p1) == find(p2)) return;
        int first = find(Math.min(p1, p2));
        int second = find(Math.max(p1, p2));
        if (second == PM) {
            second = first;
            first = PM;
        }
        // make second point to first
        unionFind[second] = first;
        merged = true;

    }

    public static int find(int x) {
        if (unionFind[x] == x) return x;
        unionFind[x] = find(unionFind[x]);
        return unionFind[x];
    }

}
