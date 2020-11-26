package self.sontq.taostudy.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import self.sontq.taostudy.model.Question;

public interface APIService {
    @FormUrlEncoded
    @POST("Main/login")
    Call<String> login(@Field("login") String login, @Field("password") String password, @Field("connect") String connect, @Field("loginForm_sent") int loginForm_sent);

    @GET("changes/")
    Call<List<Question>> getQuestion(@Field("extension") String extension, @Field("module") String module, @Field("action") String action);
}
//extension=taoDelivery&module=DeliveryServer&action=index