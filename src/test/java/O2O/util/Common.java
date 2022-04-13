package O2O.util;

import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableSet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Sean_Pan
 * @ClassName Common
 * @Description TODO
 * @date 11/30/2021 11:14 AM
 * @Version 1.0
 */
public class Common {
    public static final String ENV ;
    public static final String LOCATION;
    public static final String DEBUG_MODE;
    public static final String USER_PATH;
    public static final String OS_NAME;

    public static final String TREATS_QUERY_PATH = "/treats-status-query/v1/status/treats";
    public static final int RETRY_COUNT = 5;

    public static final String ENV_LOCAL = "local";
    public static final String ENV_SIT = "sit";
    public static final String ENV_UAT = "uat";
    public static final String ENV_PRD = "prd";

    public static final String LOCATION_LN = "ldn";
    public static final String LOCATION_HK = "hk";
    public static final String LOCATION_NY = "ny";
    public static final String[] BULK_TRADE_CSV_HEADER = {"Remark","Result","CounterParty"};
    public static final String[] O2O_ADDRESS_EXCLUDED_FIELDS = {"id"};
    public static final String EMEPTY_STRING = "";
    public static final String RODS_TOKEN = "Basic eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZGV2LW8yby1hcGkueGh0LWt5eS5jb21cL2FwaVwvYnV5ZXJcL3Bhc3N3b3JkTG9naW4iLCJpYXQiOjE2Mzk5NzI5NTYsImV4cCI6MTY0MjU2NDk1NiwibmJmIjoxNjM5OTcyOTU2LCJqdGkiOiI2R3VMWkExY2swSjRZdWtQIiwic3ViIjoxMDc3LCJwcnYiOiI4NjY1YWU5Nzc1Y2YyNmY2YjhlNDk2Zjg2ZmE1MzZkNjhkZDcxODE4In0.r216ERH-Saix0IdtyaqNuvCpr_N402K7GAUkrxFuRGc";
    public static final String TLCC_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZGV2LW8yby1hcGkueGh0LWt5eS5jb21cL2FwaVwvYnV5ZXJcL3Bhc3N3b3JkTG9naW4iLCJpYXQiOjE2Mzk5NzI5NTYsImV4cCI6MTY0MjU2NDk1NiwibmJmIjoxNjM5OTcyOTU2LCJqdGkiOiI2R3VMWkExY2swSjRZdWtQIiwic3ViIjoxMDc3LCJwcnYiOiI4NjY1YWU5Nzc1Y2YyNmY2YjhlNDk2Zjg2ZmE1MzZkNjhkZDcxODE4In0.r216ERH-Saix0IdtyaqNuvCpr_N402K7GAUkrxFuRGc";
    public static final String O2O_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZGV2LW8yby1hcGkueGh0LWt5eS5jb21cL2FwaVwvYnV5ZXJcL3Bhc3N3b3JkTG9naW4iLCJpYXQiOjE2Mzk5NzI5NTYsImV4cCI6MTY0MjU2NDk1NiwibmJmIjoxNjM5OTcyOTU2LCJqdGkiOiI2R3VMWkExY2swSjRZdWtQIiwic3ViIjoxMDc3LCJwcnYiOiI4NjY1YWU5Nzc1Y2YyNmY2YjhlNDk2Zjg2ZmE1MzZkNjhkZDcxODE4In0.r216ERH-Saix0IdtyaqNuvCpr_N402K7GAUkrxFuRGc";

    static {
        ENV = System.getProperty("targetEnv", "uat");
        LOCATION = System.getProperty("region", "ldn");
        DEBUG_MODE = System.getProperty("debug", "false");
        USER_PATH = System.getProperty("user.dir");
        OS_NAME = System.getProperty("os.name");
    }

    private static Map<String, Map<String, String>> O2O_CLIENT_HOST_MAP = ImmutableMap.of(
            "uat", ImmutableMap.of(
                    "ldn","https://dev-o2o-api.xht-kyy.com",
                    "hk","https://dev-o2o-api.xht-kyy.hk.com",
                    "ny","https://dev-o2o-api.xht-kyy.us.com"
            )
    );

    public static String getO2OClientHost() {
        if (O2O_CLIENT_HOST_MAP.containsKey(Common.ENV)) {
            return O2O_CLIENT_HOST_MAP.get(Common.ENV).getOrDefault(Common.LOCATION, null);
        }
        return null;
    }


   public static final Set<String> TREATES_AVAILABLE_REGION_SET = ImmutableSet.of(
           "HKH","VNM");

   public static List<String> getValueDateServiceRouterURLs() {
       if (! VALUE_DATE_SERVICE_ROUTER_MAP.containsKey(Common.ENV)) {
            return null;
       }
       List<String> urlList = new ArrayList<>();
       urlList.addAll(VALUE_DATE_SERVICE_ROUTER_MAP.get(Common.ENV).get(Common.LOCATION));
       return urlList;
   }

   private static final Map<String, Map<String, Set<String>>> VALUE_DATE_SERVICE_ROUTER_MAP = ImmutableMap.of(
           ENV_LOCAL, ImmutableMap.of(LOCATION_LN, ImmutableSet.of(String.valueOf(Arrays.asList(
                   "localhost:38011")))
           ),
           ENV_SIT, ImmutableMap.of(
                   LOCATION_LN, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.uk",
                           "uat-erisk-vds2.uk",
                           "uat-erisk-vds3.uk")),
                   LOCATION_HK, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.hk",
                           "uat-erisk-vds2.hk",
                           "uat-erisk-vds3.hk")),
                   LOCATION_NY, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.us",
                           "uat-erisk-vds2.us",
                           "uat-erisk-vds3.us"))
           ),
           ENV_UAT, ImmutableMap.of(
                   LOCATION_LN, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.uk",
                           "uat-erisk-vds2.uk",
                           "uat-erisk-vds3.uk")),
                   LOCATION_HK, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.hk",
                           "uat-erisk-vds2.hk",
                           "uat-erisk-vds3.hk")),
                   LOCATION_NY, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.us",
                           "uat-erisk-vds2.us",
                           "uat-erisk-vds3.us"))
           ),
           ENV_PRD, ImmutableMap.of(
                   LOCATION_LN, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.uk",
                           "uat-erisk-vds2.uk",
                           "uat-erisk-vds3.uk")),
                   LOCATION_HK, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.hk",
                           "uat-erisk-vds2.hk",
                           "uat-erisk-vds3.hk")),
                   LOCATION_NY, ImmutableSet.copyOf(Arrays.asList(
                           "uat-erisk-vds1.us",
                           "uat-erisk-vds2.us",
                           "uat-erisk-vds3.us"))
           )
        );

   private static Map<String, String> COOKIE_MAP = new HashMap<String, String>() {{
       put(LOCATION_LN, null);
       put(LOCATION_HK, null);
       put(LOCATION_NY, null);
   }};

   public static String getCookie() {
       if (COOKIE_MAP.get(LOCATION) == null) {
           if ("false".equalsIgnoreCase(DEBUG_MODE)) {
               CloseableHttpClient cookieRetriever = HttpClients.createMinimal();
               HttpPut entry = new HttpPut("https://evolve-internal.uat.com");
               entry.addHeader("Authorization", "Basic dkfejfeife");
               try {
                   StringEntity sE = new StringEntity(O2O_CLIENT_HOST_MAP.get("uat").get(LOCATION) + "/evolve");
                   entry.setEntity(sE);
                   CloseableHttpResponse response = cookieRetriever.execute(entry);
                   String responseBody = EntityUtils.toString(response.getEntity());
                   COOKIE_MAP.put(LOCATION, responseBody);
               } catch (IOException e) {
                   throw new RuntimeException("Failed to retrieve Cookie for the service ");
               }
           } else {
               try {
                   Process process = Runtime.getRuntime().exec("curl -X POST https://evolve-internal.uat.com --negotiate --header");
                   BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                   String line;
                   Pattern r = Pattern.compile("set-Cookie: (.*)");
                   while ((line = reader.readLine()) != null) {
                       Matcher m = r.matcher(line);
                       if (m.find()) {
                           COOKIE_MAP.put(LOCATION, m.group().split(":")[1].trim());
                           break;
                       }
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       return COOKIE_MAP.get(Common.LOCATION);
   }

}
