package mc.com.serverSite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerPropertyLoader {

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.host}")
    private String serverHost;

    public String getServerHost() {
        return this.serverHost;
    }

    public String getServerPort() {
        return this.serverPort;
    }

    public String getServerBaseUrl() {
        return this.serverHost + ":" + this.serverPort;
    }

}