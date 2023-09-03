package ru.leti.wise.task.task.service.plugin;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.plugin.PluginServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.plugin.PluginServiceGrpc.newBlockingStub;

@Component
@Observed
@RequiredArgsConstructor
public class PluginStubHolder {

    @Value("${grpc.service.plugin.port}")
    private int port;

    @Value("${grpc.service.plugin.host}")
    private String host;

    private PluginServiceGrpc.PluginServiceBlockingStub pluginServiceStub;


    @PostConstruct
    void init() {
        pluginServiceStub = newBlockingStub(forAddress(host, port).usePlaintext().build());
    }

    PluginServiceGrpc.PluginServiceBlockingStub get() {
        return pluginServiceStub;
    }
}
