package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.testFramework.ParsingTestCase;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslParserDefinition;

public class ValidBacktickLiteralsTest extends ParsingTestCase {

    public ValidBacktickLiteralsTest() {
        super("", "dpd", new ProjectionsDslParserDefinition());
    }

    public void testBacktickPropertyAlias() {
        doTest(true);
    }

    /**
     * @return path to the test data file directory relative to the root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/valid/backtick_literals";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
