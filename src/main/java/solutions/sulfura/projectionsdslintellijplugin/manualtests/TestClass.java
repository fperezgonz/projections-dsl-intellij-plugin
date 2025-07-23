package solutions.sulfura.projectionsdslintellijplugin.manualtests;

import solutions.sulfura.hyperkit.dsl.projections.DtoProjectionSpec;
import solutions.sulfura.hyperkit.dtos.Dto;
import solutions.sulfura.hyperkit.dtos.ListOperation;
import solutions.sulfura.hyperkit.dtos.ValueWrapper;

import java.util.List;
import java.util.Optional;

public class TestClass {

    @DtoProjectionSpec(projectedClass = TestDto.class, value = """
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
    public TestDto testMethod(@DtoProjectionSpec(projectedClass = TestDto.class, value = "{listTestDto, optionalString}") TestDto testParam) {

        return null;
    }

    public static class TestDto implements Dto {
        String plainString;
        ValueWrapper<String> optionString;
        Optional<String> optionalString;
        List<String> listString;
        ValueWrapper<TestDto> optionTestDto;
        List<ListOperation<TestDto>> listTestDto;
        Optional<NestedTestDto> nestedNestedTestDto;

        @Override
        public Class<?> getSourceClass() {
            return null;
        }
    }

    public static class NestedTestDto implements Dto {
        ValueWrapper<TestDto> nestedTestDto;
        String nestedPlainString;

        @Override
        public Class<?> getSourceClass() {
            return null;
        }
    }

}
