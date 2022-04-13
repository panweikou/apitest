package O2O.service;

import O2O.Bean.O2O.*;
import O2O.util.Common;
import O2O.util.HttpSender;
import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.client.api.ContentResponse;

/**
 * @Author: Sean_Pan
 * @ClassName O2OService
 * @Description TODO
 * @date 12/20/2021 4:16 PM
 * @Version 1.0
 */
public class O2OService {
    private static final O2OService instance = new O2OService();
    private static final String host = Common.getO2OClientHost();
    public static String loginPath = "/api/buyer/passwordLogin";
    public static String addAddressPath = "/api/buyer/address/add";
    public static String queryAddressListPath = "/api/buyer/address/index";
    public static String updateAddressPath = "/api/buyer/address/update";
    public static String editAddressPath = "/api/buyer/address/edit";
    public static String deleteAddressPath = "/api/buyer/address/delete";

    public static O2OService getInstance() {
        return instance;
    }

    public ContentResponse login(O2OLogin o2OLogin) throws Exception {
        String URL = host + loginPath;
        String requestBody = JSON.toJSONString(o2OLogin);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

    public ContentResponse addShippingAddress(O2OAddress o2oAddress) throws Exception {
        String URL = host + addAddressPath;
        String requestBody = JSON.toJSONString(o2oAddress);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

    public ContentResponse queryShippingAddressList(O2OAddressListRequest o2OAddressListRequest) throws Exception {
        String URL = host + queryAddressListPath;
        String requestBody = JSON.toJSONString(o2OAddressListRequest);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

    public ContentResponse updateShippingAddress(O2OAddressData o2OAddressData) throws Exception {
        String URL = host + updateAddressPath;
        String requestBody = JSON.toJSONString(o2OAddressData);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

    public ContentResponse queryShippingAddressDetail(O2OAddressDetailRequest o2OAddressDetailRequest) throws Exception {
        String URL = host + editAddressPath;
        String requestBody = JSON.toJSONString(o2OAddressDetailRequest);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

    public ContentResponse deleteShippingAddress(O2OAddressDeleteRequest o2OAddressDeleteRequest) throws Exception {
        String URL = host + deleteAddressPath;
        String requestBody = JSON.toJSONString(o2OAddressDeleteRequest);
        return HttpSender.getSender().sendPostRequest(URL,requestBody);
    }

}
