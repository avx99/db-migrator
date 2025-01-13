package ma.inwi.innov.migration_app.utils;

import ma.inwi.innov.migration_app.annotations.Executable;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for reflection-based operations to find and filter classes.
 * Specifically used to locate classes annotated with {@link Executable}
 * that match specified versions.
 */
public class ReflectionUtils {

    private ReflectionUtils() {

    }

    /**
     * Finds and returns a list of classes in the specified package that are annotated
     * with {@link Executable} and match one of the provided valid versions.
     *
     * @param packageName   the package to search for classes
     * @param validVersions the list of valid versions to match
     * @return a list of classes that are annotated with {@link Executable} and match
     *         the specified versions, or null if an error occurs
     */
    public static List<Pair<Class<?>, String>>findExecutableClasses(String packageName, List<String> validVersions) {
        try {
            var classes = new ArrayList<Pair<Class<?>, String>>();
            var packagePath = packageName.replace('.', '/');
            var resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (resources.hasMoreElements()) {
                var resource = resources.nextElement();
                var directory = new File(resource.getFile());
                if (directory.exists()) {
                    findClassesInDirectory(packageName, directory, classes, validVersions);
                }
            }
            return classes;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Recursively finds classes in the given directory that are annotated with
     * {@link Executable} and match the specified versions, adding them to the provided list.
     *
     * @param packageName   the current package name being scanned
     * @param directory     the directory to scan for classes
     * @param classes       the list to add matching classes (with their versions)
     * @param validVersions the list of valid versions to match
     */
    private static void findClassesInDirectory(String packageName, File directory, List<Pair<Class<?>, String>> classes, List<String> validVersions) {
        try {
            for (var file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isDirectory()) {
                    findClassesInDirectory(packageName + "." + file.getName(), file, classes, validVersions);
                } else if (file.getName().endsWith(".class")) {
                    var className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    var clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Executable.class)) {
                        var executable = clazz.getAnnotation(Executable.class);
                        var jobVersion = executable.version();
                        if (validVersions != null && validVersions.contains(jobVersion)) {
                            classes.add(Pair.of(clazz, jobVersion));
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }
}
