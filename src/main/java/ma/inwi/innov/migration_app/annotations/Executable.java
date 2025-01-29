package ma.inwi.innov.migration_app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class as executable with a specific version.
 * This annotation is retained at runtime and is intended to be used on types (classes or interfaces).
 *
 * <p>The {@code version} element specifies the version of the executable class. This can be used
 * to filter and execute specific versions of classes during runtime, ensuring compatibility or
 * version-based functionality within an application.
 *
 * <p>Usage example:
 * <pre>
 * &#64;Executable(version = "1.0.0")
 * public class SampleJob {
 *     // Implementation of the job
 * }
 * </pre>
 *
 * <p>This annotation can be processed using reflection to dynamically discover and execute
 * classes with the specified version.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Executable {

    /**
     * Specifies the version of the executable class.
     *
     * @return the version as a {@code String}
     */
    String version();

    /**
     * Decide if this job is going to be executed or not.
     *
     * @return the version as a {@code boolean}
     */
    boolean enable() default true;

    /**
     * Order of job's execution
     *
     * @return the version as a {@code String}
     */
    String order() default "0";

    /**
     * Description of the job
     *
     * @return the description as a {@code String}
     */
    String description() default "";
}
