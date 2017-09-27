package com.pluralsight;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jingshan on 9/23/2017.
 */
@Path("search/activities")
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions) {
    public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions,
                                        @QueryParam("durationFrom") int durationFrom,
                                        @QueryParam("durationTo") int durationTo) {
//        System.out.println(descriptions);
        System.out.println(descriptions + ", " + durationFrom + ", " + durationTo);

//        List<Activity> activities = activityRepository.findByDescription(descriptions);
        List<Activity> activities = activityRepository.findByDescription(descriptions, durationFrom, durationTo);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(activities).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForActivities(ActivitySearch search) {
        System.out.println(search.getDescriptions() + ", " + search.getDurationFrom() + ", " + search.getDurationTo());

        List<Activity> activities = activityRepository.findByConstraints(search);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(activities).build();
    }

}
