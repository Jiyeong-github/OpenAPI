package com.kedu.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedu.user.VO.InsertEntity;
import com.kedu.user.VO.LocationCodeEntity;
import com.kedu.user.VO.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class TodayService {

    @Autowired
    private TodayMapper mapper;

    public List<LocationCodeEntity> selLocationCode() {
        return mapper.selLocationCode();
    }

    public void saveData(SearchDTO param) {
        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        // final String serviceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
        final String decodeServicekey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";

    /*    try {
            decodeServicekey = URLDecoder.decode(serviceKey, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        final HttpHeaders HEADERS = new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // final HttpEntity<String> entity = new HttpEntity<String>(HEADERS);

        String deal_ym = String.format("%s%02d", param.getDeal_yr(), param.getDeal_mth());
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", param.getExcd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServicekey)
                .queryParam("numOfRows", "5000")
                .build(false);

        String urlWithParam = url + "?LAWD_CD=" + param.getExcd() + "&DEAL_YMD" + deal_ym + "&serviceKey" + decodeServicekey;
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
        String result = responseEntity.getBody();

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        try {
            jsonNode = om.readTree(result);
            list = om.treeToValue(jsonNode.path("response").path("body")
                    .path("items").path("item"), ApartmentInfoEntity[].class);
            System.out.println("list.length:" + list.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ApartmentInfoEntity item : list) {
            System.out.println(item);
        }

        InsertEntity param2 = new InsertEntity();
        param2.setLocd(1);
        param2.setArr(list);

        mapper.insApartmentInfoArr(param2);
    }
}
