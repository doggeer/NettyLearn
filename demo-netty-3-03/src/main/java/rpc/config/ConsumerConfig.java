package rpc.config;

/**
 * @author wc
 */
public class ConsumerConfig<T> {

    private String nozzle;

    private String alias;


    public ConsumerConfig(String nozzle, String alias) {
        this.nozzle = nozzle;
        this.alias = alias;
    }

    public ConsumerConfig() {
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
