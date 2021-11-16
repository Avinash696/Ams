package com.ams.amsvistara.ws.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.BuildConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ams.amsvistara.constants.AppConstant.BASE_URL;


public class RestService {
    private static APIService apiService;


    public static APIService getApiService(@NonNull Map<String, String> req) {
        if (apiService == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            String valueUser = "";
            String valuePassword = "";
            for (Map.Entry<String, String> entry : req.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("username")) {
                    valueUser = entry.getValue();
                }
                if (entry.getKey().equalsIgnoreCase("password")) {
                    valuePassword = entry.getValue();
                }
            }
            Log.e("####","####User : "+valueUser);
            Log.e("####","####Password : "+valuePassword);
            //timeout start
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);//default 30
            builder.readTimeout(60, TimeUnit.SECONDS);//default 30
            builder.writeTimeout(60, TimeUnit.SECONDS);//default 30
            builder.addInterceptor(new BasicAuthInterceptor(valueUser ,valuePassword));

            //timeout end
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(httpLoggingInterceptor);
            }
//            builder.addInterceptor(new RequestTokenInterceptor());
            OkHttpClient client = builder.build();
            apiService = getApiServiceSimple(client);
        }
        return apiService;
    }

    public static APIService getApiServiceOther() {
        if (apiService == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //timeout start
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);//default 30
            builder.readTimeout(60, TimeUnit.SECONDS);//default 30
            builder.writeTimeout(60, TimeUnit.SECONDS);//default 30

            //timeout end
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(httpLoggingInterceptor);
            }
//            builder.addInterceptor(new RequestTokenInterceptor());
            OkHttpClient client = builder.build();
            apiService = getApiServiceSimple(client);
        }
        return apiService;
    }

    private static APIService getApiServiceSimple(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return apiService = retrofit.create(APIService.class);

    }

   /* public static APIService getApiServiceWithAuth() {
        if (apiService == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);//default 30
            builder.readTimeout(60, TimeUnit.SECONDS);//default 30
            builder.writeTimeout(60, TimeUnit.SECONDS);//default 30
            OkHttpClient client = builder.addInterceptor(httpLoggingInterceptor).addInterceptor(new RequestTokenInterceptorFake(false)).build();

            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("")
                    // .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();
            apiService = retrofit.create(APIService.class);
        }

        return apiService;
    }

*/

}

