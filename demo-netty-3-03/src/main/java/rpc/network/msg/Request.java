package rpc.network.msg;

public class Request {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 返回结果
     */
    private Object result;

    private String channel;

    private String nozzle;

    private String ref;

    private String alias;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
