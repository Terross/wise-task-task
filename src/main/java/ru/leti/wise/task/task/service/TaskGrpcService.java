package ru.leti.wise.task.task.service;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.lognet.springboot.grpc.recovery.GRpcServiceAdvice;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.TaskServiceGrpc;
import ru.leti.wise.task.task.error.BusinessException;
import ru.leti.wise.task.task.error.GrpcErrorHandler;
import ru.leti.wise.task.task.helper.LogInterceptor;
import ru.leti.wise.task.task.logic.*;

import java.util.UUID;

@Slf4j
@GRpcService(interceptors = { LogInterceptor.class })
@RequiredArgsConstructor
public class TaskGrpcService extends TaskServiceGrpc.TaskServiceImplBase {

    private final GetTaskOperation getTaskOperation;
    private final GetTasksOperation getTasksOperation;
//    private final SolveTaskOperation solveTaskOperation;
    private final UpdateTaskOperation updateTaskOperation;
    private final DeleteTaskOperation deleteTaskOperation;
    private final CreateTaskOperation createTaskOperation;
//    private final GetTaskSolutionOperation getTaskSolutionOperation;
//    private final GetTaskSolutionsOperation getTaskSolutionsOperation;
//    private final GetUserSolutionStatisticOperation getUserSolutionStatisticOperation;

    @Override
    public void getTask(TaskGrpc.GetTaskRequest request, StreamObserver<TaskGrpc.GetTaskResponse> responseObserver) {
        responseObserver.onNext(getTaskOperation.activate(UUID.fromString(request.getId())));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTask(Empty request, StreamObserver<TaskGrpc.GetAllTaskResponse> responseObserver) {
        responseObserver.onNext(getTasksOperation.activate());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTask(TaskGrpc.DeleteTaskRequest request, StreamObserver<Empty> responseObserver) {
        deleteTaskOperation.activate(UUID.fromString(request.getId()));
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void createTask(TaskGrpc.CreateTaskRequest request, StreamObserver<TaskGrpc.CreateTaskResponse> responseObserver) {
        responseObserver.onNext(createTaskOperation.activate(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateTask(TaskGrpc.UpdateTaskRequest request, StreamObserver<TaskGrpc.UpdateTaskResponse> responseObserver) {
        responseObserver.onNext(updateTaskOperation.activate(request));
        responseObserver.onCompleted();
    }

    @Override
    public void solveTask(TaskGrpc.SolveTaskRequest request, StreamObserver<TaskGrpc.SolveTaskResponse> responseObserver) {
        super.solveTask(request, responseObserver);
    }

    @Override
    public void getTaskSolution(TaskGrpc.GetTaskSolutionRequest request, StreamObserver<TaskGrpc.GetTaskSolutionResponse> responseObserver) {
        super.getTaskSolution(request, responseObserver);
    }

    @Override
    public void getAllTaskSolutions(TaskGrpc.GetAllTaskSolutionsRequest request, StreamObserver<TaskGrpc.GetAllTaskSolutionsResponse> responseObserver) {
        super.getAllTaskSolutions(request, responseObserver);
    }

    @Override
    public void getUserSolutionStatistic(TaskGrpc.GetUserSolutionStatisticRequest request, StreamObserver<TaskGrpc.GetAllTaskSolutionsResponse> responseObserver) {
        super.getUserSolutionStatistic(request, responseObserver);
    }

    @GRpcServiceAdvice
    @RequiredArgsConstructor
    static class ErrorHandler {
        private final GrpcErrorHandler grpcErrorHandler;

        @GRpcExceptionHandler
        public Status handleBusinessException(BusinessException e, GRpcExceptionScope scope) {
            return grpcErrorHandler.processError(e);
        }
    }
}
