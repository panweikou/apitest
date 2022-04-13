package O2O.util;

/**
 * @Author: Sean_Pan
 * @ClassName JsonUtil
 * @Description TODO
 * @date 12/29/2021 5:48 PM
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 判断实际结果和预期结果是否一致，在预期结果中可以去除不需要验证的key
     *
     * @param actualJson
     * @param exceptedJson
     * @return
     */
    public static Boolean cmpJsonObject(JSONObject actualJson, JSONObject exceptedJson, List<String> excludedFields) {
        List<String> resultList = new LinkedList<>();
        for (Map.Entry<String, Object> actualEntry : actualJson.entrySet()) {
            for (Map.Entry<String, Object> exceptedEntry : exceptedJson.entrySet()) {
                if (!excludedFields.contains(actualEntry.getKey())) {
                    if (actualEntry.getKey().equals(exceptedEntry.getKey())) {
                        if(actualEntry.getValue() != null && exceptedEntry.getValue() != null) {
                            if (!actualEntry.getValue().equals(exceptedEntry.getValue())) {
                                resultList.add(actualEntry.getKey());
                            }
                        }
                    }
                }
            }
        }
        if (resultList.size() > 0) {
            Map<String, String> theMap = new HashMap<String, String>();
            for (String s : resultList) {
                theMap.put(s, actualJson.getString(s) + "/" + exceptedJson.getString(s));
            }
            String msg = "共有" + resultList.size() + "对数据不匹配, 数据如下：\n"
                    + "key:actual/expect \n"
                    + theMap;
            logger.error(msg);
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param actualJsonArray
     * @param exceptedJsonArray
     * @return
     */
    public static Boolean cmpJsonArray(JSONArray actualJsonArray, JSONArray exceptedJsonArray, List<String> excludedFields) throws Exception {
        List<String> resultList = new LinkedList<>();
        if (actualJsonArray.size() == exceptedJsonArray.size()) {
            for (int i = 0; i < actualJsonArray.size(); i++) {
                if (verifyJsonObject(actualJsonArray.getString(i)) && verifyJsonObject(exceptedJsonArray.getString(i))) {
                    if (!cmpJsonObject(actualJsonArray.getJSONObject(i), exceptedJsonArray.getJSONObject(i), excludedFields)) {
                        resultList.add(String.valueOf(i));
                    }
                } else {
                    if (!actualJsonArray.get(i).equals(exceptedJsonArray.get(i))) {
                        resultList.add(String.valueOf(i));
                    }
                }
            }
        }
        if (resultList.size() > 0) {
            logger.info("共有--->" + resultList.size() + "<---对键值对不匹配");
            for (String s : resultList) {
                logger.info(resultList.indexOf(s) + ":");
                logger.info("实际值：" + actualJsonArray.getString(Integer.valueOf(s)));
                logger.info("预期值：" + exceptedJsonArray.getString(Integer.valueOf(s)));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验是否是json串
     *
     * @param jsonString
     * @return Boolean
     */
    private static Boolean verifyJson(String jsonString) {
        try {
            JSON.parse(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否是JSONObject类型的json
     *
     * @param jsonString
     * @return Boolean
     */
    public static Boolean verifyJsonObject(String jsonString) {
        if (!verifyJson(jsonString)) {
            return false;
        }
        try {
            JSONObject.parseObject(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否是JSONArray类型的json
     *
     * @param jsonString
     * @return Boolean
     */
    public static Boolean verifyJsonArray(String jsonString) {
        if (!verifyJson(jsonString)) {
            return false;
        }
        try {
            JSONArray.parseArray(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}