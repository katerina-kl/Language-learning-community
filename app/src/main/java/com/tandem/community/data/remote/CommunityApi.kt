package com.tandem.community.data.remote

import com.tandem.community.data.remote.dto.CommunityResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CommunityApi {

    /**
     * Fetches one page of the community feed.
     *
     * The endpoint exposes one static JSON file per page, e.g.
     * `community_1.json`, `community_2.json`, ...
     */
    @GET("api/community_{page}.json")
    suspend fun getCommunity(@Path("page") page: Int): CommunityResponseDto

    companion object {
        const val BASE_URL = "https://tandem2019.web.app/"
    }
}
