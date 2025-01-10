package ma.inwi.innov.migration_app.utils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Utility class for handling file operations, specifically for retrieving files from the
 * local "uploads" directory and converting them to {@link MultipartFile}. The "uploads" folder
 * should be configured as a volume (the source is the static files of the environment), and during deployment,
 * this volume must be mounted to the application to ensure proper access to the files.
 */
public class FilesUtils {

    /**
     * Base path for file uploads.
     */
    private final static String BASE_PATH = "uploads/";

    /**
     * Private constructor to prevent instantiation.
     */
    private FilesUtils() {
        // Utility class, prevent instantiation
    }

    /**
     * Retrieves a file from the "uploads" directory and converts it to a {@link MultipartFile}.
     *
     * <p>During deployment, ensure that the "uploads" directory is mounted as a volume to this
     * application. This setup allows the application to access and manipulate the files as needed.
     *
     * @param filename the name of the file to retrieve
     * @return a {@link MultipartFile} representation of the file, or {@code null} if an error occurs
     */
    public static MultipartFile getFile(String filename) {
        try {
            var resource = new ClassPathResource(BASE_PATH + filename);
            var content = FileCopyUtils.copyToByteArray(resource.getInputStream());

            return new MultipartFile() {
                @Override
                public String getName() {
                    return filename;
                }

                @Override
                public String getOriginalFilename() {
                    return filename;
                }

                @Override
                public String getContentType() {
                    return null; // Set the content type if needed
                }

                @Override
                public boolean isEmpty() {
                    return content.length == 0;
                }

                @Override
                public long getSize() {
                    return content.length;
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return content;
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return new ByteArrayInputStream(content);
                }

                @Override
                public Resource getResource() {
                    return MultipartFile.super.getResource();
                }

                @Override
                public void transferTo(Path dest) throws IOException, IllegalStateException {
                    MultipartFile.super.transferTo(dest);
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {
                    FileCopyUtils.copy(content, dest);
                }
            };
        } catch (IOException e) {
            return null;
        }
    }
}
