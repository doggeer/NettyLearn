package rpc.config;

import cn.hutool.core.util.StrUtil;

/**
 * @author wc
 */
public class ProviderConfig {

    /**
     * 接口
     */
    private String nozzle;

    /**
     * 映射
     */
    private String ref;


    /**
     * 别名
     */
    private String alias;


    private String host;

    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public ProviderConfig(String nozzle, String ref, String alias) {
        this.nozzle = nozzle;
        this.ref = ref;
        this.alias = alias;
    }


    public ProviderConfig() {
    }

    protected void doExport(){
        System.out.println(StrUtil.format("生产者信息 => [接口: {}] [映射: {}] [别名: {}] \r\n ", nozzle,ref,alias));
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

