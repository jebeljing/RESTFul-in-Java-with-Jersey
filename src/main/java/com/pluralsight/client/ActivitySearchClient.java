package com.pluralsight.client;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by Jingshan on 9/23/2017.
 */
public class ActivitySearchClient {

    private Client client;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

//    public List<Activity> search (String param, List<String> searchValues) {
    public List<Activity> search(String param, List<String> searchValues, String secondParam, int durationFrom, String thirdParam, int durationTo) {

        // http://localhost:8080/webapi/search/activities?description=swimming&sedcription=running
        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/activities")
                .queryParam(param, searchValues)
                .queryParam(secondParam, durationFrom)
                .queryParam(thirdParam, durationTo)
                .build();
        WebTarget target = client.target(uri);

        List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(List.class);

        System.out.println(response);
        return response;
    }

    public List<Activity> search(ActivitySearch search) {
        // http://localhost:8080/webapi/search/activities?description=swimming&sedcription=running
        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/activities")
                .build();
        WebTarget target = client.target(uri);

        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(search, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }
        return response.readEntity(List.class);
    }
}
