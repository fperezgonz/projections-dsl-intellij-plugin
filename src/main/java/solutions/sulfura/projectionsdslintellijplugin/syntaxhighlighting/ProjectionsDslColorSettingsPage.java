package solutions.sulfura.projectionsdslintellijplugin.syntaxhighlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslLanguageFileType;

import javax.swing.*;
import java.util.Map;

public class ProjectionsDslColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Projection", ProjectionsDslSyntaxHighlighter.PROJECTION),
            new AttributesDescriptor("Property name", ProjectionsDslSyntaxHighlighter.PROPERTY_NAME),
            new AttributesDescriptor("Property alias", ProjectionsDslSyntaxHighlighter.PROPERTY_ALIAS),
            new AttributesDescriptor("Type alias", ProjectionsDslSyntaxHighlighter.TYPE_ALIAS),
            new AttributesDescriptor("Separator", ProjectionsDslSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Braces", ProjectionsDslSyntaxHighlighter.BRACES),
            new AttributesDescriptor("Bad character", ProjectionsDslSyntaxHighlighter.BAD_CHARACTER)
    };

    @Override
    public Icon getIcon() {
        return ProjectionsDslLanguageFileType.INSTANCE.getIcon();
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ProjectionsDslSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                {
                    propertyName propertyAlias :TypeAlias {
                        propertyName2, propertyName3
                    }
                
                }
                """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "ProjectionsDsl";
    }

}
