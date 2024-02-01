
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String word = "";
		word = str.substring(1);
		return word;
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));

			
		} else {
			int min1 = levenshtein(tail(word1), word2);
			int min2 =  levenshtein(word1, tail(word2));
			int min3 = levenshtein(tail(word2), tail(word1));
			int finalMin = 1 + Math.min(min1, Math.min(min2, min3));	
		    return finalMin;
		}
	}
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String ans  = "";
		int temp = 0;
		int minLev = threshold + 1;
		for (int i = 0; i < dictionary.length; i++) {
			
			if ((temp = (levenshtein(word, dictionary[i]))) < minLev) {
				minLev = temp;
				ans = dictionary[i];
			}

		}
		if (minLev > threshold) {
			return word;
		} else {
			return ans;
		}
	
	}

}
