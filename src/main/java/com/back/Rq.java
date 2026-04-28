package com.back;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String,String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();
        String[] cmdBits = cmd.split("\\?",2);
        String queryString = cmdBits.length > 1 ? cmdBits[1] : "";
        actionName = cmdBits[0];
        //쿼리를 & 로 분리
        String[] queryStringBits = queryString.split("&");
        for(String queryParam : queryStringBits){
            String[] queryParamBits = queryParam.split("=",2);
            String key = queryParamBits[0].trim();
            String value = queryParamBits.length > 1 ? queryParamBits[1].trim() : "";
            if (value.isEmpty()) continue;
            paramsMap.put(key,value);
        }
    }

    public String getParam(String paramName, String defaultVale) {
        // 만약 해쉬맵 키가 서치키워드타입이면 해당 value반환 아니면 ""
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        }
        return defaultVale;
    }

    public String getActionName() {
        return actionName;
    }
}
