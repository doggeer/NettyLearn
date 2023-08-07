package rpc.network.msg;

/**
 * @author wc
 */
public class Response {

    private String requestId;

    private String param;

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Response(String requestId, String param) {
        this.requestId = requestId;
        this.param = param;
    }

    public Response() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
