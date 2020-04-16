import java.math.BigInteger;

/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static BigInteger countTurnips(In in) {
        BigInteger totalTurnips = BigInteger.valueOf(0);
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            BigInteger numAvailable = BigInteger.valueOf(in.readInt());
            if (foodType.equals("turnip")) {
                BigInteger newTotal = totalTurnips.add(numAvailable);
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
    }
}
