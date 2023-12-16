package oncall.util;

import java.util.Arrays;
import java.util.List;

public class WorkerFactory {
    public static List<String> createWorkers(String workers) {
        String[] arr = workers.split(",");

        return Arrays.stream(arr)
                .toList();
    }
}
