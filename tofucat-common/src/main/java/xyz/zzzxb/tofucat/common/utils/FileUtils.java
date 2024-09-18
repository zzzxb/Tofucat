package xyz.zzzxb.tofucat.common.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * zzzxb
 * 2023/8/10
 */
public final class FileUtils {

    public static void canCreateDirectories(String dirPath) {
        if (!exists(dirPath)) {
            try {
                Path path = Files.createDirectories(Paths.get(dirPath));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void canCreateFile(String filePath, String content) {
        if (!exists(filePath)) {
            write(content.getBytes(StandardCharsets.UTF_8), filePath);
        }
    }

    public static String joinPath(String ... pathNodeNames) {
        return StringUtils.join(File.separator, pathNodeNames);
    }

    public static boolean exists(Collection<String> filepath) {
        for (String path : filepath) {
            File file = new File(path);
            CheckParamUtils.isFalse(exists(file.getAbsolutePath())).throwMessage("{} file not exists", file.getAbsolutePath());
        }
        return true;
    }

    public static boolean exists(String filepath) {
        return Files.exists(Paths.get(filepath));
    }

    public static void write(byte[] bytes, String outPath) {
        File file = new File(outPath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] read(String filePath) {
        return read(new File(filePath));
    }

    public static byte[] read(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
