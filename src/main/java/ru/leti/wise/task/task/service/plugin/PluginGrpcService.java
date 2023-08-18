package ru.leti.wise.task.task.service.plugin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphGrpc.GenerateGraphRequest;
import ru.leti.wise.task.graph.GraphOuterClass.Graph;
import ru.leti.wise.task.plugin.PluginGrpc;
import ru.leti.wise.task.plugin.PluginOuterClass;


@Component
@RequiredArgsConstructor
public class PluginGrpcService {

    private final PluginStubHolder pluginStubHolder;

    public String checkPluginSolution(PluginOuterClass.Solution solution) {
        var request = PluginGrpc.CheckPluginSolutionRequest.newBuilder()
                .setSolution(solution)
                .build();
       return pluginStubHolder.get().checkPluginSolution(request).getResult();
    }

//    public Graph getGraph(int edgeCount, int vertexCount, boolean isDirect) {
//        var request = GenerateGraphRequest.newBuilder()
//                .setEdgeCount(edgeCount)
//                .setVertexCount(vertexCount)
//                .setIsDirect(isDirect)
//                .build();
//
//        return pluginStubHolder.get().generateRandomGraph(request).getGraph();
//    }
//
//    public Graph createGraph(Graph graph) {
//        var request = GraphGrpc.CreateGraphRequest.newBuilder()
//                .setGraph(graph)
//                .build();
//
//        return pluginStubHolder.get().createGraph(request).getGraph();
//    }
}
