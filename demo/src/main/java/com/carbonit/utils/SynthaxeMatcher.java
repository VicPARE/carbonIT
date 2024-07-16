package com.carbonit.utils;

import java.util.regex.Pattern;

public class SynthaxeMatcher {
    public static boolean matchesAny(String input, String...rexepStrings){
        for(String regexp : rexepStrings)
        {
            if (Pattern.compile(regexp).matcher(input.replaceAll("[\\s\\t]","")).find()) return true;
        }
        return false ;
    }
}
