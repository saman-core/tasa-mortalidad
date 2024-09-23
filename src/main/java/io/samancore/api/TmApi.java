package io.samancore.api;

import io.samancore.repository.TmRepository;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.logging.Logger;

import java.util.Map;

@Path("")
public class TmApi {

    @Inject
    Logger log;

    @Inject
    TmRepository repository;

    @POST
    @Path("/single/{property}")
    @RolesAllowed({"admin"})
    public Uni<Object> single(@PathParam("property") String property,
                              Map<String, Object> filters
    ) {
        log.debug("TmApi.single");
        return repository.single(property, filters);
    }
}
