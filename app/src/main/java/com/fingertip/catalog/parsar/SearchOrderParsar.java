package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.OrdersBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 19-05-2018.
 */

public class SearchOrderParsar
{
    public static Map parseOrders(String response){
    Map orderMap = new HashMap();
    List<OrdersBO> ordersBOList = new ArrayList<OrdersBO>();
    if(response.length()>0){

        try{


            JSONObject mainJsonObject = new JSONObject(response);
            if (mainJsonObject != null){
                JSONArray mainJsonArray = mainJsonObject.getJSONArray("info");
                if (mainJsonArray != null){
                    for (int i=0; i<mainJsonArray.length(); i++){
                        JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                        OrdersBO ordersBO = new OrdersBO();
                        if (jsonObject != null){
//


                            if (!jsonObject.isNull("id")) {
                                ordersBO.setId(jsonObject.getString("id"));
                            } else {
                                ordersBO.setId("");
                            }
                            if (!jsonObject.isNull("creation_date")) {
                                ordersBO.setDate(jsonObject.getString("creation_date"));
                            } else {
                                ordersBO.setDate("");
                            }
                            if (!jsonObject.isNull("invoice_id")) {
                                ordersBO.setOrderNo(jsonObject.getString("invoice_id"));
                            } else {
                                ordersBO.setOrderNo("");
                            }
                            if (!jsonObject.isNull("email")) {
                                ordersBO.setEmail(jsonObject.getString("email"));
                            } else {
                                ordersBO.setEmail("");
                            }
                            if (!jsonObject.isNull("mobile")) {
                                ordersBO.setMobile(jsonObject.getString("mobile"));
                            } else {
                                ordersBO.setMobile("");
                            }
                            if(!jsonObject.isNull("total")){
                                ordersBO.setTotal(jsonObject.getString("total"));
                            }else{
                                ordersBO.setTotal("");
                            }
                            if(!jsonObject.isNull("status_name")){
                                ordersBO.setOrderstat(jsonObject.getString("status_name"));
                            }else{
                                ordersBO.setOrderstat("");
                            }
                            if(!jsonObject.isNull("first_name")){
                                ordersBO.setCusfname(jsonObject.getString("first_name"));
                            }else{
                                ordersBO.setCusfname("");
                            }
                            if(!jsonObject.isNull("last_name")){
                                ordersBO.setCuslname(jsonObject.getString("last_name"));
                            }else{
                                ordersBO.setCuslname("");
                            }



                        }



                        ordersBOList.add(ordersBO);
                    }

                }
                orderMap.put("search1_list",ordersBOList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    return orderMap;
}

}




