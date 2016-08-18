package co.mantara.demoretrofit;

import co.mantara.demoretrofit.model.GitHub;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{username}")
    Call<GitHub> getGitHubUsers(@Path("username")String username);
}