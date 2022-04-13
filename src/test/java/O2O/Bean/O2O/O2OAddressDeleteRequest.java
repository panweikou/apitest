package O2O.Bean.O2O;

/**
 * @Author: Sean_Pan
 * @ClassName O2OAddressDeleteRequest
 * @Description TODO
 * @date 12/22/2021 5:50 PM
 * @Version 1.0
 */
public class O2OAddressDeleteRequest {
    private int[] id_arr;
    private String token;

    public int[] getId_arr() {
        return id_arr;
    }

    public void setId_arr(int[] id_arr) {
        this.id_arr = id_arr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
