package self.sontq.taostudy.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

public class ServiceBuilder {
    private static final long TIME_OUT_CONNECT = 30;
    private static final String BASE_URL = "http://aigle.blife.ai/tao/Main/login/";
    private static Retrofit retrofit;
    private static APIService sApiService;

    public static synchronized APIService getApiServiceNormal() {
        sApiService = getRetrofitNomal().create(APIService.class);
        return sApiService;
    }

    public static synchronized APIService getApiServiceAuthen() {
        sApiService = getRetrofitAuthen().create(APIService.class);

        return sApiService;
    }

    public static Retrofit getRetrofitNomal() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getUnSafeHttpClient(false))
                .build();
    }

    public static Retrofit getRetrofitAuthen() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getUnSafeHttpClient(true))
                .build();
    }

    public static OkHttpClient getUnSafeHttpClient(final boolean isLogin) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                for (Map.Entry<String, String> header : getHeader(isLogin).entrySet()) {
                    builder.header(header.getKey(), header.getValue() == null ? "" : header.getValue());
                }

                Request request = builder
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    public static HashMap<String, String> getHeader(boolean isLogin) {
        HashMap<String, String> headers = new HashMap<>();

//        String token = SharedPreferenceUtils.getInstance(DtdtApplication.getInstance()).getStringValue(Const.KEY_TOKEN, null);
//        Log.d("TOKEN ", "getHeader: " + token);
        headers.put("Content-Type", "application/json");
        headers.put("Device", "ANDROID");

        return headers;
    }
}
