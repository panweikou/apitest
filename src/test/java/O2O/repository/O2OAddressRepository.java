package O2O.repository;

import O2O.Bean.O2O.O2OAddress;
import O2O.Bean.O2O.O2OAddressData;
import O2O.Bean.O2O.O2OLogin;
import O2O.util.CsvUtil;
import O2O.util.Guard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Sean Pan
 * @ClassName O2OAddressRepository
 * @Description TODO
 * @date 2021/11/28 22:12
 * @Version 1.0
 */
public class O2OAddressRepository {
    private static Logger logger = LoggerFactory.getLogger(O2OAddressRepository.class);
    private Map<String , O2OAddressData> o2oAddressDatas = new HashMap<>();
    private Map<String, O2OAddress> o2oAddresses = new HashMap<>();
    private Map<String, O2OLogin> o2OLogins = new HashMap<>();

    private static O2OAddressRepository instance = new O2OAddressRepository();

    private O2OAddressRepository() {}

    public static O2OAddressRepository instance() {return instance;};

    public void appendAllCsvOfO2OAddress(String path) throws FileNotFoundException {
        File tradeFolder = new File(path);
        if (!tradeFolder.exists()) {
            throw new IllegalArgumentException( "path not exiests:" + path + ",resolved path: " + tradeFolder.getAbsoluteFile());
        }
        Collection<File> allFiles = FileUtils.listFiles(tradeFolder, new String[]{"csv"}, true);
        CsvUtil<O2OAddressData> csvUtil = new CsvUtil<>();
        for (File file:allFiles) {
            logger.info("loading address data of output from file:" + file);
            List<O2OAddressData> addressDatas = csvUtil.csvToBean(file, O2OAddressData.class);
            if (addressDatas != null) {
                for (O2OAddressData addressData: addressDatas) {
                    o2oAddressDatas.put(addressData.useCaseID, addressData);
                }
            }
        }
    }

    public void appendAllJsonOfO2OAddress(String path) {
        File tradeFolder = new File(path);
        if (!tradeFolder.exists()) {
            throw new IllegalArgumentException( "path not exiests:" + path + ",resolved path: " + tradeFolder.getAbsoluteFile());
        }
        Collection<File> allFiles = FileUtils.listFiles(tradeFolder, new String[]{"json"}, true);
        CsvUtil<O2OAddress> csvUtil = new CsvUtil<>();
        for (File file : allFiles) {
            logger.info("loading address json from file:" + file);
            Type mapType = new TypeToken<Map<String, O2OAddress>>(){}.getType();
            Map<String, O2OAddress> addresses = loadJsonFile(file, mapType);
            if (addresses != null && ! addresses.isEmpty()) {
                addresses.forEach((useCaseID, address) -> {
                    o2oAddresses.put(useCaseID, address);
                });
            }
        }
    }
    public void appendAllJsonOfO2OLogin(String path) {
        File tradeFolder = new File(path);
        if (!tradeFolder.exists()) {
            throw new IllegalArgumentException( "path not exiests:" + path + ",resolved path: " + tradeFolder.getAbsoluteFile());
        }
        Collection<File> allFiles = FileUtils.listFiles(tradeFolder, new String[]{"json"}, true);
        CsvUtil<O2OLogin> csvUtil = new CsvUtil<>();
        for (File file : allFiles) {
            logger.info("loading address json from file:" + file);
            Type mapType = new TypeToken<Map<String, O2OLogin>>(){}.getType();
            Map<String, O2OLogin> O2OLogins = loadJsonFile(file, mapType);
            if (O2OLogins != null && ! O2OLogins.isEmpty()) {
                O2OLogins.forEach((useCaseID, login) -> {
                    o2OLogins.put(useCaseID, login);
                });
            }
        }
    }

    private Map loadJsonFile(File file, Type type) {
        try {
            String json = IOUtils.toString(new FileInputStream(file));
            return new Gson().fromJson(json, type);
        }
        catch (Exception e) {
            logger.error("'{}' Exception", e);
        }
        return null;
    }

    public void reloadO2oAddressDatas(String file) throws FileNotFoundException {
        logger.info("reloading address data from :" +file);
        CsvUtil<O2OAddressData> csvUtil = new CsvUtil<>();
        List<O2OAddressData> trades = csvUtil.csvToBean(file, O2OAddressData.class);
        o2oAddressDatas.clear();
        for (O2OAddressData trade: trades) {
            Guard.notBlank(trade.useCaseID, "missing UseCaseID -" + trade);
            Guard.isTrue(!o2oAddressDatas.containsKey(trade.useCaseID),"address data already exist with id:" + trade.useCaseID);
            o2oAddressDatas.put(trade.useCaseID, trade);
        }
    }

    public O2OAddressData getO2OAddressData(String useCaseID) {
        if (o2oAddressDatas.size() == 0) {
            throw new RuntimeException("no address data is loaded");
        }
        if (o2oAddressDatas.containsKey(useCaseID)) {
            return o2oAddressDatas.get(useCaseID);
        } else {
            throw new IllegalArgumentException("address data not found:" + useCaseID);
        }
    }

    public O2OAddress getO2OAddresses(String useCaseID) {
        if (o2oAddresses.size() == 0) {
            throw new RuntimeException("no address data is loaded");
        }
        if (o2oAddresses.containsKey(useCaseID)) {
            return o2oAddresses.get(useCaseID);
        } else {
            throw new IllegalArgumentException("address data not found:" + useCaseID);
        }
    }
    public O2OLogin getO2OLogins(String useCaseID) {
        if (o2OLogins.size() == 0) {
            throw new RuntimeException("no login data is loaded");
        }
        if (o2OLogins.containsKey(useCaseID)) {
            return o2OLogins.get(useCaseID);
        } else {
            throw new IllegalArgumentException("login data not found:" + useCaseID);
        }
    }


}
