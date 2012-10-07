package edu.nyu.cs.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.nyu.cs.split.ChineseSpliter;
import edu.nyu.cs.split.TextProcessor;

public class DataManager {

	private static DataManager instance = null;

	private TextProcessor processor = null;

	private String[] classifications;

	private File trainingTextDir;

	private int vocabulary = -1;

	private Map<String, Integer> wordsCounter = null;

	public static DataManager getInstance() {
		return DataManager.getInstance(null);
	}

	public static DataManager getInstance(String filePath) {
		if (instance == null && filePath != null) {
			instance = new DataManager(filePath);
		}
		return instance;
	}

	private DataManager(String filePath) {
		// initialize text processor
		processor = new TextProcessor(new ChineseSpliter());
		//
		trainingTextDir = new File(filePath);
		if (!trainingTextDir.isDirectory()) {
			throw new IllegalArgumentException();
		}
		this.classifications = trainingTextDir.list();
		wordsCounter = new HashMap<String, Integer>();

		preprocess();
	}

	private void preprocess() {
		// compute total number of vocabulary
		Set<String> vocaSet = new HashSet<String>();
		// classification
		for (String category : classifications) {
			try {
				String[] filePathList = getFilesPath(category);
				Integer wordCount = 0;
				for (String filePath : filePathList) {
					String text = getText(filePath);
					String[] terms = processor.process(text, filePath);
					for (String term : terms) {
						vocaSet.add(term);
					}
					wordCount += terms.length;
				}
				wordsCounter.put(category, wordCount);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		vocabulary = vocaSet.size();
	}

	public String[] getClassifications() {
		return this.classifications;
	}

	/**
	 * Reads all file paths under given directory
	 * 
	 * @param classification
	 *            directory which contains all text files
	 * @return list of file paths as String
	 */
	public String[] getFilesPath(String classification) {
		String absolutePath = trainingTextDir.getPath() + File.separator
				+ classification;
		File classDir = new File(absolutePath);
		String[] result = classDir.list();
		for (int i = 0; i < result.length; i++) {
			result[i] = absolutePath + File.separator + result[i];
		}

		return result;
	}

	/**
	 * Reads file in a String
	 * 
	 * @param filePath
	 *            absolute file path
	 * @return a String which contains plain text
	 * @throws IOException
	 */
	public String getText(String filePath) throws IOException {
		InputStreamReader in = new InputStreamReader(new FileInputStream(
				filePath),"GBK");
		BufferedReader reader = new BufferedReader(in);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line + " ");
		}
		in.close();
		reader.close();
		return sb.toString();
	}

	public int getTrainingFileCount() {
		int total = 0;
		for (String classification : classifications) {
			total += getTrainingFileCountOfClassification(classification);
		}
		return total;
	}

	public int getTrainingFileCountOfClassification(String classification) {
		File classDir = new File(trainingTextDir.getPath() + File.separator
				+ classification);
		return classDir.list().length;
	}

	public Integer numberOfWord(String key, String category) {
		if (key == null || key.equals("")) {
			return 0;
		}
		int count = 0;

		String[] filePathList = getFilesPath(category);
		for (String filePath : filePathList) {
			String[] terms = processor.retriveByPath(filePath);
			for (String term : terms) {
				if (key.equals(term)) {
					count++;
				}
			}
		}
		return count;
	}

	public Integer getVocabulary() {
		return vocabulary;
	}

	public Integer totalNumberOfWords(String category) {
		return wordsCounter.get(category);
	}
}
