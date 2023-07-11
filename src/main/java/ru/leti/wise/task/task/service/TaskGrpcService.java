package ru.leti.wise.task.task.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.TaskServiceGrpc;
import ru.leti.wise.task.task.helper.LogInterceptor;

@Slf4j
@GRpcService(interceptors = { LogInterceptor.class })
@RequiredArgsConstructor
public class TaskGrpcService extends TaskServiceGrpc.TaskServiceImplBase {

    @Override
    public void getTask(TaskGrpc.GetTaskRequest request, StreamObserver<TaskGrpc.GetTaskResponse> responseObserver) {
        super.getTask(request, responseObserver);
    }

    @Override
    public void getAllTask(Empty request, StreamObserver<TaskGrpc.GetAllTaskResponse> responseObserver) {
        super.getAllTask(request, responseObserver);
    }

    @Override
    public void deleteTask(TaskGrpc.DeleteTaskRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteTask(request, responseObserver);
    }

    @Override
    public void createTask(TaskGrpc.CreateTaskRequest request, StreamObserver<TaskGrpc.CreateTaskResponse> responseObserver) {
        super.createTask(request, responseObserver);
    }

    @Override
    public void updateTask(TaskGrpc.UpdateTaskRequest request, StreamObserver<TaskGrpc.UpdateTaskResponse> responseObserver) {
        super.updateTask(request, responseObserver);
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
}
