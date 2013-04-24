package com.comp1008.serveranalytics.datamanagement;

public class Parser {
	public static String parseItemFromLine(char generalBreakCharacter, char thisBreakCharacter, String line, int numberOfItemsToSkip)
	{
		String returnString = "";
		int charCount = 0;
		int itemsPassed = 0;
		//skip items we don't want
		while (itemsPassed < numberOfItemsToSkip && charCount < line.length())
		{
			char c = line.charAt(charCount);
			if (c == generalBreakCharacter)
			{
				itemsPassed++;
			}
			charCount++;
		}
		//read the item we do want into the return string
		for (charCount = charCount; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == thisBreakCharacter)
		    {
		    	break;
		    }
		    else
		    {
		    	returnString = returnString+Character.toString(c);
		    }
		}
		return returnString;
	}
}
