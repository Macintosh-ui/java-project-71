package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1;
        Map<String, Object> data2;
        data1 = Parser.parse(Files.readString(getFilePath(filepath1)), getFileExtension(filepath1));
        data2 = Parser.parse(Files.readString(getFilePath(filepath2)), getFileExtension(filepath2));
        List<Map<String, Object>> diff = Diff.getDiff(data1, data2);
        return Formatter.format(diff, format).trim();
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    private static String getFileExtension(String filepath) {
        return filepath.substring(filepath.lastIndexOf("."));
    }

    private static Path getFilePath(String filepath) {
        return Path.of(filepath).toAbsolutePath().normalize();
    }
}
