package oncall.util;

import java.util.Arrays;
import java.util.List;

public class WorkerFactory {
    private static final String NAME_SPLITTER = ",";
    public static List<String> createWorkers(String workers) {
        String[] arr = workers.split(NAME_SPLITTER);

        return Arrays.stream(arr)
                .toList();
    }
}
