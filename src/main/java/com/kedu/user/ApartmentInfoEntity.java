package com.kedu.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApartmentInfoEntity {
    private int i_str;
    @JsonProperty("거래금액")
    private String deal_amt;
    @JsonProperty("건축년도")
    private String build_yr;
    @JsonProperty("년")
    private String deal_yr;
    @JsonProperty("월")
    private String deal_mth;
    @JsonProperty("일")
    private String deal_day;
    @JsonProperty("법정동")
    private String dong;
    @JsonProperty("아파트")
    private String apt_nm;
    @JsonProperty("지번")
    private String str;
    @JsonProperty("전용면적")
    private float afeu;
    @JsonProperty("층")
    private int flr;

    private int locd;

    public void setDeal_amt(String str){
        this.deal_amt = str.trim().replace(",","");
    }
}
