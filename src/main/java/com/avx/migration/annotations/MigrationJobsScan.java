package com.avx.migration.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * Custom annotation to specify the base package for scanning migration job classes.
 * <p>
 * This annotation can be applied to a configuration class to indicate where migration job classes are located,
 * allowing the application to scan and register them dynamically.
 * </p>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * &#064;MigrationJobsScan("com.example.migration.jobs")
 * public class MainClass {
 * }
 * </pre>
 *
 * <h3>Attributes:</h3>
 * <ul>
 *     <li>{@code value} - Alias for {@code basePackage}, specifies the package to scan.</li>
 *     <li>{@code basePackage} - Alias for {@code value}, provides the package location for scanning.</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface MigrationJobsScan {

    /**
     * Alias for {@link #basePackage()}.
     * Specifies the package to scan for migration jobs.
     *
     * @return the package name where migration jobs are located
     */
    @AliasFor("basePackage")
    String value() default "";

    /**
     * Alias for {@link #value()}.
     * Defines the base package to scan for migration jobs.
     *
     * @return the package name where migration jobs are located
     */
    @AliasFor("value")
    String basePackage() default "";
}
