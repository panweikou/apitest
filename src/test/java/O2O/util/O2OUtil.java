package O2O.util;

import com.alibaba.fastjson.JSON;
import io.cucumber.messages.internal.com.google.common.reflect.TypeToken;
import O2O.Bean.*;
import O2O.Bean.O2O.*;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Sean_Pan
 * @ClassName O2OUtil
 * @Description TODO
 * @date 11/29/2021 5:13 PM
 * @Version 1.0
 */
public class O2OUtil {
    private static final Logger logger = LoggerFactory.getLogger(O2OUtil.class);
    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    static {
        formatter.setTimeZone(TimeZone.getTimeZone("GTM"));
    }

    public static List<RodsTrade> getLinkedTradeListInRods(ContentResponse response) {
        Type typeOfDTOList = new TypeToken<List<RodsTrade>>() {
        }.getType();
        return JSON.parseObject(response.getContentAsString(), typeOfDTOList);
    }

    public static RodsTrade getUniqueClientTradeInRods(List<RodsTrade> rodsTradeList) {
        RodsTrade clientRodsTrade = null;
        // filter client trade
        boolean clientPresent = rodsTradeList.stream().anyMatch(rodsTrade -> {
            if (rodsTrade.getBtbType().equals("CLIENT")) {
                return true;
            }
            return false;
        });
        if (clientPresent) {
            clientRodsTrade = rodsTradeList.stream().filter(rodsTrade -> {
                if (rodsTrade.getBtbType().equals("CLIENT")) {
                    return rodsTrade.getViewType().equalsIgnoreCase("primary");
                }
                return false;
            }).findFirst().get();
        }
        return clientRodsTrade;
    }


    public static List<RodsTrade> getUniqueBTBTradeInRods(List<RodsTrade> rodsTradeList) {
        List<RodsTrade> BTBRodsTrade;
        // filter BTB trade
        BTBRodsTrade = rodsTradeList.stream().filter(rodsTrade -> {
            if (!rodsTrade.getBtbType().equals("CLIENT")) {
                if (rodsTrade.getSwapLeg() != null) {
                    return rodsTrade.getSwapLeg().equalsIgnoreCase("near leg");
                } else {
                    return !rodsTrade.getBtbType().equals("CLIENT");
                }
            }
            return false;
        }).collect(Collectors.toList());
        return BTBRodsTrade;
    }

    public static List<RodsTrade> getUniqueTradesInRods(List<RodsTrade> rodsTradeList) {
        List<RodsTrade> rodsTrades = new ArrayList<>();
        RodsTrade clientRodsTrade;
        List<RodsTrade> BTBRodsTrade;
        // get client trade
        clientRodsTrade = getUniqueClientTradeInRods(rodsTradeList);
        // get BTB trade
        BTBRodsTrade = getUniqueBTBTradeInRods(rodsTradeList);
        // combine client trade and BTBTrade
        if (clientRodsTrade != null) {
            rodsTrades.add(clientRodsTrade);
        }
        rodsTrades.addAll(BTBRodsTrade);
        return rodsTrades;
    }

    public static List<RodsTrade> setCaseIdForRodsTrades(List<RodsTrade> rodsTradeList, String useCaseID) {
        rodsTradeList.forEach(rodsTrade -> {
            rodsTrade.setUseCaseID(useCaseID);
        });
        return rodsTradeList;
    }

    public static List<RodsTrade> getApacTrades(List<RodsTrade> rodsTradeList) {
        return rodsTradeList.stream()
                .filter(rodsTrade -> Common.TREATES_AVAILABLE_REGION_SET.contains(rodsTrade.getPoGroup()))
                .collect(Collectors.toList());
    }

    public static Set<String> getApacUtiInRods(List<RodsTrade> rodsTradeList) {
        List<RodsTrade> tradeList = getApacTrades(rodsTradeList);
        if (tradeList.size() > 0) {
            return tradeList.stream()
                    .map(RodsTrade::getTradeUti).collect(Collectors.toSet());
        }
        return null;
    }

    public static String getCredentials (String userName) {
        return Base64.getEncoder().encodeToString(userName.getBytes());

    }

    public static O2OLoginResponse getLoginResponse(ContentResponse response) {
        Type typeOfDTO = new TypeToken<O2OLoginResponse>() {
        }.getType();
        return JSON.parseObject(response.getContentAsString(), typeOfDTO);
    }

    public static O2OAddressResponse getO2OAddress(ContentResponse response) {
        Type typeOfDTO = new TypeToken<O2OAddressResponse>() {
        }.getType();
        return JSON.parseObject(response.getContentAsString(), typeOfDTO);
    }

    public static O2OAddressListResponse getO2OAddressList(ContentResponse response) {
        Type typeOfDTO = new TypeToken<O2OAddressListResponse>() {
        }.getType();
        return JSON.parseObject(response.getContentAsString(), typeOfDTO);
    }

    public static O2OUpdateAddressResponse getUpdateO2OAddress(ContentResponse response) {
        Type typeOfDTO = new TypeToken<O2OUpdateAddressResponse>() {
        }.getType();
        return JSON.parseObject(response.getContentAsString(), typeOfDTO);
    }


}

