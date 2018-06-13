package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CustomerBO;
import com.fingertip.catalog.model.OrderViewBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderViewParsar {
    public static Map parseOrderViewList(String response){

        Map orderMap = new HashMap();
        List<OrderViewBO> orderViewBOList = new ArrayList<OrderViewBO>();
        if(response.length()>0){

            try{
                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("order");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);
                            if (jsonObject != null){

                                OrderViewBO orderViewBO = new OrderViewBO();
                                if(!jsonObject.isNull("product_id")){
                                    orderViewBO.setProid(jsonObject.getString("product_id"));
                                }else{
                                    orderViewBO.setProid("");
                                }
                                if(!jsonObject.isNull("order_id")){
                                    orderViewBO.setOrderid(jsonObject.getString("order_id"));
                                }else{
                                    orderViewBO.setOrderid("");
                                }
                                if(!jsonObject.isNull("name")){
                                    orderViewBO.setName(jsonObject.getString("name"));
                                }else{
                                    orderViewBO.setName("");
                                }
                                if(!jsonObject.isNull("sku")){
                                    orderViewBO.setSku(jsonObject.getString("sku"));
                                }else{
                                    orderViewBO.setSku("");
                                }
                                if(!jsonObject.isNull("quantity")){
                                    orderViewBO.setQuant(jsonObject.getString("quantity"));
                                }else{
                                    orderViewBO.setQuant("");
                                }
                                if(!jsonObject.isNull("unit_price")){
                                    orderViewBO.setUnit(jsonObject.getString("unit_price"));
                                }else{
                                    orderViewBO.setUnit("");
                                }
                                if(!jsonObject.isNull("total_price")){
                                    orderViewBO.setTotal(jsonObject.getString("total_price"));
                                }else{
                                    orderViewBO.setTotal("");
                                }
                                if(!jsonObject.isNull("image")){
                                    orderViewBO.setImage(jsonObject.getString("image"));
                                }else{
                                    orderViewBO.setImage("");
                                }



                                orderViewBOList.add(orderViewBO);
                            }
                        }
                    }
                    orderMap.put("orderview_list",orderViewBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return orderMap;
    }

}





