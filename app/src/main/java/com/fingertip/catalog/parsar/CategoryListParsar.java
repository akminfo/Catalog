package com.fingertip.catalog.parsar;

import com.fingertip.catalog.model.CategoryBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 08-05-2018.
 */

public class CategoryListParsar {
    public static Map parseCategoryProducts(String response){

        Map categoryMap = new HashMap();
        List<CategoryBO> categoryBOList = new ArrayList<CategoryBO>();
        if(response.length()>0){

            try{
                JSONObject mainJsonObject = new JSONObject(response);
                if (mainJsonObject != null){
                    JSONArray mainJsonArray = mainJsonObject.getJSONArray("categoryInfo");
                    if (mainJsonArray != null){
                        for (int i=0; i<mainJsonArray.length(); i++){
                            JSONObject jsonObject = mainJsonArray.getJSONObject(i);
                            if (jsonObject != null){

                                CategoryBO categoryBO = new CategoryBO();
                                if(!jsonObject.isNull("name")){
                                    categoryBO.setCatname(jsonObject.getString("name"));
                                }else{
                                    categoryBO.setCatname("");
                                }
                                if(!jsonObject.isNull("status")){
                                    categoryBO.setStatus(jsonObject.getString("status"));
                                }else{
                                    categoryBO.setStatus("");
                                }
                                if(!jsonObject.isNull("id")){
                                    categoryBO.setId(jsonObject.getString("id"));
                                }else{
                                    categoryBO.setId("");
                                }
                                if(!jsonObject.isNull("sort_order")){
                                    categoryBO.setSort(jsonObject.getString("sort_order"));
                                }else{
                                    categoryBO.setSort("");
                                }
                                if(!jsonObject.isNull("top")){
                                    categoryBO.setTop(jsonObject.getString("top"));
                                }else{
                                    categoryBO.setTop("");
                                }
                                if(!jsonObject.isNull("description")){
                                    categoryBO.setDesc(jsonObject.getString("description"));
                                }else{
                                    categoryBO.setDesc("");
                                }
                                if(!jsonObject.isNull("meta_title")){
                                    categoryBO.setMeta_title(jsonObject.getString("meta_title"));
                                }else{
                                    categoryBO.setMeta_title("");
                                }
                                if(!jsonObject.isNull("meta_description")){
                                    categoryBO.setMeta_decs(jsonObject.getString("meta_description"));
                                }else{
                                    categoryBO.setMeta_decs("");
                                }
                                if(!jsonObject.isNull("meta_keyword")){
                                    categoryBO.setMeta_key(jsonObject.getString("meta_keyword"));
                                }else{
                                    categoryBO.setMeta_key("");
                                }


                                categoryBOList.add(categoryBO);
                            }
                        }
                    }
                    categoryMap.put("category_list",categoryBOList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return categoryMap;
    }

}


