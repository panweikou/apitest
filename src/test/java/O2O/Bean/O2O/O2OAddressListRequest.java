package O2O.Bean.O2O;

/**
 * @Author: Sean_Pan
 * @ClassName O2OAddressListRequest
 * @Description TODO
 * @date 12/22/2021 2:40 PM
 * @Version 1.0
 */
public class O2OAddressListRequest {
    private int page;
    private int page_size;
    private String token;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
