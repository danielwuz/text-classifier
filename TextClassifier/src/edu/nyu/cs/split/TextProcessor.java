package edu.nyu.cs.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Preprocess text before classification
 * 
 * @author daniel
 * 
 */
public class TextProcessor {

	private Spliter spliter;

	private Map<String, String[]> cache = new HashMap<String, String[]>();

	public TextProcessor(Spliter spliter) {
		this.spliter = spliter;
	}

	public String[] process(String text) {
		String[] terms = spliter.split(text, "").split("");
		terms = dropStopWords(terms);
		return terms;
	}

	public String[] process(String text, String filePath) {
		if (cache.containsKey(filePath)) {
			return cache.get(filePath);
		}
		String[] terms = spliter.split(text, "").split("");
		terms = dropStopWords(terms);
		cache.put(filePath, terms);

		return terms;
	}

	private String[] dropStopWords(String[] oldWords) {
		List<String> result = new ArrayList<String>();
		for (String word : oldWords) {
			word = word.trim();
			if (!StopWordsHandler.instance().isStopWord(word)) {
				result.add(word);
			}
		}
		return result.toArray(new String[0]);
	}

	public String[] retriveByPath(String filePath) {
		return cache.get(filePath);
	}
}
