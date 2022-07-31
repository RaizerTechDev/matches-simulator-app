package me.dio.simulator.data;

import java.util.List;

import me.dio.simulator.domain.Match;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {

    @GET("matches.json")
    static
        //para acessar o json do pages github
    Call<List<Match>> getMatches() {
        return null;
    }
}







