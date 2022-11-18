package liqp.blocks;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import liqp.Template;
import liqp.TemplateParser;

public class RawTest {

    @Test
    public void applyTest() throws RecognitionException {

        String[][] tests = {
                {"{% raw %}{% endraw %}", ""},
                {"{% raw %}{{a|b}}{% endraw %}", "{{a|b}}"}
        };

        for (String[] test : tests) {

            Template template = TemplateParser.DEFAULT.parse(test[0]);
            String rendered = String.valueOf(template.render());

            assertThat(rendered, is(test[1]));
        }
    }

    /*
     * def test_tag_in_raw
     *   assert_template_result '{% comment %} test {% endcomment %}',
     *                          '{% raw %}{% comment %} test {% endcomment %}{% endraw %}'
     * end
     */
    @Test
    public void tag_in_rawTest() throws RecognitionException {

        assertThat(TemplateParser.DEFAULT.parse("{% raw %}{% comment %} test {% endcomment %}{% endraw %}").render(),
                is("{% comment %} test {% endcomment %}"));
    }

    /*
     * def test_output_in_raw
     *   assert_template_result '{{ test }}',
     *                          '{% raw %}{{ test }}{% endraw %}'
     * end
     */
    @Test
    public void output_in_rawTest() throws RecognitionException {

        assertThat(TemplateParser.DEFAULT.parse("{% raw %}{{ test }}{% endraw %}").render(),
                is("{{ test }}"));
    }
}
