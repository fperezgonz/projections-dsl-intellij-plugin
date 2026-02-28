package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.testFramework.ParsingTestCase;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslParserDefinition;

public class SinglePropertyParsingTest  extends ParsingTestCase {

    public SinglePropertyParsingTest() {
        super("", "dpd", new ProjectionsDslParserDefinition());
    }

    public void testSimple() {
        doTest(true);
    }

    public void testAliased() {
        doTest(true);
    }

    public void testAliasedAndTypeAliasAfterProjection() {
        doTest(true);
    }

    public void testAliasedAndTypeAliasBeforeProjection() {
        doTest(true);
    }

    public void testAliasedAs() {
        doTest(true);
    }

    public void testTypeAliasAfterProjection() {
        doTest(true);
    }

    public void testTypeAliasBeforeProjection() {
        doTest(true);
    }

    public void testWithProjection() {
        doTest(true);
    }

    /**
     * @return path to the test data file directory relative to the root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/valid/single_property";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
