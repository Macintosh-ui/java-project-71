package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String plainFormat(Map<String, Object> data1, Map<String, Object> data2, Map<String, String> diff) {
        StringBuilder output = new StringBuilder();
        diff.forEach((k, v) -> {
            String value1 = String.valueOf(data1.get(k));
            String value2 = String.valueOf(data2.get(k));
            switch (v) {
                case "change" -> changeValue(k, value1, value2, output);
                case "add" -> addValue(k, value1, value2, output);
                case "remove" -> output.append("Property '").append(k).append("' was removed").append("\n");
                default -> output.append("");
                //case "no difference" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
            }
        });
        return output.toString();
    }

    public static void addValue(String k, String value1, String value2, StringBuilder output) {
        output.append("Property '").append(k).append("' was added with value ");
        complexCheck(k, value2, output);
    }

    public static void changeValue(String k, String value1, String value2, StringBuilder output) {
        output.append("Property '").append(k).append("' was updated. From ");
        complexCheck(k, value1, value2, output);
    }

    public static void complexCheck(String k, String value2, StringBuilder output) {
        if (value2.startsWith("[") || value2.startsWith("{")) {
            output.append("[complex value]").append(".\n");
        } else {
            output.append("'").append(value2).append("'").append("\n");
        }
    }
    public static void complexCheck(String k, String value1, String value2, StringBuilder output) {
        if (value1.startsWith("[") || value1.startsWith("{")) {
            output.append("[complex value]").append(" to ");
        }
        if (value2.startsWith("[") || value2.startsWith("{")) {
            output.append("[complex value]").append(".\n");
        } else {
            output.append(value1).append(" to ").append(value2).append("\n");
        }
    }
}
