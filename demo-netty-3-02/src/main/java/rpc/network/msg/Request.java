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
