package com.fingertip.catalog.parsar;

import android.text.Html;

import com.fingertip.catalog.model.ProductBO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 10-05-2018.
 */

public class SearchParsar
{
    public static Map parseProducts(String response){

    Map searchMap = new HashMap();
    List<ProductBO> productBOList = new ArrayList<ProductBO>();
    if(response.length()>0){

        try{

            JSONObject mainJsonObject = new JSONObject(response);
            if (mainJsonObject != null){
                JSONArray mainJsonArray = mainJsonObject.getJSONArray("info");
                if (mainJsonArray != null){
                    for (int i=0; i<mainJsonArray.length(); i++){
                        JSONObject jsonObject = mainJsonArray.getJSONObject(i);


                        ProductBO productBO = new ProductBO();
                        if (jsonObject != null){
//


                            if (!jsonObject.isNull("id")) {
                                productBO.setId(jsonObject.getString("id"));
                            } else {
                                productBO.setId("");
                            }
                            if (!jsonObject.isNull("sku")) {
                                productBO.setSku(jsonObject.getString("sku"));
                            } else {
                                productBO.setSku("");
                            }
                            if (!jsonObject.isNull("image")) {
                                productBO.setImage(jsonObject.getString("image"));
                            } else {
                                productBO.setImage("");
                            }
                            if (!jsonObject.isNull("price")) {
                                productBO.setPrice(jsonObject.getString("price"));
                            } else {
                                productBO.setPrice("");
                            }
                            if (!jsonObject.isNull("quantity")) {
                                productBO.setQuantity(jsonObject.getString("quantity"));
                            } else {
                                productBO.setQuantity("");
                            }
                            if(!jsonObject.isNull("category_name")){
                                productBO.setCat(jsonObject.getString("category_name"));
                            }else{
                                productBO.setCat("");
                            }

                            if (!jsonObject.isNull("product_name")) {
                                productBO.setName(jsonObject.getString("product_name"));
                            } else {
                                productBO.setName("");
                            }
                            if (!jsonObject.isNull("description")) {
                                productBO.setDesc(jsonObject.getString("description").replaceAll("(\\n)", " "));
                            } else {
                                productBO.setDesc("");
                            }
                            if (!jsonObject.isNull("meta_title")) {
                                productBO.setMeta_title(jsonObject.getString("meta_title"));
                            } else {
                                productBO.setMeta_title("");
                            }
                            if (!jsonObject.isNull("meta_description")) {
                                String s=jsonObject.getString("meta_description").replaceAll("(\\n)", " ");

                                s=s.replaceAll("(\\n)", " ");
                                System.out.println("spaa"+s);
                                productBO.setMeta_desc(s);
                            } else {
                                productBO.setMeta_desc("");
                            }
                            if (!jsonObject.isNull("meta_keyword")) {
                                productBO.setMeta_key(jsonObject.getString("meta_keyword"));
                            } else {
                                productBO.setMeta_key("");
                            }
                            if (!jsonObject.isNull("key_feature")) {
//                                String segments[] = jsonObject.getString("key_feature").split("\\n");
//

//                                String firstSubString = segments[0];
                                productBO.setKeyfeat(jsonObject.getString("key_feature").replaceAll("(\\n)", " "));
                            } else {
                                productBO.setKeyfeat("");
                            }

                            if (!jsonObject.isNull("status")) {
                                productBO.setStatus(jsonObject.getString("status"));
                            } else {
                                productBO.setStatus("");
                            }

                            if (!jsonObject.isNull("sort_order")) {
                                productBO.setSort(jsonObject.getString("sort_order"));
                            } else {
                                productBO.setSort("");
                            }
                            if (!jsonObject.isNull("category_id")) {
                                productBO.setCat_id(jsonObject.getString("category_id"));
                            } else {
                                productBO.setCat_id("");
                            }
                            if (!jsonObject.isNull("product_image_1")) {
                                productBO.setImage1(jsonObject.getString("product_image_1"));
                            } else {
                                productBO.setImage1("");
                            }
                            if (!jsonObject.isNull("product_image_2")) {
                                productBO.setImage2(jsonObject.getString("product_image_2"));
                            } else {
                                productBO.setImage2("");
                            }
                            if (!jsonObject.isNull("product_image_3")) {
                                productBO.setImage3(jsonObject.getString("product_image_3"));
                            } else {
                                productBO.setImage3("");
                            }
                            if (!jsonObject.isNull("product_image_4")) {
                                productBO.setImage4(jsonObject.getString("product_image_4"));
                            } else {
                                productBO.setImage4("");
                            }
                            if (!jsonObject.isNull("product_image_5")) {
                                productBO.setImage5(jsonObject.getString("product_image_5"));
                            } else {
                                productBO.setImage5("");
                            }
                            if (!jsonObject.isNull("product_image_6")) {
                                productBO.setImage6(jsonObject.getString("product_image_6"));
                            } else {
                                productBO.setImage6("");
                            }
                            if (!jsonObject.isNull("product_image_7")) {
                                productBO.setImage7(jsonObject.getString("product_image_7"));
                            } else {
                                productBO.setImage7("");
                            }
                            if (!jsonObject.isNull("product_image_8")) {
                                productBO.setImage8(jsonObject.getString("product_image_8"));
                            } else {
                                productBO.setImage8("");
                            }
                            if (!jsonObject.isNull("product_image_9")) {
                                productBO.setImage9(jsonObject.getString("product_image_9"));
                            } else {
                                productBO.setImage9("");
                            }
                            if (!jsonObject.isNull("product_image_10")) {
                                productBO.setImage10(jsonObject.getString("product_image_10"));
                            } else {
                                productBO.setImage10("");
                            }
                            if (!jsonObject.isNull("product_image_sort_1")) {
                                productBO.setImgsort1(jsonObject.getString("product_image_sort_1"));
                            } else {
                                productBO.setImgsort1("");
                            }
                            if (!jsonObject.isNull("product_image_sort_2")) {
                                productBO.setImgsort2(jsonObject.getString("product_image_sort_2"));
                            } else {
                                productBO.setImgsort2("");
                            }
                            if (!jsonObject.isNull("product_image_sort_3")) {
                                productBO.setImgsort3(jsonObject.getString("product_image_sort_3"));
                            } else {
                                productBO.setImgsort3("");
                            }
                            if (!jsonObject.isNull("product_image_sort_4")) {
                                productBO.setImgsort4(jsonObject.getString("product_image_sort_4"));
                            } else {
                                productBO.setImgsort4("");
                            }
                            if (!jsonObject.isNull("product_image_sort_5")) {
                                productBO.setImgsort5(jsonObject.getString("product_image_sort_5"));
                            } else {
                                productBO.setImgsort5("");
                            }
                            if (!jsonObject.isNull("product_image_sort_6")) {
                                productBO.setImgsort6(jsonObject.getString("product_image_sort_6"));
                            } else {
                                productBO.setImgsort6("");
                            }
                            if (!jsonObject.isNull("product_image_sort_7")) {
                                productBO.setImgsort7(jsonObject.getString("product_image_sort_7"));
                            } else {
                                productBO.setImgsort7("");
                            }
                            if (!jsonObject.isNull("product_image_sort_8")) {
                                productBO.setImgsort8(jsonObject.getString("product_image_sort_8"));
                            } else {
                                productBO.setImgsort8("");
                            }
                            if (!jsonObject.isNull("product_image_sort_9")) {
                                productBO.setImgsort9(jsonObject.getString("product_image_sort_9"));
                            } else {
                                productBO.setImgsort9("");
                            }
                            if (!jsonObject.isNull("product_image_sort_10")) {
                                productBO.setImgsort10(jsonObject.getString("product_image_sort_10"));
                            } else {
                                productBO.setImgsort10("");
                            }
                            if (!jsonObject.isNull("img_count")) {
                                productBO.setImg_count(String.valueOf(jsonObject.getString("img_count")));
                            } else {
                                productBO.setImg_count("");
                            }
                            if (!jsonObject.isNull("product_image_id_1")) {
                                productBO.setImg1_id1(jsonObject.getString("product_image_id_1"));
                            } else {
                                productBO.setImg1_id1("");
                            }
                            if (!jsonObject.isNull("product_image_id_2")) {
                                productBO.setImg1_id2(jsonObject.getString("product_image_id_2"));
                            } else {
                                productBO.setImg1_id2("");
                            }
                            if (!jsonObject.isNull("product_image_id_3")) {
                                productBO.setImg1_id3(jsonObject.getString("product_image_id_3"));
                            } else {
                                productBO.setImg1_id3("");
                            }
                            if (!jsonObject.isNull("product_image_id_4")) {
                                productBO.setImg1_id4(jsonObject.getString("product_image_id_4"));
                            } else {
                                productBO.setImg1_id4("");
                            }
                            if (!jsonObject.isNull("product_image_id_5")) {
                                productBO.setImg1_id5(jsonObject.getString("product_image_id_5"));
                            } else {
                                productBO.setImg1_id5("");
                            }
                            if (!jsonObject.isNull("product_image_id_6")) {
                                productBO.setImg1_id6(jsonObject.getString("product_image_id_6"));
                            } else {
                                productBO.setImg1_id6("");
                            }
                            if (!jsonObject.isNull("product_image_id_7")) {
                                productBO.setImg1_id7(jsonObject.getString("product_image_id_7"));
                            } else {
                                productBO.setImg1_id7("");
                            }
                            if (!jsonObject.isNull("product_image_id_8")) {
                                productBO.setImg1_id8(jsonObject.getString("product_image_id_8"));
                            } else {
                                productBO.setImg1_id8("");
                            }
                            if (!jsonObject.isNull("product_image_id_9")) {
                                productBO.setImg1_id9(jsonObject.getString("product_image_id_9"));
                            } else {
                                productBO.setImg1_id9("");
                            }
                            if (!jsonObject.isNull("product_image_id_10")) {
                                productBO.setImg1_id10(jsonObject.getString("product_image_id_10"));
                            } else {
                                productBO.setImg1_id10("");
                            }

                        }



                        productBOList.add(productBO);
                    }

                }
                searchMap.put("search_list",productBOList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    return searchMap;
}

}


