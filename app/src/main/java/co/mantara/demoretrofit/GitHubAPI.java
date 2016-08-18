package co.mantara.demoretrofit;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubAPI {
    private final static String url = "https://api.github.com/";
    private GitHubService gitHubService;

    public GitHubAPI() {
        // Custom HTTP Client
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);

        // Add LoggingInterceptor to Http Client
        // (use for Logging Retrofit Request/Response)
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }
}
