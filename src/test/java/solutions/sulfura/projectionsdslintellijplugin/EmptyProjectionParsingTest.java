package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.testFramework.ParsingTestCase;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslParserDefinition;

public class EmptyProjectionParsingTest extends ParsingTestCase {

    public EmptyProjectionParsingTest() {
        super("", "dpd", new ProjectionsDslParserDefinition());
    }

    public void testEmptyProjection() {
        doTest(true);
    }

    /**
     * @return path to the test data file directory relative to the root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/grammar_tests/invalid/empty_projection";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
