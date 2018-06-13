package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CustomerGroupBO;
import com.fingertip.catalog.model.UserGroupBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UserGroupParsar {
    public static Map parseGroups(String response){
        Map useGroup = new HashMap();
        List<UserGroupBO> userGroupBOList = new ArrayList<UserGroupBO>();
        if(response.length()>0){

            try{


                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("usergroup_info");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                            UserGroupBO userGroupBO = new UserGroupBO();
                            if (jsonObject != null){

                                if (!jsonObject.isNull("id")) {

                                    userGroupBO.setId(jsonObject.getString("id"));

                                } else {
                                    userGroupBO.setId("");
                                }
                                if (!jsonObject.isNull("name")) {

                                        userGroupBO.setUsername(jsonObject.getString("name"));

                                } else {
                                    userGroupBO.setUsername("");
                                }
                                if (!jsonObject.isNull("access_permission")) {

                                    userGroupBO.setAccess(jsonObject.getString("access_permission").replace("_"," "));

                                } else {
                                    userGroupBO.setAccess("");
                                }
                                if (!jsonObject.isNull("modify_permission")) {

                                    userGroupBO.setModify(jsonObject.getString("modify_permission").replace("_"," "));

                                } else {
                                    userGroupBO.setModify("");
                                }




                            }



                            userGroupBOList.add(userGroupBO);
                        }

                    }
                    useGroup.put("usergroup_list",userGroupBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return useGroup;
    }

}





