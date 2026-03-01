package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.testFramework.ParsingTestCase;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslParserDefinition;

public class InvalidBacktickLiteralsTest extends ParsingTestCase {

    public InvalidBacktickLiteralsTest() {
        super("", "dpd", new ProjectionsDslParserDefinition());
    }

    public void testSingleBacktick() {
        doTest(true);
    }

    public void testUnclosedBacktick() {
        doTest(true);
    }

    public void testUnescapedBackslash() {
        doTest(true);
    }

    public void testUnescapedBacktick() {
        doTest(true);
    }

    /**
     * @return path to the test data file directory relative to the root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/grammar_tests/invalid/backtick_literals";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
