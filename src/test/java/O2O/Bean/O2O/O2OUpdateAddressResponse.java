package O2O.Bean.O2O;

/**
 * @Author: Sean_Pan
 * @ClassName O2OUpdateAddressResponse
 * @Description TODO
 * @date 12/22/2021 3:45 PM
 * @Version 1.0
 */
public class O2OUpdateAddressResponse {
    private int code;
    private String msg;
    private String[] data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

}
