package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Member;
import org.acme.repository.MemberRepository;

import java.net.URI;
import java.util.List;

@Path("/members")
@Produces("application/json")
@Consumes("application/json")
public class MemberResource {

    @Inject
    MemberRepository memberRepository;

    @GET
    public List<Member> listAllMembers() {
        return memberRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Member getMemberById(@PathParam("id") Long id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new WebApplicationException("Member with ID " + id + " not found", Response.Status.NOT_FOUND);
        }
        return member;
    }

    @POST
    @Transactional
    public Response createMember(Member member) {
        memberRepository.persist(member);
        return Response.created(URI.create("/members/" + member.getId())).build();
    }
}
