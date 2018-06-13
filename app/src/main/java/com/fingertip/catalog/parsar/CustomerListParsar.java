package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CustomerBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 18-05-2018.
 */

public class CustomerListParsar {
    public static Map parseCustomerList(String response){

        Map customerMap = new HashMap();
        List<CustomerBO> customerBOList = new ArrayList<CustomerBO>();
        if(response.length()>0){

            try{
                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("info");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);
                            if (jsonObject != null){

                                CustomerBO customerBO = new CustomerBO();
                                if(!jsonObject.isNull("first_name")){
                                    customerBO.setCusfname(jsonObject.getString("first_name"));
                                }else{
                                    customerBO.setCusfname("");
                                }
                                if(!jsonObject.isNull("last_name")){
                                    customerBO.setCuslname(jsonObject.getString("last_name"));
                                }else{
                                    customerBO.setCuslname("");
                                }
                                if(!jsonObject.isNull("email")){
                                    customerBO.setCusemail(jsonObject.getString("email"));
                                }else{
                                    customerBO.setCusemail("");
                                }
                                if(!jsonObject.isNull("mobile")){
                                    customerBO.setCusmobile(jsonObject.getString("mobile"));
                                }else{
                                    customerBO.setCusmobile("");
                                }
                                if(!jsonObject.isNull("username")){
                                    customerBO.setCususer(jsonObject.getString("username"));
                                }else{
                                    customerBO.setCususer("");
                                }
                                if(!jsonObject.isNull("status")){
                                    customerBO.setCusstatus(jsonObject.getString("status"));
                                }else{
                                    customerBO.setCusstatus("");
                                }
                                if(!jsonObject.isNull("password")){
                                    customerBO.setPass(jsonObject.getString("password"));
                                }else{
                                    customerBO.setPass("");
                                }
                                if(!jsonObject.isNull("gender")){
                                    customerBO.setGender(jsonObject.getString("gender"));
                                }else{
                                    customerBO.setGender("");
                                }
                                if(!jsonObject.isNull("image")){
                                    customerBO.setImage(jsonObject.getString("image"));
                                }else{
                                    customerBO.setImage("");
                                }
                                if(!jsonObject.isNull("city")){
                                    customerBO.setCity(jsonObject.getString("city"));
                                }else{
                                    customerBO.setCity("");
                                }
                                if(!jsonObject.isNull("address")){
                                    customerBO.setAddress(jsonObject.getString("address"));
                                }else{
                                    customerBO.setAddress("");
                                }
                                if(!jsonObject.isNull("pincode")){
                                    customerBO.setPin(jsonObject.getString("pincode"));
                                }else{
                                    customerBO.setPin("");
                                }
                                if(!jsonObject.isNull("country_id")){
                                    customerBO.setCountry(jsonObject.getString("country_id"));
                                }else{
                                    customerBO.setCountry("");
                                }
                                if(!jsonObject.isNull("zone_id")){
                                    customerBO.setState(jsonObject.getString("zone_id"));
                                }else{
                                    customerBO.setState("");
                                }
                                if(!jsonObject.isNull("customer_id")){
                                    customerBO.setId(jsonObject.getString("customer_id"));
                                }else{
                                    customerBO.setId("");
                                }
                                if(!jsonObject.isNull("customer_group")){
                                    customerBO.setCusgroup(jsonObject.getString("customer_group"));
                                }else{
                                    customerBO.setCusgroup("");
                                }


                                customerBOList.add(customerBO);
                            }
                        }
                    }
                    customerMap.put("customer_list",customerBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return customerMap;
    }

}



