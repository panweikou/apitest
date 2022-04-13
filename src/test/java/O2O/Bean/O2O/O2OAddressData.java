package O2O.Bean.O2O;

import com.alibaba.fastjson.JSON;
import com.opencsv.bean.CsvBindByName;

/**
 * @Author: Sean_Pan
 * @ClassName O2OAddressData
 * @Description TODO
 * @date 12/20/2021 5:09 PM
 * @Version 1.0
 */

public class O2OAddressData {


    @CsvBindByName(column = "case_id")
    public String useCaseID;

    @CsvBindByName(column = "id")
    public int id;

    @CsvBindByName(column = "contacts_name")
    public String contacts_name;

    @CsvBindByName(column = "contacts_phone_area")
    public String contacts_phone_area;

    @CsvBindByName(column = "contacts_phone")
    public String contacts_phone;

    @CsvBindByName(column = "detailed_address")
    public String detailed_address;

    @CsvBindByName(column = "house_number")
    public String house_number;

    @CsvBindByName(column = "long")
    private String Long;

    @CsvBindByName(column = "latitude")
    public String latitude;

    @CsvBindByName(column = "province")
    public int province;

    @CsvBindByName(column = "city")
    public int city;

    @CsvBindByName(column = "area")
    public int area;

    @CsvBindByName(column = "poiname")
    public String poiname;

    @CsvBindByName(column = "abcode")
    public String abcode;

    public int is_default;
    public String province_name;
    public String city_name;
    public String dist_name;
    public String token;


    public String getUseCaseID() {
        return useCaseID;
    }

    public void setUseCaseID(String useCaseID) {
        this.useCaseID = useCaseID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDist_name() {
        return dist_name;
    }

    public void setDist_name(String dist_name) {
        this.dist_name = dist_name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }
    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_phone_area(String contacts_phone_area) {
        this.contacts_phone_area = contacts_phone_area;
    }
    public String getContacts_phone_area() {
        return contacts_phone_area;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }
    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setDetailed_address(String detailed_address) {
        this.detailed_address = detailed_address;
    }
    public String getDetailed_address() {
        return detailed_address;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
    public String getHouse_number() {
        return house_number;
    }

    public void setLong(String Long) {
        this.Long = Long;
    }
    public String getLong() {
        return Long;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setProvince(int province) {
        this.province = province;
    }
    public int getProvince() {
        return province;
    }

    public void setCity(int city) {
        this.city = city;
    }
    public int getCity() {
        return city;
    }

    public void setArea(int area) {
        this.area = area;
    }
    public int getArea() {
        return area;
    }

    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }
    public String getPoiname() {
        return poiname;
    }

    public void setAbcode(String abcode) {
        this.abcode = abcode;
    }
    public String getAbcode() {
        return abcode;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
