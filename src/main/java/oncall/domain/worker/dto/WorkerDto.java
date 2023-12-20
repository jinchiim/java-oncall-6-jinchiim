package oncall.domain.worker.dto;

import java.util.List;

public record WorkerDto(List<String> weekDayWorker, List<String> weekendWorker) {
}
