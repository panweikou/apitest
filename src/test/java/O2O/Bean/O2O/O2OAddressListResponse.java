package O2O.Bean.O2O;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author: Sean_Pan
 * @ClassName O2OAddressListResponse
 * @Description TODO
 * @date 12/22/2021 2:43 PM
 * @Version 1.0
 */
public class O2OAddressListResponse {

    private int code;
    private String msg;
    private List<O2OAddressData> data;

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

    public List<O2OAddressData> getData() {
        return data;
    }

    public void setData(List<O2OAddressData> data) {
        this.data = data;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
