package O2O.cucumber.stepDefinition;

import O2O.Bean.O2O.*;
import O2O.repository.O2OAddressRepository;
import O2O.repository.O2OContext;
import O2O.service.O2OService;
import O2O.util.Common;
import O2O.util.Hook;
import O2O.util.JsonUtil;
import O2O.util.O2OUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.assertEquals;

/**
 * @Author: Sean_Pan
 * @ClassName AbstractO2O
 * @Description TODO
 * @date 12/2/2021 12:18 PM
 * @Version 1.0
 */
public abstract class AbstractO2O {
    String className = this.getClass().getName();
    protected SoftAssert softAssert;
    O2OLogin o2OLogin;
    O2OAddress o2oAddress;
    O2OAddressListRequest o2OAddressListRequest;
    O2OAddressData o2OAddressData;
    O2OAddressDetailRequest o2OAddressDetailRequest;
    O2OAddressDeleteRequest o2OAddressDeleteRequest;
    O2OContext o2OContext;
    String threadName = Thread.currentThread().getName();
    private static final Logger logger = LoggerFactory.getLogger(AbstractO2O.class);
    public AbstractO2O(SoftAssert softAssert) {
        this.o2oAddress = new O2OAddress();
        this.o2OAddressListRequest = new O2OAddressListRequest();
        this.o2OAddressData = new O2OAddressData();
        this.o2OAddressDetailRequest = new O2OAddressDetailRequest();
        this.o2OAddressDeleteRequest = new O2OAddressDeleteRequest();
        this.o2OLogin = new O2OLogin();
        this.o2OContext = new O2OContext();
        this.softAssert = softAssert;

    }

    public void o2oLogin(O2OLogin o2OLogin) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().login(o2OLogin);
        O2OLoginResponse o2OAddressResponse = O2OUtil.getLoginResponse(contentResponse);
        assertEquals( contentResponse.getStatus(), 200);
        assertEquals( o2OAddressResponse.getCode(), 1);
        assertEquals( o2OAddressResponse.getMsg(), "登錄成功");
        // set token into context
        o2OContext.setO2oToken(o2OAddressResponse.getData().getToken());
        Hook.getO2OContextHap().put(threadName, o2OContext);

    }

    public void o2oAddAddress(O2OAddress o2oAddress) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().addShippingAddress(o2oAddress);
        O2OAddressResponse o2OAddressResponse = O2OUtil.getO2OAddress(contentResponse);
        assertEquals( contentResponse.getStatus(), 200);
        assertEquals( o2OAddressResponse.getCode(), 1);
        assertEquals( o2OAddressResponse.getMsg(), "新增地址成功");
        // set o2oAddress into context
        o2OContext.setO2oAddress(o2oAddress);
        // set address_id into context
        o2OContext.setO2oAddressID(o2OAddressResponse.getData().getId());
        // set address_data into context
        o2OContext.setO2OAddressData(o2OAddressResponse.getData());

    }

    public void o2oQueryAddressList(O2OAddressListRequest o2OAddressListRequest) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().queryShippingAddressList(o2OAddressListRequest);
        O2OAddressListResponse o2OAddressListResponse = O2OUtil.getO2OAddressList(contentResponse);
        assertEquals(contentResponse.getStatus(), 200);
        assertEquals(o2OAddressListResponse.getCode(), 1);
        int expectAddressId = Hook.getO2OContextHap().get(threadName).getO2oAddressID();
        int actualAddressId = o2OAddressListResponse.getData().get(0).getId();
        assertEquals(actualAddressId, expectAddressId);
    }

    public void o2oUpdateAddress(O2OAddressData o2OAddressData) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().updateShippingAddress(o2OAddressData);
        O2OUpdateAddressResponse o2OUpdateAddressResponse = O2OUtil.getUpdateO2OAddress(contentResponse);
        assertEquals(contentResponse.getStatus(), 200);
        assertEquals( o2OUpdateAddressResponse.getCode(), 1);
        assertEquals( o2OUpdateAddressResponse.getMsg(), "編輯地址成功");
        assertEquals(o2OUpdateAddressResponse.getData().length,0);
    }

    public void o2oQueryAddressDetail(O2OAddressDetailRequest o2OAddressDetailRequest, String caseID) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().queryShippingAddressDetail(o2OAddressDetailRequest);
        O2OAddressResponse o2OAddressResponse = O2OUtil.getO2OAddress(contentResponse);
        assertEquals(contentResponse.getStatus(), 200);
        assertEquals( o2OAddressResponse.getCode(), 1);
        assertEquals( o2OAddressResponse.getMsg(), "");
        // get expect response data from o2OContext
        // O2OAddressData expectO2OAddressData = o2OContext.getO2OAddressData();
        // get expect response data from repository
        O2OAddressData expectO2OAddressData = O2OAddressRepository.instance().getO2OAddressData(caseID);
        // get actual response data
        O2OAddressData actualO2OAddressData = o2OAddressResponse.getData();
        //compare JsonObj
        List<String> excludedFieldList = Arrays.asList(Common.O2O_ADDRESS_EXCLUDED_FIELDS);
        JSONObject expectedJson = (JSONObject) JSON.toJSON(expectO2OAddressData);
        JSONObject actualJson = (JSONObject) JSON.toJSON(actualO2OAddressData);
        logger.info("expectedJson:{}",expectedJson);
        logger.info("actualJson:{}",actualJson);
        // softAssert
        String msg = String.format("assert fail at %s:%s", className, Thread.currentThread().getStackTrace()[1].getMethodName());
        softAssert.assertTrue(JsonUtil.cmpJsonObject(actualJson, expectedJson, excludedFieldList), msg);
    }

    public void o2oDeleteAddress(O2OAddressDeleteRequest o2OAddressDeleteRequest) throws Exception {
        ContentResponse contentResponse = O2OService.getInstance().deleteShippingAddress(o2OAddressDeleteRequest);
        O2OUpdateAddressResponse o2OUpdateAddressResponse = O2OUtil.getUpdateO2OAddress(contentResponse);
        assertEquals(contentResponse.getStatus(), 200);
        assertEquals( o2OUpdateAddressResponse.getCode(), 1);
        assertEquals( o2OUpdateAddressResponse.getMsg(), "刪除成功");
        assertEquals(o2OUpdateAddressResponse.getData().length,0);
    }
}
