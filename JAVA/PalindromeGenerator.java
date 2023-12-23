import java.util.ArrayList;
import java.util.List;

public class PalindromeGenerator {
    public static void main(String[] args) {
        int maxLength = 5; // Set the maximum length of palindromes

        List<Integer> palindromes = generatePalindromes(maxLength);

        System.out.println("Palindromes of length less than " + maxLength + ": " + palindromes);
    }

    public static List<Integer> generatePalindromes(int maxLength) {
        List<Integer> palindromes = new ArrayList<>();

        // Generate palindromes for even length
        for (int i = 1; i < Math.pow(10, maxLength / 2); i++) {
            String str = Integer.toString(i);
            String palindrome = str + new StringBuilder(str).reverse().toString();
            palindromes.add(Integer.parseInt(palindrome));
        }

        // Generate palindromes for odd length
        for (int i = 1; i < Math.pow(10, (maxLength - 1) / 2); i++) {
            String str = Integer.toString(i);
            String palindrome = str + new StringBuilder(str).reverse().deleteCharAt(0).toString();
            palindromes.add(Integer.parseInt(palindrome));
        }

        return palindromes;
    }
}
