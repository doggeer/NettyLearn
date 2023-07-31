package rpc.config;

public class ServerConfig {
    private String host;

    private String port;


    public ServerConfig(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public ServerConfig() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    protected void doExportServer(){
        System.out.println("生产者信息发布.....");
    }
}
