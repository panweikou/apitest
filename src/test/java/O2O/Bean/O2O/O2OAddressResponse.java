package O2O.Bean.O2O;

import com.alibaba.fastjson.JSON;

/**
 * @Author: Sean_Pan
 * @ClassName O2OAddressResponse
 * @Description TODO
 * @date 12/20/2021 5:06 PM
 * @Version 1.0
 */
public class O2OAddressResponse {

    private int code;
    private String msg;
    private O2OAddressData data;

    public O2OAddressData getData() {
        return data;
    }

    public void setData(O2OAddressData data) {
        this.data = data;
    }

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

    public String toString() {
        return JSON.toJSONString(this);
    }
}
