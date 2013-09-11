package za.co.immedia.pinnedheaderlistviewexample;

import java.util.ArrayList;

import za.co.immedia.pinnedheaderlistviewexample.HanziToPinyin.Token;

public class ResourcesNameUtil {

	/**
	 * 将资源名转成拼音+资源名
	 * 
	 * @param resourcesName
	 *            资源名
	 * @return
	 */
	public static String getSortKey(String resourcesName) {
		ArrayList<Token> tokens = HanziToPinyin.getInstance()
				.get(resourcesName);
		if (tokens != null && tokens.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Token token : tokens) {
				if (Token.PINYIN == token.type) {
					if (sb.length() > 0) {
						sb.append(' ');
					}
					sb.append(token.target.toLowerCase());
					sb.append(' ');
					sb.append(token.source.toLowerCase());
				} else {
					if (sb.length() > 0) {
						sb.append(' ');
					}
					sb.append(token.source.toLowerCase());
				}
			}
			return sb.toString();
		}
		return null;
	}
}
