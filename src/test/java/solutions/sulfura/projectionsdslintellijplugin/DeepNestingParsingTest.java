package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.testFramework.ParsingTestCase;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslParserDefinition;

public class DeepNestingParsingTest extends ParsingTestCase {

    public DeepNestingParsingTest() {
        super("", "dpd", new ProjectionsDslParserDefinition());
    }

    public void testAliasedWithTypeAlias() {
        doTest(true);
    }

    /**
     * @return path to the test data file directory relative to the root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/valid/deep_nesting";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
