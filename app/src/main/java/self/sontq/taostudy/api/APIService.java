package self.sontq.taostudy.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @FormUrlEncoded
    @POST("Main/login")
    Call<String> login(@Field("login") String login, @Field("password") String password, @Field("connect") String connect, @Field("loginForm_sent") int loginForm_sent);

    @GET("http://aigle.blife.ai/tao/Main/index")
    Call<String> getTest(@Query("results") String results, @Query("ext") String ext);
}