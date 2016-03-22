import java.util.ArrayList;

public class Trie {

	char c;
	Trie[] children;
	boolean isWord;

	public Trie() {
		this((char) 0);
	}

	public Trie(char c) {
		this.c = c;
		this.children = new Trie[26];
		this.isWord = false;
	}

	public void load(char[] dictinary) {
		if (dictinary.length == 0) {
			this.isWord = true;
			return;
		}

		char letter = dictinary[0];
		int index = letter - 'a';

		if (this.children[index] == null) {
			this.children[index] = new Trie(letter);
		}

		char[] dict = new char[dictinary.length - 1];
		for (int i = 0; i < dict.length; i++) {
			dict[i] = dictinary[i];
		}

		this.children[index].load(dict);
	}

	public void load(String word) {
		if (word.isEmpty()) {
			this.isWord = true;
			return;
		}

		char letter = word.charAt(0);
		int index = letter - 'a';

		if (this.children[index] == null) {
			this.children[index] = new Trie(letter);
		}

		this.children[index].load(word.substring(1));
	}

	public boolean constains(String word) {
		if (word.isEmpty())
			if (this.isWord)
				return true;
			else
				return false;

		char letter = word.charAt(0);
		int index = letter - 'a';

		if (this.children[index] == null) {
			return false;
		}

		return this.children[index].constains(word.substring(1));
	}

	ArrayList<String> startsWith(String prefix) {
		return startsWith(prefix.toCharArray(), 0, prefix.length() - 1);
	}

	ArrayList<String> startsWith(char[] prefix, int start, int end) {
		char letter = prefix[start];
		int index = letter - 'a';

		if (this.children[index] == null) {
			return null;
		}

		if (start+1 > end)
			return getWords(String.valueOf(prefix));
		else
			return this.children[index].startsWith(prefix, start + 1, end);
	}

	private ArrayList<String> getWords(String prefix) {
		// TODO Auto-generated method stub
		ArrayList<String> words = new ArrayList<>();
		// prefix += String.valueOf(c);
		if (this.isWord)
			words.add(prefix + this.c);
		for (int i = 0; i < children.length; i++)
			if (children[i] != null) {
				ArrayList<String> w = children[i].getWords(prefix + this.c);
				if (!w.isEmpty())
					words.addAll(w);
			}

		return words;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(c);
	}

}
