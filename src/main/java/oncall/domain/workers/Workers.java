package oncall.domain.workers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Workers {
    private static final String NAME_REGEX = "^[가-힣]{1,5}$";
    private final List<String> workers;

    public Workers(List<String> workers) {
        validateWorkers(workers);
        this.workers = new ArrayList<>(workers);
    }

    private void validateWorkers(List<String> workers) {
        validateUniqueName(workers);
        if (workers.size() > 35 || workers.size() < 5) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
        for (String worker : workers) {
            if (!worker.matches(NAME_REGEX)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateUniqueName(List<String> workers) {
        Set<String> uniqueWorkers = new HashSet<>(workers);
        if (uniqueWorkers.size() != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public void compareWorker(Workers workers) {
        List<String> otherWorkers = workers.getWorkers();
        for (String name : this.workers) {
            if (!otherWorkers.contains(name)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
        }
    }

    public List<String> getWorkers() {
        return workers;
    }
}
