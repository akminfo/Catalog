package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CouponBO;
import com.fingertip.catalog.model.CustomerGroupBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 21-05-2018.
 */

public class CustomerGroupListParsar {
    public static Map parseGroups(String response){
        Map cusGroup = new HashMap();
        List<CustomerGroupBO> customerGroupBOList = new ArrayList<CustomerGroupBO>();
        if(response.length()>0){

            try{


                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("customergroup_info");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                            CustomerGroupBO customerGroupBO = new CustomerGroupBO();
                            if (jsonObject != null){

                                if (!jsonObject.isNull("id")) {
                                    if(!(jsonObject.getString("id").equals("5"))) {
                                        customerGroupBO.setId(jsonObject.getString("id"));
                                    }
                                    else {
                                        customerGroupBO.setId("");
                                    }
                                } else {
                                    customerGroupBO.setId("");
                                }
                                if (!jsonObject.isNull("name")) {
                                    if(!(jsonObject.getString("name").equals("none")))
                                    {
                                        customerGroupBO.setName(jsonObject.getString("name"));
                                    }
                                    else {
                                        customerGroupBO.setName("");
                                    }
                                } else {
                                    customerGroupBO.setName("");
                                }

                                if (!jsonObject.isNull("status")) {
                                    if(!(jsonObject.getString("name").equals("none")))
                                    {
                                        customerGroupBO.setStatus(jsonObject.getString("status"));
                                    }
                                    else {
                                        customerGroupBO.setStatus("");
                                    }
                                } else {
                                    customerGroupBO.setStatus("");
                                }


                            }



                            customerGroupBOList.add(customerGroupBO);
                        }

                    }
                    cusGroup.put("cusgroup_list",customerGroupBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return cusGroup;
    }

}



