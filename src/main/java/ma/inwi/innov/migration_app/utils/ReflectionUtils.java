package ma.inwi.innov.migration_app.utils;

import ma.inwi.innov.migration_app.annotations.Executable;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class ReflectionUtils {

    private ReflectionUtils() {

    }


    public static List<Class<?>> findExecutableClasses(String packageName) {
        try {
            var classes = new ArrayList<Class<?>>();
            var packagePath = packageName.replace('.', '/');
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    findClassesInDirectory(packageName, directory, classes);
                }
            }
            return classes;
        } catch (Exception e) {
            return null;
        }

    }

    private static void findClassesInDirectory(String packageName, File directory, List<Class<?>> classes) {
        try {
            for (var file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isDirectory()) {
                    findClassesInDirectory(packageName + "." + file.getName(), file, classes);
                } else if (file.getName().endsWith(".class")) {
                    var className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    var clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Executable.class)) {
                        classes.add(clazz);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
