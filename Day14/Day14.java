import java.util.List;

public class Day14 {

	static int intInput = 554401;
    public static void main(String[] args) {
        System.out.println(makeRecipes(intInput).substring(intInput));
        System.out.println(makeRecipes(30_000_000).indexOf(Integer.toString(intInput)));
    }

    static StringBuilder makeRecipes(int goal) {
        StringBuilder scores = new StringBuilder();
        int a = 3;
        int b = 7;
        int aPos = 0;
        int bPos = 1;
        scores.append(String.valueOf(a));
        scores.append(String.valueOf(b));
        while (scores.length() < goal + 10) {
            int sum = a + b;
            if (sum > 9) {
                scores.append(String.valueOf(sum / 10));
                scores.append(String.valueOf(sum % 10));
            } else {
                scores.append(String.valueOf(sum));
            }
            aPos = (aPos + (1 + a)) % scores.length();
            bPos = (bPos + (1 + b)) % scores.length();
            a = scores.charAt(aPos) - '0';
            b = scores.charAt(bPos) - '0';
        }
        return scores;
    }
}