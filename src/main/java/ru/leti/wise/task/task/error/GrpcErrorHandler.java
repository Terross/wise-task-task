package ru.leti.wise.task.task.error;

import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcErrorHandler {
    public Status processError(BusinessException e) {
        return switch (e.getErrorCode()) {
            case TASK_NOT_FOUND -> Status.NOT_FOUND.withDescription("Задачи с таким id не существует");
            case CATALOG_NOT_FOUND -> Status.NOT_FOUND.withDescription("Каталога с таким id не существует");
//            case INVALID_PASSWORD -> Status.UNAUTHENTICATED;
            default -> Status.UNKNOWN;
        };
    }
}
