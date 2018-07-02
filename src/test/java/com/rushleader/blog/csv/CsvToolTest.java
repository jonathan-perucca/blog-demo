package com.rushleader.blog.csv;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvToolTest {

    private CsvTool csvTool = new CsvTool( asList(',', '|') );

    @Test
    public void should_get_separator() {
        String content = "ColumnOne|ColumnTwo|ColumnThree";

        Character separator = csvTool.getSeparator(content);

        assertThat( separator ).isEqualTo('|');
    }

    @Test
    public void should_extract_data() {
        String content = "Value One|Value Two|\"Value Three\"";

        User user = csvTool.parse(content, (String[] parts) -> {
            User result = new User();
            result.setOne(parts[0]);
            result.setTwo(parts[1]);
            result.setThree(parts[2]);
            return result;
        });

        assertThat( user.getOne() ).isEqualToIgnoringCase("value one");
        assertThat( user.getTwo() ).isEqualToIgnoringCase("value two");
        assertThat( user.getThree() ).isEqualToIgnoringCase("\"value three\"");
    }
}
