package solutions.sulfura.projectionsdslintellijplugin.manualtests;

import io.vavr.control.Option;
import solutions.sulfura.gend.dtos.Dto;
import solutions.sulfura.gend.dtos.ListOperation;

import java.util.List;
import java.util.Optional;

public class TestClass {


    @TestAnnotation(projectedClass = TestDto.class, value = """
            {
            listTestDto ,
            
            
            optionalString,
            nestedNestedTestDto{
                nestedTestDto {
                    listString
                    plainString
                }
            }
            listTestDto{
                optionString
                optionalString {
                    optionTestDto { optionString,opt, listString
            
                     }
                     optionTestDto
                }
            }
            listString, optionTestDto
                  }
            """)
    public TestDto testMethod(@TestAnnotation(projectedClass = TestDto.class, value = "{listTestDto, optionalString}") TestDto testParam) {

        return null;
    }

    public static class TestDto implements Dto {
        String plainString;
        Option<String> optionString;
        Optional<String> optionalString;
        List<String> listString;
        Option<TestDto> optionTestDto;
        List<ListOperation<TestDto>> listTestDto;
        Optional<NestedTestDto> nestedNestedTestDto;

        @Override
        public Class<?> getSourceClass() {
            return null;
        }
    }

    public static class NestedTestDto implements Dto {
        Option<TestDto> nestedTestDto;
        String nestedPlainString;

        @Override
        public Class<?> getSourceClass() {
            return null;
        }
    }

}
