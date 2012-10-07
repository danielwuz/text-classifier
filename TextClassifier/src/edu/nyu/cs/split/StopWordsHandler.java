package edu.nyu.cs.split;

import java.util.HashSet;
import java.util.Set;

public class StopWordsHandler {
	// stop words list
	private static String stopWordsList[] = { "的", "了", "在", "是", "我", "有", "和", "就",  
        "不", "人", "都", "一", "一个", "上", "也", "很", "到", "说", "要", "去", "你",  
        "会", "着", "没有", "看", "好", "自己", "这", "" };

	private Set<String> stopWordFilter = new HashSet<String>();

	private static StopWordsHandler instance = null;

	private StopWordsHandler() {
		for (int i = 0; i < stopWordsList.length; i++) {
			stopWordFilter.add(stopWordsList[i]);
		}
	}

	public static StopWordsHandler instance() {
		if (instance == null) {
			instance = new StopWordsHandler();
		}
		return instance;
	}

	public boolean isStopWord(String word) {
		return stopWordFilter.contains(word);
	}
}
