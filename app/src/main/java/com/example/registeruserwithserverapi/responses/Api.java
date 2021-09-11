package com.example.registeruserwithserverapi.responses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("fetchUsers.php")
    Call<FetchUserResponse> fetchAllUsers();

    @FormUrlEncoded
    @POST("updateUser.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id") int userid,
            @Field("username") String username,
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("updatePass.php")
    Call<UpdatePassResponse> updateUserPass(


            @Field("current") String currentPassword,
            @Field("email") String email,
            @Field("new") String newPassword

    );


    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<DeleteResponse> deleteUserAccount(


            @Field("id") int userId

    );

}
