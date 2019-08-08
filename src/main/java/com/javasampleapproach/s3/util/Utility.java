package com.javasampleapproach.s3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utility {

	public static void displayText(InputStream input) throws IOException {
		BufferedReader fin;
		String fieldsep;

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		 getline(reader);
		/*
		 * // Read one text line at a time and display. BufferedReader reader =
		 * new BufferedReader(new InputStreamReader(input)); while (true) {
		 * String line = reader.readLine(); if (line == null) break;
		 * System.out.println("    " + line); }
		 */
	}

	public static String getline(BufferedReader fin) throws IOException {
		String line;

		line = fin.readLine();
		if (line == null)
			return null;

		ArrayList field = split(line, "|");
		
		field.stream().forEach(System.out::println);
		System.out.println(line);
		return line;
	}

	private static ArrayList split(String line, String sep) {
		ArrayList list = new ArrayList();
		int i, j;

		if (line.length() == 0)
			return list;

		i = 0;
		do {
			if (i < line.length() && line.charAt(i) == '"') {
				StringBuffer field = new StringBuffer();
				j = advquoted(line, ++i, sep, field);
				list.add(field.toString());
			}

			else {
				j = line.indexOf(sep, i);
				if (j == -1)
					j = line.length();
				list.add(line.substring(i, j));
			}
			i = j + sep.length();
		} while (j < line.length());

		return list;
	}

	private static int advquoted(String s, int i, String sep, StringBuffer field) {
		field.setLength(0);
		for (; i < s.length(); i++) {
			if (s.charAt(i) == '"' && ++i < s.length() && s.charAt(++i) != '"') {
				int j = s.indexOf(sep, i);
				if (j == -1)
					j = s.length();
				field.append(s.substring(i, j));
				i = j;
				break;
			}
			field.append(s.charAt(i));
		}

		return i;
	}
}
