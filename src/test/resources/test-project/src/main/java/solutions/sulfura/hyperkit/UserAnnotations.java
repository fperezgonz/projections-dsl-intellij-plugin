package solutions.sulfura.hyperkit;

import solutions.sulfura.hyperkit.dsl.projections.DtoProjectionSpec;
import solutions.sulfura.hyperkit.dtos.UserDto;

import java.lang.annotation.*;

public class UserAnnotations {

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.METHOD})
    @DtoProjectionSpec(
            projectedClass = UserDto.class,
            value = """
                    id
                    name
                    email
                    """
    )
    public @interface StdUserPostRequestProjection {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.METHOD})
    @DtoProjectionSpec(
            projectedClass = UserDto.class,
            value = """
                    id
                    name
                    email
                    roles {
                        id
                        name
                        permissions {
                            id
                            name
                        }
                    }
                    """
    )
    public @interface StdUserResponseProjection {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.METHOD})
    @DtoProjectionSpec(
            projectedClass = UserDto.class,
            value = """
                    id
                    name
                    email
                    roles {
                        nid
                        name
                        npermissions {
                            id
                            name
                        }
                    }
                    """
    )
    public @interface MissingReferencesExample {
    }

}
