package O2O.Bean;

import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;

/**
 * @Author: Sean_Pan
 * @ClassName RodsTrade
 * @Description TODO
 * @date 12/2/2021 3:41 PM
 * @Version 1.0
 */
public class RodsTrade {

    @SerializedName("trade-event-type")
    @CsvBindByName(column = "TradeEventType")
    public String tradeEventType;


    @CsvBindByName(column = "Status")
    public String status;

    @SerializedName("primary-amount")
    @CsvBindByName(column = "PrimaryAmount")
    private String primaryAmount;

    @SerializedName("trade-uti")
    @CsvBindByName(column = "TradeUti")
    public String tradeUti;


    @SerializedName("po-group")
    @CsvBindByName(column = "PoGroup")
    public String poGroup;


    @CsvBindByName(column = "UseCaseID")
    public String useCaseID;

    @SerializedName("btb-type")
    @CsvBindByName(column = "BtbType")
    public String btbType;


    @SerializedName("view-type")
    @CsvBindByName(column = "ViewType")
    public String viewType;

    @SerializedName("swap-leg")
    @CsvBindByName(column = "SwapLeg")
    public String swapLeg;


    public String getSwapLeg() {
        return swapLeg;
    }

    public void setSwapLeg(String swapLeg) {
        this.swapLeg = swapLeg;
    }


    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }


    public String getBtbType() {
        return btbType;
    }

    public void setBtbType(String btbType) {
        this.btbType = btbType;
    }


    public String getUseCaseID() {
        return useCaseID;
    }

    public void setUseCaseID(String useCaseID) {
        this.useCaseID = useCaseID;
    }


    public String getPoGroup() {
        return poGroup;
    }

    public void setPoGroup(String poGroup) {
        this.poGroup = poGroup;
    }


    public String getTradeUti() {
        return tradeUti;
    }

    public void setTradeUti(String tradeUti) {
        this.tradeUti = tradeUti;
    }



    public String getTradeEventType() {
        return tradeEventType;
    }

    public void setTradeEventType(String tradeEventType) {
        this.tradeEventType = tradeEventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrimaryAmount() {
        return primaryAmount;
    }

    public void setPrimaryAmount(String primaryAmount) {
        this.primaryAmount = primaryAmount;
    }



}
