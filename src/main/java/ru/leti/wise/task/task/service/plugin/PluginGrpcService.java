package ru.leti.wise.task.task.service.plugin;

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
}
