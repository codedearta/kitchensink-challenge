package org.acme;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class StartupBean {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        List<Object[]> tables = entityManager.createNativeQuery("SELECT table_name \n" +
                "FROM information_schema.tables \n" +
                "WHERE table_schema = 'public' \n" +
                "ORDER BY table_name").getResultList();
        System.out.println("Tables: " + tables.size());
        System.out.println("Tables: " + tables);
    }
}