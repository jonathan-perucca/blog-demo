package com.rushleader.blog.csv;

import java.util.List;
import java.util.function.Function;

public class CsvTool {

    private List<Character> legalSeparators;

    public CsvTool(List<Character> legalSeparators) {
        this.legalSeparators = legalSeparators;
    }

    public <T> T parse(String content, Function<String[], T> mapper) {
        Character separator = getSeparator(content);
        String[] parts = content.split( String.valueOf("\\" + separator) );

        return mapper.apply(parts);
    }

    Character getSeparator(String content) {
        for (int i = 0; i < content.length(); i++) {
            char character = content.charAt(i);
            if( legalSeparators.contains(character) ) {
                return character;
            }
        }

        throw new IllegalArgumentException("No separator found");
    }
}
