package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.OrdersBO;
import com.fingertip.catalog.model.ProductBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 19-05-2018.
 */

public class OrderListParsar {
    public static Map parseOrders(String response){
        Map orderMap = new HashMap();
        List<OrdersBO> ordersBOList = new ArrayList<OrdersBO>();
        if(response.length()>0){

            try{


                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("order_info");
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


                                    String segments[] = jsonObject.getString("creation_date").split(" ");
// Grab the last segment

                                    String firstSubString = segments[0];

                                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
                                    Date date = (Date)formatter.parse(firstSubString);
                                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    String finalString = newFormat.format(date);
                                    System.out.println("fileaa"+finalString);
                                    ordersBO.setDate(finalString);
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
                                if(!jsonObject.isNull("customer_coment")){
                                    ordersBO.setComment(jsonObject.getString("customer_coment"));
                                }else{
                                    ordersBO.setComment("");
                                }

                                if(!jsonObject.isNull("payment_address")){
                                    ordersBO.setAddress(jsonObject.getString("payment_address"));
                                }else{
                                    ordersBO.setAddress("");
                                }

                                if(!jsonObject.isNull("payment_country")){
                                    ordersBO.setCountry(jsonObject.getString("payment_country"));
                                }else{
                                    ordersBO.setCountry("");
                                }

                                if(!jsonObject.isNull("payment_zone")){
                                    ordersBO.setState(jsonObject.getString("payment_zone"));
                                }else{
                                    ordersBO.setState("");
                                }

                                if(!jsonObject.isNull("payment_city")){
                                    ordersBO.setCity(jsonObject.getString("payment_city"));
                                }else{
                                    ordersBO.setCity("");
                                }

                                if(!jsonObject.isNull("payment_pincode")){
                                    ordersBO.setPincode(jsonObject.getString("payment_pincode"));
                                }else{
                                    ordersBO.setPincode("");
                                }
                                if(!jsonObject.isNull("shipping_charge")){
                                    ordersBO.setShip_charge(jsonObject.getString("shipping_charge"));
                                }else{
                                    ordersBO.setShip_charge("");
                                }
                                if(!jsonObject.isNull("admin_approval")){
                                    ordersBO.setOrd_app(jsonObject.getString("admin_approval"));
                                }else{
                                    ordersBO.setOrd_app("");
                                }
                                if(!jsonObject.isNull("remark")){
                                    ordersBO.setRemark(jsonObject.getString("remark"));
                                }else{
                                    ordersBO.setRemark("");
                                }



                            }



                            ordersBOList.add(ordersBO);
                        }

                    }
                    orderMap.put("order_list",ordersBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return orderMap;
    }

}



