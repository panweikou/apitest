package O2O.Bean.O2O;

import com.alibaba.fastjson.JSON;

/**
 * @Author: Sean_Pan
 * @ClassName O2OLoginResponse
 * @Description TODO
 * @date 12/23/2021 10:41 AM
 * @Version 1.0
 */
public class O2OLoginResponse {

    private int code;
    private String msg;
    private O2OToken data;


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

    public O2OToken getData() {
        return data;
    }

    public void setData(O2OToken data) {
        this.data = data;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
