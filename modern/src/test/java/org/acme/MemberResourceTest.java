package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.Member;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class MemberResourceTest {

    @Inject
    EntityManager entityManager;

    @Test
    public void testListAllMembers() {
        // Add test users using REST endpoints
        Member member1 = new Member();
        member1.setName("Alice");
        member1.setEmail("alice@example.com");
        member1.setPhoneNumber("1111111111");

        given()
            .contentType("application/json")
            .body(member1)
            .when().post("/members")
            .then()
            .statusCode(201);

        Member member2 = new Member();
        member2.setName("Bob");
        member2.setEmail("bob@example.com");
        member2.setPhoneNumber("2222222222");

        given()
            .contentType("application/json")
            .body(member2)
            .when().post("/members")
            .then()
            .statusCode(201);

        // Verify the list of members
        given()
            .when().get("/members")
            .then()
            .statusCode(200)
            .body(containsString("Alice"))
            .body(containsString("Bob"));
    }

    @Test
    public void testGetMemberByIdNotFound() {
        given()
            .when().get("/members/999")
            .then()
            .statusCode(404)
            .body(is(""));
    }

    @Test
    public void testGetMemberById() {
        // Create a new member using REST endpoint
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("1234567890");

        String location = given()
            .contentType("application/json")
            .body(member)
            .when().post("/members")
            .then()
            .statusCode(201)
            .extract().header("Location");

        // Extract the ID from the location header
        String[] locationParts = location.split("/");
        String memberId = locationParts[locationParts.length - 1];

        // Retrieve the member by ID and verify the response
        given()
            .when().get("/members/" + memberId)
            .then()
            .statusCode(200)
            .body("name", is("John Doe"))
            .body("email", is("john.doe@example.com"))
            .body("phoneNumber", is("1234567890"));
    }

    public void testCreateMember() {
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("1234567890");

        given()
            .contentType("application/json")
            .body(member)
            .when().post("/members")
            .then()
            .statusCode(201)
            .header("Location", containsString("/members/"));
    }
}
