package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entity.TestEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class TestEntityPersistenceTest {

    @Inject
    EntityManager entityManager;

    @Test
    @Transactional
    public void testPersistAndRetrieveEntity() {
        // Create and persist a new TestEntity
        TestEntity entity = new TestEntity();
        entity.setName("Test Name");
        entityManager.persist(entity);

        // Retrieve the entity by ID
        TestEntity retrievedEntity = entityManager.find(TestEntity.class, entity.getId());

        // Verify the entity was persisted and retrieved correctly
        assertNotNull(retrievedEntity);
        assertEquals("Test Name", retrievedEntity.getName());
    }
}
