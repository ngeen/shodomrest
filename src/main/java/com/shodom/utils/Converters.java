package com.shodom.utils;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Converters {
	
	public static String toEnglish(String s) {
		Locale enLocale = Locale.forLanguageTag("en_US");
		return StringUtils.stripAccents(s.toUpperCase(enLocale)).toLowerCase(enLocale).replaceAll(" ", "-");
	}

}
