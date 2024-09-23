package io.samancore.repository;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.Logger;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class TmRepository {

    @Inject
    Logger log;

    @Inject
    Mutiny.SessionFactory sessionFactory;

    public Uni<Object> single(String property, Map<String, Object> filters) {
        log.debug("TmRepository.single");
        return sessionFactory.withSession(s -> {
                    AtomicReference<String> sql = new AtomicReference<>("Select ".concat(property).concat(" From TmEntity e"));
                    if (!filters.isEmpty()) {
                        sql.set(sql.get().concat(" Where 1=1"));
                        filters.keySet().forEach(k -> sql.set(sql.get().concat(" And e.").concat(k).concat("=:").concat(k)));
                    }
                    var q = s.createQuery(sql.get());
                    filters.forEach(q::setParameter);
                    return q.getSingleResultOrNull();
                })
                .onItem().ifNull().failWith(NotFoundException::new);
    }
}
