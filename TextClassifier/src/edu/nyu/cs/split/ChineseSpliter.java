package edu.nyu.cs.split;

import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

/**
 * @author daniel
 *
 */
public class ChineseSpliter implements Spliter{

	public String split(String text, String splitToken){
		String result = null;
		
		MMAnalyzer analyzer = new MMAnalyzer();
		try{
			result = analyzer.segment(text, splitToken);
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
}
