package O2O.repository;

import O2O.Bean.O2O.O2OAddress;
import O2O.Bean.O2O.O2OAddressData;

/**
 * @Author: Sean Pan
 * @ClassName O2OContext
 * @Description TODO
 * @date 2021/11/28 16:02
 * @Version 1.0
 */
public class O2OContext {
    private String o2oToken;
    private int o2oAddressID;
    private O2OAddress o2oAddress;
    private O2OAddressData o2OAddressData;

    public String getO2oToken() {
        return o2oToken;
    }

    public void setO2oToken(String o2oToken) {
        this.o2oToken = o2oToken;
    }

    public int getO2oAddressID() {
        return o2oAddressID;
    }

    public void setO2oAddressID(int o2oAddressID) {
        this.o2oAddressID = o2oAddressID;
    }



    public O2OAddressData getO2OAddressData() {
        return o2OAddressData;
    }

    public void setO2OAddressData(O2OAddressData o2OAddressData) {
        this.o2OAddressData = o2OAddressData;
    }

    public O2OAddress getO2oAddress() {
        return o2oAddress;
    }

    public void setO2oAddress(O2OAddress o2oAddress) {
        this.o2oAddress = o2oAddress;
    }


}
