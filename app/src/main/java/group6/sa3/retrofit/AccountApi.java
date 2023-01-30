package group6.sa3.retrofit;


import java.util.List;

import group6.sa3.model.Account;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountApi {
    @GET ("/account/{username}")
    Call <Account> getAccount(String username);

    @POST("/add-account")
    Call <Account> addAccount(@Body Account account);
}
