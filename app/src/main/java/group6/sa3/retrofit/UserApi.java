package group6.sa3.retrofit;


import java.util.List;

import group6.sa3.model.Account;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @GET ("/")
    Call <List<Account>> getAllUsers();

    @POST("/add-account")
    Call <Account> save(@Body Account account);
}
