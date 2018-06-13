package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CouponBO;
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

public class CouponListParsar {
    public static Map parseCoupons(String response){
        Map coupMap = new HashMap();
        List<CouponBO> couponBOList = new ArrayList<CouponBO>();
        if(response.length()>0){

            try{


                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("info");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                            CouponBO couponBO = new CouponBO();
                            if (jsonObject != null){

                                if (!jsonObject.isNull("coupon_id")) {
                                    couponBO.setId(jsonObject.getString("coupon_id"));
                                } else {
                                    couponBO.setId("");
                                }
                                if (!jsonObject.isNull("name")) {
                                    couponBO.setName(jsonObject.getString("name"));
                                } else {
                                    couponBO.setName("");
                                }
                                if (!jsonObject.isNull("code")) {
                                    couponBO.setCode(jsonObject.getString("code"));
                                } else {
                                    couponBO.setCode("");
                                }
                                if (!jsonObject.isNull("discount")) {
                                    couponBO.setDis(jsonObject.getString("discount"));
                                } else {
                                    couponBO.setDis("");
                                }
                                if (!jsonObject.isNull("date_start")) {
                                    couponBO.setDatestart(jsonObject.getString("date_start"));
                                } else {
                                    couponBO.setDatestart("");
                                }
                                if(!jsonObject.isNull("date_end")){
                                    couponBO.setDateend(jsonObject.getString("date_end"));
                                }else{
                                    couponBO.setDateend("");
                                }
                                if(!jsonObject.isNull("status")){
                                    couponBO.setStatus(jsonObject.getString("status"));
                                }else{
                                    couponBO.setStatus("");
                                }
                                if(!jsonObject.isNull("type")){
                                    couponBO.setType(jsonObject.getString("type"));
                                }else{
                                    couponBO.setType("");
                                }
                                if(!jsonObject.isNull("customer_group")){
                                    couponBO.setCusgroup(jsonObject.getString("customer_group"));
                                }else{
                                    couponBO.setCusgroup("");
                                }

                                if(!jsonObject.isNull("total")){
                                    couponBO.setTot(jsonObject.getString("total"));
                                }else{
                                    couponBO.setTot("");
                                }

                                if(!jsonObject.isNull("uses_total")){
                                    couponBO.setUser_percoupon(jsonObject.getString("uses_total"));
                                }else{
                                    couponBO.setUser_percoupon("");
                                }

                                if(!jsonObject.isNull("customer_group_id")){
                                    couponBO.setCusgroup(jsonObject.getString("customer_group_id"));
                                }else{
                                    couponBO.setCusgroup("");
                                }

                                if(!jsonObject.isNull("uses_customer")){
                                    couponBO.setUser_percust(jsonObject.getString("uses_customer"));
                                }else{
                                    couponBO.setUser_percust("");
                                }


                            }



                            couponBOList.add(couponBO);
                        }

                    }
                    coupMap.put("coupon_list",couponBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return coupMap;
    }

}

