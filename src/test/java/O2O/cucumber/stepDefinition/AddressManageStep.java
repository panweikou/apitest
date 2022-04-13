package O2O.cucumber.stepDefinition;

import O2O.repository.O2OAddressRepository;
import O2O.util.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

/**
 * @Author: Sean_Pan
 * @ClassName AddressManageStep
 * @Description TODO
 * @date 12/20/2021 3:30 PM
 * @Version 1.0
 */
public class AddressManageStep extends AbstractO2O {
    private static final Logger logger = LoggerFactory.getLogger(AddressManageStep.class);

    public AddressManageStep(SoftAssert softAssert) {
        super(softAssert);
    }


    @Given("User login of {string}")
    public void userUserNameLogin(String caseID) throws Exception {
        o2OLogin = O2OAddressRepository.instance().getO2OLogins(caseID);
        o2oLogin(o2OLogin);
    }

    @When("Add address of {string}")
    public void addAddress(String caseID) throws Exception {
        o2oAddress = O2OAddressRepository.instance().getO2OAddresses(caseID);
        o2oAddress.setToken(o2OContext.getO2oToken());
        o2oAddAddress(o2oAddress);

    }

    @And("Query address list")
    public void queryAddressList() throws Exception {
        o2OAddressListRequest.setPage(1);
        o2OAddressListRequest.setPage_size(10);
        o2OAddressListRequest.setToken(o2OContext.getO2oToken());
        o2oQueryAddressList(o2OAddressListRequest);

    }

    @And("Update address")
    public void updateAddress() throws Exception {
        o2OAddressData = Hook.getO2OContextHap().get(threadName).getO2OAddressData();
        o2OAddressData.setToken(o2OContext.getO2oToken());
        o2OAddressData.setDetailed_address("Pepper Lunch BaoAn district");
        o2oUpdateAddress(o2OAddressData);
    }

    @And("Query address detail of {string}")
    public void queryAddressDetail(String caseID) throws Exception {
        o2OAddressDetailRequest.setId(String.valueOf(o2OAddressData.getId()));
        o2OAddressDetailRequest.setToken(o2OContext.getO2oToken());
        o2oQueryAddressDetail(o2OAddressDetailRequest, caseID);
    }

    @And("Delete address")
    public void deleteAddress() throws Exception {
        int[] addressIds = {0};
        addressIds[0] = o2OAddressData.getId();
        o2OAddressDeleteRequest.setId_arr(addressIds);
        o2OAddressDeleteRequest.setToken(o2OContext.getO2oToken());
        o2oDeleteAddress(o2OAddressDeleteRequest);

    }

    @And("All steps are successful")
    public void assertAll() {
        softAssert.assertAll();
    }

}
