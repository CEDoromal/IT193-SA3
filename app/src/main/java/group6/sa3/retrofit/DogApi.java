package group6.sa3.retrofit;

import java.util.List;

import group6.sa3.model.Dog;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DogApi {
    @GET ("/dogs")
    Call <List<Dog>> getAllDog();

    @GET ("/dogs/{id}")
    Call <Dog> getDog(@Path("id") int id);

    @POST ("/add-dog")
    Call <Dog> addDog(@Body Dog dog);

    @PUT ("/update-dog")
    Call <Dog> updateDog(@Body Dog dog);

    @DELETE ("/remove-dog/{id}")
    Call <Void> deleteDog(@Path("id") int id);
}