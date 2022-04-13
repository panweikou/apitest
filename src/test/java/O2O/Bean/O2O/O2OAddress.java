package O2O.Bean.O2O;


import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: Sean_Pan
 * @ClassName AddAdress
 * @Description TODO
 * @date 12/20/2021 3:37 PM
 * @Version 1.0
 */

public class O2OAddress {

    private String house_number;
    private String contacts_name;
    private String detailed_address;
    private String contacts_phone;
    @SerializedName("long")
    private double Long;
    private double latitude;
    private String contacts_phone_area;
    private String token;
    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
    public String getHouse_number() {
        return house_number;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }
    public String getContacts_name() {
        return contacts_name;
    }

    public void setDetailed_address(String detailed_address) {
        this.detailed_address = detailed_address;
    }
    public String getDetailed_address() {
        return detailed_address;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }
    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setLong(double Long) {
        this.Long = Long;
    }
    public double getLong() {
        return Long;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setContacts_phone_area(String contacts_phone_area) {
        this.contacts_phone_area = contacts_phone_area;
    }
    public String getContacts_phone_area() {
        return contacts_phone_area;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}