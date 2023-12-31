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


    protected void doExport(){
        System.out.println(StrUtil.format("生产者信息 => [接口: {}] [映射: {}] [别名: {}] \r\n ", nozzle,ref,alias));
    }


    public ProviderConfig(String nozzle, String ref, String alias) {
        this.nozzle = nozzle;
        this.ref = ref;
        this.alias = alias;
    }

    public ProviderConfig() {
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

