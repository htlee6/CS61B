package hw3.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        // Declaration and Initialization
        HashMap<Integer, Integer> buckets = new HashMap<>();
        for (int i = 0; i < M; i += 1) {
            buckets.put(i, 0);
        }
        // put oomages into buckets
        for (Oomage o : oomages) {
            int buckNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets.put(buckNum, buckets.get(buckNum) + 1);
        }
        int N = oomages.size();
        for (int buckedID : buckets.keySet()) {
            int oomageNum = buckets.get(buckedID);
            if (oomageNum >= N / 2.5 || oomageNum <= N / 50) {
                return false;
            }
        }
        return true;
    }
}
