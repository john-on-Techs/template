package com.creativityskills.john.on.techs.fees.beans;

import com.google.gson.Gson;
import okhttp3.*;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Util {
    public String getStudentById(long studentId) {
        RetrofitServiceInterface service = RetrofitServiceInstance
                .getRetrofitInstance("http://192.168.10.60:8080/SchoolSystemApplication/")
                .create(RetrofitServiceInterface.class);
        Call<ResponseBody>  call = service.findStudentById(studentId);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    private static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    public String getStudentByRegistrationNo(String registrationNumber,String resourceUrl){
        OkHttpClient client=new OkHttpClient();
        Map<String, String> jsonParams = new HashMap<>();
        //put something inside the map, could be null
        jsonParams.put("registrationNumber", registrationNumber);
        RequestBody requestBody=RequestBody.create(JSON,new Gson().toJson(jsonParams));
        Request request=new Request.Builder()
                .url("http://192.168.10.60:8080/SchoolSystemApplication/"+resourceUrl)
                .post(requestBody)
                .build();
        try {
            Response response=client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return null;
        }
    }

    public String getStudentByRegistrationNumber(String registrationNumber) {
        RetrofitServiceInterface service = RetrofitServiceInstance.
                getRetrofitInstance("http://192.168.10.60:8080/SchoolSystemApplication/")
                .create(RetrofitServiceInterface.class);

        //exam-api/students/findByRegistrationNumber
        HashMap<String,String> body = new HashMap<>();
        body.put("registrationNumber",registrationNumber);
        Call<ResponseBody>  call = service.findStudentByRegistrationNumber(body);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            return null;
        }


    }
}
