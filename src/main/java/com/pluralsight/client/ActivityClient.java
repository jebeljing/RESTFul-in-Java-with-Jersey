package com.pluralsight.client;

import com.pluralsight.model.Activity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jingshan on 9/22/2017.
 */
public class ActivityClient {

    private Client client;

    public ActivityClient() {
        client = ClientBuilder.newClient();
    }

    public Activity get(String id) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

//        Activity response = target.path("activities/" + id).request().get(Activity.class);
//        String response = target.path("activities/" + id).request(MediaType.APPLICATION_XML).get(String.class);
//        String response = target.path("activities/" + id).request().get(String.class);

//        System.out.println(response);
//        return null;


        Response response = target.path("activities/" + id).request().get(Response.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }
        return response.readEntity(Activity.class);
    }

    public List<Activity> get() {
        WebTarget target = client.target("http://localhost:8080/webapi/");
//        List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(List.class);
        List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>(){});

        return  response;
    }

    public Activity create(Activity activity) {
        WebTarget target = client.target("http://localhost:8080/webapi/");
        Response response = target.path("activities/activity").request().post(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }
        return response.readEntity(Activity.class);
    }

    public Activity update(Activity activity) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("activities/" + activity.getId()).request().put(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }
        return response.readEntity(Activity.class);
    }

    public void delete(String activityId) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("activities/" + activityId).request(MediaType.APPLICATION_JSON).delete();

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

    }
}
