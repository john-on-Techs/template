package com.creativityskills.john.on.techs.fees.beans;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface RetrofitServiceInterface {
    @GET("api/students/{studentId}")
    Call<ResponseBody>  findStudentById(@Path("studentId") long studentId);

    @POST("api/students/findStudentByRegistrationNumber")
    Call<ResponseBody>   findStudentByRegistrationNumber(@Body HashMap<String, String>  postData);
    @POST("api/students/findStudentByRegistrationNumber")
    Call<ResponseBody>  findStudentByRegistrationNo(@Body HashMap<String, String>  postData);


}
