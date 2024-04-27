package ru.leti.wise.task.task.service.grpc.plugin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

    public PluginOuterClass.ImplementationResult checkPluginImplementation(String implementationFile, String pluginId) {
        var request = PluginGrpc.CheckPluginImplementationRequest.newBuilder()
                .setId(pluginId)
                .setFile(implementationFile)
                .build();
        return pluginStubHolder.get().checkPluginImplementation(request).getImplementationResult();
    }
}
