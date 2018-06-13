package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.OrdersBO;
import com.fingertip.catalog.model.UsersBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UsersParsar {
    public static Map parseUsers(String response){
        Map userMap = new HashMap();
        List<UsersBO> usersBOList = new ArrayList<UsersBO>();
        if(response.length()>0){

            try{


                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("user_info");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                            UsersBO usersBO = new UsersBO();
                            if (jsonObject != null){
//


                                if (!jsonObject.isNull("id")) {
                                    usersBO.setId(jsonObject.getString("id"));
                                } else {
                                    usersBO.setId("");
                                }
                                if (!jsonObject.isNull("first_name")) {
                                    usersBO.setFname(jsonObject.getString("first_name"));
                                } else {
                                    usersBO.setFname("");
                                }
                                if (!jsonObject.isNull("last_name")) {
                                    usersBO.setLname(jsonObject.getString("last_name"));
                                } else {
                                    usersBO.setLname("");
                                }
                                if (!jsonObject.isNull("gender")) {
                                    usersBO.setGender(jsonObject.getString("gender"));
                                } else {
                                    usersBO.setGender("");
                                }
                                if (!jsonObject.isNull("username")) {
                                  usersBO.setUsername(jsonObject.getString("username"));
                                } else {
                                    usersBO.setUsername("");
                                }
                                if(!jsonObject.isNull("password")){
                                    usersBO.setPass(jsonObject.getString("password"));
                                }else{
                                    usersBO.setPass("");
                                }
                                if(!jsonObject.isNull("gstin")){
                                    usersBO.setGstin(jsonObject.getString("gstin"));
                                }else{
                                    usersBO.setGstin("");
                                }
                                if(!jsonObject.isNull("usergroup")){
                                   usersBO.setUsegr(jsonObject.getString("usergroup"));
                                }else{
                                    usersBO.setUsegr("");
                                }
                                if(!jsonObject.isNull("email")){
                                   usersBO.setEmail(jsonObject.getString("email"));
                                }else {
                                    usersBO.setEmail("");
                                }

                                    if(!jsonObject.isNull("mobile")){
                                        usersBO.setMob(jsonObject.getString("mobile"));
                                    }else{
                                        usersBO.setMob("");
                                    }

                                    if(!jsonObject.isNull("status")){
                                        usersBO.setStatus(jsonObject.getString("status"));
                                    }else{
                                        usersBO.setStatus("");
                                    }




                            }



                            usersBOList.add(usersBO);
                        }

                    }
                    userMap.put("user_list",usersBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return userMap;
    }

}





