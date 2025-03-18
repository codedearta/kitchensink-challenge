This file is a merged representation of a subset of the codebase, containing files not matching ignore patterns, combined into a single document by Repomix. The content has been processed where empty lines have been removed, line numbers have been added, security check has been disabled.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Multiple file entries, each consisting of:
   a. A header with the file path (## File: path/to/file)
   b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching these patterns are excluded: workflow/*
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Empty lines have been removed from all files
- Line numbers have been added to the beginning of each line
- Security check has been disabled - content may contain sensitive information

## Additional Info

# Directory Structure
```
legacy/
  charts/
    helm.yaml
  src/
    main/
      java/
        org/
          jboss/
            as/
              quickstarts/
                kitchensink/
                  controller/
                    MemberController.java
                  data/
                    MemberListProducer.java
                    MemberRepository.java
                  model/
                    Member.java
                  rest/
                    JaxRsActivator.java
                    MemberResourceRESTService.java
                  service/
                    MemberRegistration.java
                  util/
                    Resources.java
      resources/
        META-INF/
          persistence.xml
        import.sql
      webapp/
        resources/
          css/
            screen.css
        WEB-INF/
          templates/
            default.xhtml
          beans.xml
          faces-config.xml
          kitchensink-quickstart-ds.xml
        index.html
        index.xhtml
    test/
      java/
        org/
          jboss/
            as/
              quickstarts/
                kitchensink/
                  test/
                    MemberRegistrationIT.java
                    RemoteMemberRegistrationIT.java
      resources/
        META-INF/
          test-persistence.xml
        arquillian.xml
        test-ds.xml
  .cheatsheet.xml
  pom.xml
  README-source.adoc
  README.adoc
  README.html
modern/
  placeholder.txt
readme.md
```

# Files

## File: legacy/charts/helm.yaml
```yaml
1: build:
2:   uri: https://github.com/jboss-developer/jboss-eap-quickstarts.git
3:   ref: 8.0.x
4:   contextDir: kitchensink
5: deploy:
6:   replicas: 1
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.controller;
18: import jakarta.annotation.PostConstruct;
19: import jakarta.enterprise.inject.Model;
20: import jakarta.enterprise.inject.Produces;
21: import jakarta.faces.application.FacesMessage;
22: import jakarta.faces.context.FacesContext;
23: import jakarta.inject.Inject;
24: import jakarta.inject.Named;
25: import org.jboss.as.quickstarts.kitchensink.model.Member;
26: import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
27: // The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
28: // EL name
29: // Read more about the @Model stereotype in this FAQ:
30: // http://www.cdi-spec.org/faq/#accordion6
31: @Model
32: public class MemberController {
33:     @Inject
34:     private FacesContext facesContext;
35:     @Inject
36:     private MemberRegistration memberRegistration;
37:     @Produces
38:     @Named
39:     private Member newMember;
40:     @PostConstruct
41:     public void initNewMember() {
42:         newMember = new Member();
43:     }
44:     public void register() throws Exception {
45:         try {
46:             memberRegistration.register(newMember);
47:             FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
48:             facesContext.addMessage(null, m);
49:             initNewMember();
50:         } catch (Exception e) {
51:             String errorMessage = getRootErrorMessage(e);
52:             FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
53:             facesContext.addMessage(null, m);
54:         }
55:     }
56:     private String getRootErrorMessage(Exception e) {
57:         // Default to general error message that registration failed.
58:         String errorMessage = "Registration failed. See server log for more information";
59:         if (e == null) {
60:             // This shouldn't happen, but return the default messages
61:             return errorMessage;
62:         }
63:         // Start with the exception and recurse to find the root cause
64:         Throwable t = e;
65:         while (t != null) {
66:             // Get the message from the Throwable class instance
67:             errorMessage = t.getLocalizedMessage();
68:             t = t.getCause();
69:         }
70:         // This is the root cause message
71:         return errorMessage;
72:     }
73: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberListProducer.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.data;
18: import jakarta.annotation.PostConstruct;
19: import jakarta.enterprise.context.RequestScoped;
20: import jakarta.enterprise.event.Observes;
21: import jakarta.enterprise.event.Reception;
22: import jakarta.enterprise.inject.Produces;
23: import jakarta.inject.Inject;
24: import jakarta.inject.Named;
25: import java.util.List;
26: import org.jboss.as.quickstarts.kitchensink.model.Member;
27: @RequestScoped
28: public class MemberListProducer {
29:     @Inject
30:     private MemberRepository memberRepository;
31:     private List<Member> members;
32:     // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
33:     // Facelets or JSP view)
34:     @Produces
35:     @Named
36:     public List<Member> getMembers() {
37:         return members;
38:     }
39:     public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
40:         retrieveAllMembersOrderedByName();
41:     }
42:     @PostConstruct
43:     public void retrieveAllMembersOrderedByName() {
44:         members = memberRepository.findAllOrderedByName();
45:     }
46: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberRepository.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.data;
18: import jakarta.enterprise.context.ApplicationScoped;
19: import jakarta.inject.Inject;
20: import jakarta.persistence.EntityManager;
21: import jakarta.persistence.criteria.CriteriaBuilder;
22: import jakarta.persistence.criteria.CriteriaQuery;
23: import jakarta.persistence.criteria.Root;
24: import java.util.List;
25: import org.jboss.as.quickstarts.kitchensink.model.Member;
26: @ApplicationScoped
27: public class MemberRepository {
28:     @Inject
29:     private EntityManager em;
30:     public Member findById(Long id) {
31:         return em.find(Member.class, id);
32:     }
33:     public Member findByEmail(String email) {
34:         CriteriaBuilder cb = em.getCriteriaBuilder();
35:         CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
36:         Root<Member> member = criteria.from(Member.class);
37:         // Swap criteria statements if you would like to try out type-safe criteria queries, a new
38:         // feature in JPA 2.0
39:         // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
40:         criteria.select(member).where(cb.equal(member.get("email"), email));
41:         return em.createQuery(criteria).getSingleResult();
42:     }
43:     public List<Member> findAllOrderedByName() {
44:         CriteriaBuilder cb = em.getCriteriaBuilder();
45:         CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
46:         Root<Member> member = criteria.from(Member.class);
47:         // Swap criteria statements if you would like to try out type-safe criteria queries, a new
48:         // feature in JPA 2.0
49:         // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
50:         criteria.select(member).orderBy(cb.asc(member.get("name")));
51:         return em.createQuery(criteria).getResultList();
52:     }
53: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.model;
18: import java.io.Serializable;
19: import jakarta.persistence.Column;
20: import jakarta.persistence.Entity;
21: import jakarta.persistence.GeneratedValue;
22: import jakarta.persistence.Id;
23: import jakarta.persistence.Table;
24: import jakarta.persistence.UniqueConstraint;
25: import jakarta.validation.constraints.Digits;
26: import jakarta.validation.constraints.NotNull;
27: import jakarta.validation.constraints.Pattern;
28: import jakarta.validation.constraints.Size;
29: import jakarta.xml.bind.annotation.XmlRootElement;
30: import jakarta.validation.constraints.Email;
31: import jakarta.validation.constraints.NotEmpty;
32: @SuppressWarnings("serial")
33: @Entity
34: @XmlRootElement
35: @Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
36: public class Member implements Serializable {
37:     @Id
38:     @GeneratedValue
39:     private Long id;
40:     @NotNull
41:     @Size(min = 1, max = 25)
42:     @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
43:     private String name;
44:     @NotNull
45:     @NotEmpty
46:     @Email
47:     private String email;
48:     @NotNull
49:     @Size(min = 10, max = 12)
50:     @Digits(fraction = 0, integer = 12)
51:     @Column(name = "phone_number")
52:     private String phoneNumber;
53:     public Long getId() {
54:         return id;
55:     }
56:     public void setId(Long id) {
57:         this.id = id;
58:     }
59:     public String getName() {
60:         return name;
61:     }
62:     public void setName(String name) {
63:         this.name = name;
64:     }
65:     public String getEmail() {
66:         return email;
67:     }
68:     public void setEmail(String email) {
69:         this.email = email;
70:     }
71:     public String getPhoneNumber() {
72:         return phoneNumber;
73:     }
74:     public void setPhoneNumber(String phoneNumber) {
75:         this.phoneNumber = phoneNumber;
76:     }
77: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/JaxRsActivator.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.rest;
18: import jakarta.ws.rs.ApplicationPath;
19: import jakarta.ws.rs.core.Application;
20: /**
21:  * A class extending {@link Application} and annotated with @ApplicationPath is the Jakarta EE "no XML" approach to activating
22:  * JAX-RS.
23:  * <p>
24:  * <p>
25:  * Resources are served relative to the servlet path specified in the {@link ApplicationPath} annotation.
26:  * </p>
27:  */
28: @ApplicationPath("/rest")
29: public class JaxRsActivator extends Application {
30:     /* class body intentionally left blank */
31: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java
```java
  1: /*
  2:  * JBoss, Home of Professional Open Source
  3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
  4:  * contributors by the @authors tag. See the copyright.txt in the
  5:  * distribution for a full listing of individual contributors.
  6:  *
  7:  * Licensed under the Apache License, Version 2.0 (the "License");
  8:  * you may not use this file except in compliance with the License.
  9:  * You may obtain a copy of the License at
 10:  * http://www.apache.org/licenses/LICENSE-2.0
 11:  * Unless required by applicable law or agreed to in writing, software
 12:  * distributed under the License is distributed on an "AS IS" BASIS,
 13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 14:  * See the License for the specific language governing permissions and
 15:  * limitations under the License.
 16:  */
 17: package org.jboss.as.quickstarts.kitchensink.rest;
 18: import java.util.HashMap;
 19: import java.util.HashSet;
 20: import java.util.List;
 21: import java.util.Map;
 22: import java.util.Set;
 23: import java.util.logging.Logger;
 24: import jakarta.enterprise.context.RequestScoped;
 25: import jakarta.inject.Inject;
 26: import jakarta.persistence.NoResultException;
 27: import jakarta.validation.ConstraintViolation;
 28: import jakarta.validation.ConstraintViolationException;
 29: import jakarta.validation.ValidationException;
 30: import jakarta.validation.Validator;
 31: import jakarta.ws.rs.Consumes;
 32: import jakarta.ws.rs.GET;
 33: import jakarta.ws.rs.POST;
 34: import jakarta.ws.rs.Path;
 35: import jakarta.ws.rs.PathParam;
 36: import jakarta.ws.rs.Produces;
 37: import jakarta.ws.rs.WebApplicationException;
 38: import jakarta.ws.rs.core.MediaType;
 39: import jakarta.ws.rs.core.Response;
 40: import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
 41: import org.jboss.as.quickstarts.kitchensink.model.Member;
 42: import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
 43: /**
 44:  * JAX-RS Example
 45:  * <p/>
 46:  * This class produces a RESTful service to read/write the contents of the members table.
 47:  */
 48: @Path("/members")
 49: @RequestScoped
 50: public class MemberResourceRESTService {
 51:     @Inject
 52:     private Logger log;
 53:     @Inject
 54:     private Validator validator;
 55:     @Inject
 56:     private MemberRepository repository;
 57:     @Inject
 58:     MemberRegistration registration;
 59:     @GET
 60:     @Produces(MediaType.APPLICATION_JSON)
 61:     public List<Member> listAllMembers() {
 62:         return repository.findAllOrderedByName();
 63:     }
 64:     @GET
 65:     @Path("/{id:[0-9][0-9]*}")
 66:     @Produces(MediaType.APPLICATION_JSON)
 67:     public Member lookupMemberById(@PathParam("id") long id) {
 68:         Member member = repository.findById(id);
 69:         if (member == null) {
 70:             throw new WebApplicationException(Response.Status.NOT_FOUND);
 71:         }
 72:         return member;
 73:     }
 74:     /**
 75:      * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
 76:      * or with a map of fields, and related errors.
 77:      */
 78:     @POST
 79:     @Consumes(MediaType.APPLICATION_JSON)
 80:     @Produces(MediaType.APPLICATION_JSON)
 81:     public Response createMember(Member member) {
 82:         Response.ResponseBuilder builder = null;
 83:         try {
 84:             // Validates member using bean validation
 85:             validateMember(member);
 86:             registration.register(member);
 87:             // Create an "ok" response
 88:             builder = Response.ok();
 89:         } catch (ConstraintViolationException ce) {
 90:             // Handle bean validation issues
 91:             builder = createViolationResponse(ce.getConstraintViolations());
 92:         } catch (ValidationException e) {
 93:             // Handle the unique constrain violation
 94:             Map<String, String> responseObj = new HashMap<>();
 95:             responseObj.put("email", "Email taken");
 96:             builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
 97:         } catch (Exception e) {
 98:             // Handle generic exceptions
 99:             Map<String, String> responseObj = new HashMap<>();
100:             responseObj.put("error", e.getMessage());
101:             builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
102:         }
103:         return builder.build();
104:     }
105:     /**
106:      * <p>
107:      * Validates the given Member variable and throws validation exceptions based on the type of error. If the error is standard
108:      * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
109:      * </p>
110:      * <p>
111:      * If the error is caused because an existing member with the same email is registered it throws a regular validation
112:      * exception so that it can be interpreted separately.
113:      * </p>
114:      *
115:      * @param member Member to be validated
116:      * @throws ConstraintViolationException If Bean Validation errors exist
117:      * @throws ValidationException If member with the same email already exists
118:      */
119:     private void validateMember(Member member) throws ConstraintViolationException, ValidationException {
120:         // Create a bean validator and check for issues.
121:         Set<ConstraintViolation<Member>> violations = validator.validate(member);
122:         if (!violations.isEmpty()) {
123:             throw new ConstraintViolationException(new HashSet<>(violations));
124:         }
125:         // Check the uniqueness of the email address
126:         if (emailAlreadyExists(member.getEmail())) {
127:             throw new ValidationException("Unique Email Violation");
128:         }
129:     }
130:     /**
131:      * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
132:      * by clients to show violations.
133:      *
134:      * @param violations A set of violations that needs to be reported
135:      * @return JAX-RS response containing all violations
136:      */
137:     private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
138:         log.fine("Validation completed. violations found: " + violations.size());
139:         Map<String, String> responseObj = new HashMap<>();
140:         for (ConstraintViolation<?> violation : violations) {
141:             responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
142:         }
143:         return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
144:     }
145:     /**
146:      * Checks if a member with the same email address is already registered. This is the only way to easily capture the
147:      * "@UniqueConstraint(columnNames = "email")" constraint from the Member class.
148:      *
149:      * @param email The email to check
150:      * @return True if the email already exists, and false otherwise
151:      */
152:     public boolean emailAlreadyExists(String email) {
153:         Member member = null;
154:         try {
155:             member = repository.findByEmail(email);
156:         } catch (NoResultException e) {
157:             // ignore
158:         }
159:         return member != null;
160:     }
161: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/service/MemberRegistration.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.service;
18: import org.jboss.as.quickstarts.kitchensink.model.Member;
19: import jakarta.ejb.Stateless;
20: import jakarta.enterprise.event.Event;
21: import jakarta.inject.Inject;
22: import jakarta.persistence.EntityManager;
23: import java.util.logging.Logger;
24: // The @Stateless annotation eliminates the need for manual transaction demarcation
25: @Stateless
26: public class MemberRegistration {
27:     @Inject
28:     private Logger log;
29:     @Inject
30:     private EntityManager em;
31:     @Inject
32:     private Event<Member> memberEventSrc;
33:     public void register(Member member) throws Exception {
34:         log.info("Registering " + member.getName());
35:         em.persist(member);
36:         memberEventSrc.fire(member);
37:     }
38: }
```

## File: legacy/src/main/java/org/jboss/as/quickstarts/kitchensink/util/Resources.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.util;
18: import java.util.logging.Logger;
19: import jakarta.enterprise.inject.Produces;
20: import jakarta.enterprise.inject.spi.InjectionPoint;
21: import jakarta.persistence.EntityManager;
22: import jakarta.persistence.PersistenceContext;
23: /**
24:  * This class uses CDI to alias Jakarta EE resources, such as the persistence context, to CDI beans
25:  *
26:  * <p>
27:  * Example injection on a managed bean field:
28:  * </p>
29:  *
30:  * <pre>
31:  * &#064;Inject
32:  * private EntityManager em;
33:  * </pre>
34:  */
35: public class Resources {
36:     @Produces
37:     @PersistenceContext
38:     private EntityManager em;
39:     @Produces
40:     public Logger produceLog(InjectionPoint injectionPoint) {
41:         return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
42:     }
43: }
```

## File: legacy/src/main/resources/META-INF/persistence.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
18:              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
19:              xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
20:                                  https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
21:              version="3.0">
22:    <persistence-unit name="primary">
23:       <!-- If you are running in a production environment, add a managed
24:          data source, this example data source is just for development and testing! -->
25:       <!-- The datasource is deployed as WEB-INF/kitchensink-quickstart-ds.xml, you
26:          can find it in the source at src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml -->
27:       <jta-data-source>java:jboss/datasources/KitchensinkQuickstartDS</jta-data-source>
28:       <properties>
29:          <!-- Properties for Hibernate -->
30:          <property name="hibernate.hbm2ddl.auto" value="create-drop" />
31:          <property name="hibernate.show_sql" value="false" />
32:       </properties>
33:    </persistence-unit>
34: </persistence>
```

## File: legacy/src/main/resources/import.sql
```sql
 1: --
 2: -- JBoss, Home of Professional Open Source
 3: -- Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4: -- contributors by the @authors tag. See the copyright.txt in the
 5: -- distribution for a full listing of individual contributors.
 6: --
 7: -- Licensed under the Apache License, Version 2.0 (the "License");
 8: -- you may not use this file except in compliance with the License.
 9: -- You may obtain a copy of the License at
10: -- http://www.apache.org/licenses/LICENSE-2.0
11: -- Unless required by applicable law or agreed to in writing, software
12: -- distributed under the License is distributed on an "AS IS" BASIS,
13: -- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14: -- See the License for the specific language governing permissions and
15: -- limitations under the License.
16: --
17: -- You can use this file to load seed data into the database using SQL statements
18: insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212')
```

## File: legacy/src/main/webapp/resources/css/screen.css
```css
  1: /*
  2:  * JBoss, Home of Professional Open Source
  3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
  4:  * contributors by the @authors tag. See the copyright.txt in the
  5:  * distribution for a full listing of individual contributors.
  6:  *
  7:  * Licensed under the Apache License, Version 2.0 (the "License");
  8:  * you may not use this file except in compliance with the License.
  9:  * You may obtain a copy of the License at
 10:  * http://www.apache.org/licenses/LICENSE-2.0
 11:  * Unless required by applicable law or agreed to in writing, software
 12:  * distributed under the License is distributed on an "AS IS" BASIS,
 13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 14:  * See the License for the specific language governing permissions and
 15:  * limitations under the License.
 16:  */
 17: /* Core styles for the page */
 18: body {
 19:   margin: 0;
 20:   padding: 0;
 21:   background-color: #F1F1F1;
 22:   font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
 23:   font-size: 0.8em;
 24:   color:#363636;
 25: }
 26: #container {
 27:   margin: 0 auto;
 28:   padding: 0 20px 10px 20px;
 29:   border-top: 5px solid #000000;
 30:   border-left: 5px solid #8c8f91;
 31:   border-right: 5px solid #8c8f91;
 32:   border-bottom: 25px solid #8c8f91;
 33:   width: 865px; /* subtract 40px from banner width for padding */
 34:   background: #FFFFFF;
 35:   background-image: url(#{request.contextPath}/resources/gfx/headerbkg.png);
 36:   background-repeat: repeat-x;
 37:   padding-top: 30px;
 38:   box-shadow: 3px 3px 15px #d5d5d5;
 39: }
 40: #content {
 41:   float: left;
 42:   width: 500px;
 43:   margin: 20px;
 44: }
 45: #aside {
 46:   font-size: 0.9em;
 47:   width: 275px;
 48:   float: left;
 49:   margin: 20px 0px;
 50:   border: 1px solid #D5D5D5;
 51:   background: #F1F1F1;
 52:   background-image: url(#{request.contextPath}/resources/gfx/asidebkg.png);
 53:   background-repeat: repeat-x;
 54:   padding: 20px;
 55: }
 56: #aside ul {
 57:   padding-left: 30px;
 58: }
 59: .dualbrand {
 60:   float: right;
 61:   padding-right: 10px;
 62: }
 63: #footer {
 64:   clear: both;
 65:   text-align: center;
 66:   color: #666666;
 67:   font-size: 0.85em;
 68: }
 69: code {
 70:   font-size: 1.1em;
 71: }
 72: a {
 73:   color: #4a5d75;
 74:   text-decoration: none;
 75: }
 76: a:hover {
 77:   color: #369;
 78:   text-decoration: underline;
 79: }
 80: h1 {
 81:   color:#243446;
 82:   font-size: 2.25em;
 83: }
 84: h2 {
 85:   font-size: 1em;
 86: }
 87: h3 {
 88:   color:#243446;
 89: }
 90: h4 {
 91: }
 92: h5 {
 93: }
 94: h6 {
 95: }
 96: /* Member registration styles */
 97: span.invalid {
 98:   padding-left: 3px;
 99:   color: red;
100: }
101: form {
102:   padding: 1em;
103:   font: 80%/1 sans-serif;
104:   width: 375px;
105:   border: 1px solid #D5D5D5;
106: }
107: label {
108:   float: left;
109:   width: 15%;
110:   margin-left: 20px;
111:   margin-right: 0.5em;
112:   padding-top: 0.2em;
113:   text-align: right;
114:   font-weight: bold;
115:   color:#363636;
116: }
117: input {
118:   margin-bottom: 8px;
119: }
120: .register {
121:   float: left;
122:   margin-left: 85px;
123: }
124: /*  -----  table style  -------  */
125: /*  = Simple Table style (black header, grey/white stripes  */
126: .simpletablestyle {
127:   background-color:#E6E7E8;
128:   clear:both;
129:   width: 550px;
130: }
131: .simpletablestyle img {
132:   border:0px;
133: }
134: .simpletablestyle td {
135:   height:2em;
136:   padding-left: 6px;
137:   font-size:11px;
138:   padding:5px 5px;
139: }
140: .simpletablestyle th {
141: 	background: url(#{request.contextPath}/resources/gfx/bkg-blkheader.png) black repeat-x top left;
142:   font-size:12px;
143:   font-weight:normal;
144:   padding:0 10px 0 5px;
145:   border-bottom:#999999 dotted 1px;
146: }
147: .simpletablestyle thead {
148:   background: url(#{request.contextPath}/resources/gfx/bkg-blkheader.png) black repeat-x top left;
149:   height:31px;
150:   font-size:10px;
151:   font-weight:bold;
152:   color:#FFFFFF;
153:   text-align:left;
154: }
155: .simpletablestyle .header a {
156:   color:#94aebd;
157: }
158: .simpletablestype tfoot {
159: 	height: 20px;
160:   font-size: 10px;
161:   font-weight: bold;
162:   background-color: #EAECEE;
163:   text-align: center;
164: }
165: .simpletablestyle tr.header td {
166:   padding: 0px 10px 0px 5px;
167: }
168: .simpletablestyle .subheader {
169:   background-color: #e6e7e8;
170:   font-size:10px;
171:   font-weight:bold;
172:   color:#000000;
173:   text-align:left;
174: }
175: /* Using new CSS3 selectors for styling*/
176: .simpletablestyle tr:nth-child(odd) {
177:   background: #f4f3f3;
178: }
179: .simpletablestyle tr:nth-child(even) {
180:   background: #ffffff;
181: }
182: .simpletablestyle td a:hover {
183:   color:#3883ce;
184:   text-decoration:none; 
185: }
```

## File: legacy/src/main/webapp/WEB-INF/templates/default.xhtml
```
 1: <!--
 2:     JBoss, Home of Professional Open Source
 3:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:     contributors by the @authors tag. See the copyright.txt in the
 5:     distribution for a full listing of individual contributors.
 6: 
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
18:     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
19: <html xmlns="http://www.w3.org/1999/xhtml"
20:     xmlns:h="http://java.sun.com/jsf/html"
21:     xmlns:ui="http://java.sun.com/jsf/facelets">
22: <h:head>
23:     <title>kitchensink</title>
24:     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
25:     <h:outputStylesheet name="css/screen.css" />
26: </h:head>
27: <h:body>
28:     <div id="container">
29:         <div class="dualbrand">
30:             <img src="resources/gfx/rhjb_eap_logo.png" width="300" height="175" />
31:         </div>
32:         <div id="content">
33:             <ui:insert name="content">
34:                     [Template content will be inserted here]
35:          </ui:insert>
36:         </div>
37:         <div id="aside">
38:             <p>Learn more about Red Hat JBoss Enterprise Application Platform.</p>
39:             <ul>
40:                 <li><a href="https://access.redhat.com/documentation/en/red-hat-jboss-enterprise-application-platform/">Documentation</a></li>
41:                 <li><a href="http://www.redhat.com/en/technologies/jboss-middleware/application-platform">Product Information</a></li>
42:             </ul>
43:         </div>
44:         <div id="footer">
45:             <p>
46:                 This project was generated from a Maven archetype from
47:                 JBoss.<br />
48:             </p>
49:         </div>
50:     </div>
51: </h:body>
52: </html>
```

## File: legacy/src/main/webapp/WEB-INF/beans.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <!-- Marker file indicating CDI should be enabled -->
18: <beans xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
19:     xsi:schemaLocation="
20:       http://xmlns.jcp.org/xml/ns/javaee
21:       http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
22:     bean-discovery-mode="all">
23: </beans>
```

## File: legacy/src/main/webapp/WEB-INF/faces-config.xml
```xml
 1: <?xml version="1.0"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <!-- This file is not required if you don't need any extra configuration. -->
18: <faces-config xmlns="http://xmlns.jcp.org/xml/ns/javaee"
19:               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
20:               xsi:schemaLocation="
21:                   https://jakarta.ee/xml/ns/jakartaee
22:                   https://jakarta.ee/xml/ns/jakartaee/web-facesconfig_4_0.xsd"
23:               version="4.0">
24:     <!-- This descriptor activates the JSF Servlet -->
25:     <!-- Write your navigation rules here. You are encouraged to use CDI
26:         for creating @Named managed beans. -->
27: </faces-config>
```

## File: legacy/src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <!-- This is an unmanaged datasource. It should be used for proofs of concept
18:    or testing only. It uses H2, a lightweight, in-memory, example database that
19:    ships with JBoss EAP. It is not robust or scalable, is not supported,
20:    and should NOT be used in a production environment! -->
21: <datasources xmlns="http://www.jboss.org/ironjacamar/schema"
22:     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
23:     xsi:schemaLocation="http://www.jboss.org/ironjacamar/schema http://docs.jboss.org/ironjacamar/schema/datasources_1_0.xsd">
24:     <!-- The datasource is bound into JNDI at this location. We reference
25:         this in META-INF/persistence.xml -->
26:     <datasource jndi-name="java:jboss/datasources/KitchensinkQuickstartDS"
27:         pool-name="kitchensink-quickstart" enabled="true"
28:         use-java-context="true">
29:         <connection-url>jdbc:h2:mem:kitchensink-quickstart;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1</connection-url>
30:         <driver>h2</driver>
31:         <security>
32:             <user-name>sa</user-name>
33:             <password>sa</password>
34:         </security>
35:     </datasource>
36: </datasources>
```

## File: legacy/src/main/webapp/index.html
```html
 1: <!--
 2:     JBoss, Home of Professional Open Source
 3:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:     contributors by the @authors tag. See the copyright.txt in the
 5:     distribution for a full listing of individual contributors.
 6:     Licensed under the Apache License, Version 2.0 (the "License");
 7:     you may not use this file except in compliance with the License.
 8:     You may obtain a copy of the License at
 9:     http://www.apache.org/licenses/LICENSE-2.0
10:     Unless required by applicable law or agreed to in writing, software
11:     distributed under the License is distributed on an "AS IS" BASIS,
12:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
13:     See the License for the specific language governing permissions and
14:     limitations under the License.
15: -->
16: <!-- Plain HTML page that kicks us into the app -->
17: <html>
18:     <head>
19:         <meta http-equiv="Refresh" content="0; URL=index.jsf">
20:     </head>
21: </html>
```

## File: legacy/src/main/webapp/index.xhtml
```
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7: 
 8:     Licensed under the Apache License, Version 2.0 (the "License");
 9:     you may not use this file except in compliance with the License.
10:     You may obtain a copy of the License at
11:     http://www.apache.org/licenses/LICENSE-2.0
12:     Unless required by applicable law or agreed to in writing, software
13:     distributed under the License is distributed on an "AS IS" BASIS,
14:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
15:     See the License for the specific language governing permissions and
16:     limitations under the License.
17: -->
18: <ui:composition xmlns="http://www.w3.org/1999/xhtml"
19:                 xmlns:ui="jakarta.faces.facelets"
20:                 xmlns:f="jakarta.faces.core"
21:                 xmlns:h="jakarta.faces.html"
22:                 template="/WEB-INF/templates/default.xhtml">
23:     <ui:define name="content">
24:         <h1>Welcome to JBoss!</h1>
25: 
26:         <div>
27:             <p>You have successfully deployed a Jakarta EE Enterprise
28:                 Application.</p>
29:         </div>
30: 
31:         <h:form id="reg">
32:             <h2>Member Registration</h2>
33:             <p>Enforces annotation-based constraints defined on the
34:                 model class.</p>
35:             <h:panelGrid columns="3" columnClasses="titleCell">
36:                 <h:outputLabel for="name" value="Name:" />
37:                 <h:inputText id="name" value="#{newMember.name}" />
38:                 <h:message for="name" errorClass="invalid" />
39: 
40:                 <h:outputLabel for="email" value="Email:" />
41:                 <h:inputText id="email" value="#{newMember.email}" />
42:                 <h:message for="email" errorClass="invalid" />
43: 
44:                 <h:outputLabel for="phoneNumber" value="Phone #:" />
45:                 <h:inputText id="phoneNumber"
46:                              value="#{newMember.phoneNumber}" />
47:                 <h:message for="phoneNumber" errorClass="invalid" />
48:             </h:panelGrid>
49: 
50:             <p>
51:                 <h:panelGrid columns="2">
52:                     <h:commandButton id="register"
53:                                      action="#{memberController.register}"
54:                                      value="Register" styleClass="register" />
55:                     <h:messages styleClass="messages"
56:                                 errorClass="invalid" infoClass="valid"
57:                                 warnClass="warning" globalOnly="true" />
58:                 </h:panelGrid>
59:             </p>
60:         </h:form>
61:         <h2>Members</h2>
62:         <h:panelGroup rendered="#{empty members}">
63:             <em>No registered members.</em>
64:         </h:panelGroup>
65:         <h:dataTable var="_member" value="#{members}"
66:                      rendered="#{not empty members}"
67:                      styleClass="simpletablestyle">
68:             <h:column>
69:                 <f:facet name="header">Id</f:facet>
70:                     #{_member.id}
71:             </h:column>
72:             <h:column>
73:                 <f:facet name="header">Name</f:facet>
74:                     #{_member.name}
75:             </h:column>
76:             <h:column>
77:                 <f:facet name="header">Email</f:facet>
78:                     #{_member.email}
79:             </h:column>
80:             <h:column>
81:                 <f:facet name="header">Phone #</f:facet>
82:                     #{_member.phoneNumber}
83:             </h:column>
84:             <h:column>
85:                 <f:facet name="header">REST URL</f:facet>
86:                 <a
87:                     href="#{request.contextPath}/rest/members/#{_member.id}">/rest/members/#{_member.id}</a>
88:             </h:column>
89:             <f:facet name="footer">
90:                 REST URL for all members: <a
91:                     href="#{request.contextPath}/rest/members">/rest/members</a>
92:             </f:facet>
93:         </h:dataTable>
94:     </ui:define>
95: </ui:composition>
```

## File: legacy/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationIT.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.test;
18: import static org.junit.Assert.assertNotNull;
19: import java.util.logging.Logger;
20: import jakarta.inject.Inject;
21: import org.jboss.arquillian.container.test.api.Deployment;
22: import org.jboss.arquillian.junit.Arquillian;
23: import org.jboss.as.quickstarts.kitchensink.model.Member;
24: import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
25: import org.jboss.as.quickstarts.kitchensink.util.Resources;
26: import org.jboss.shrinkwrap.api.Archive;
27: import org.jboss.shrinkwrap.api.ShrinkWrap;
28: import org.jboss.shrinkwrap.api.asset.StringAsset;
29: import org.jboss.shrinkwrap.api.spec.WebArchive;
30: import org.junit.Test;
31: import org.junit.runner.RunWith;
32: @RunWith(Arquillian.class)
33: public class MemberRegistrationIT {
34:     @Deployment
35:     public static Archive<?> createTestArchive() {
36:         return ShrinkWrap.create(WebArchive.class, "test.war")
37:             .addClasses(Member.class, MemberRegistration.class, Resources.class)
38:             .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
39:             .addAsWebInfResource(new StringAsset("<beans xmlns=\"https://jakarta.ee/xml/ns/jakartaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
40:                         + "xsi:schemaLocation=\"https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/beans_3_0.xsd\"\n"
41:                         + "bean-discovery-mode=\"all\">\n"
42:                         + "</beans>"), "beans.xml")
43:             // Deploy our test datasource
44:             .addAsWebInfResource("test-ds.xml");
45:     }
46:     @Inject
47:     MemberRegistration memberRegistration;
48:     @Inject
49:     Logger log;
50:     @Test
51:     public void testRegister() throws Exception {
52:         Member newMember = new Member();
53:         newMember.setName("Jane Doe");
54:         newMember.setEmail("jane@mailinator.com");
55:         newMember.setPhoneNumber("2125551234");
56:         memberRegistration.register(newMember);
57:         assertNotNull(newMember.getId());
58:         log.info(newMember.getName() + " was persisted with id " + newMember.getId());
59:     }
60: }
```

## File: legacy/src/test/java/org/jboss/as/quickstarts/kitchensink/test/RemoteMemberRegistrationIT.java
```java
 1: /*
 2:  * JBoss, Home of Professional Open Source
 3:  * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 4:  * contributors by the @authors tag. See the copyright.txt in the
 5:  * distribution for a full listing of individual contributors.
 6:  *
 7:  * Licensed under the Apache License, Version 2.0 (the "License");
 8:  * you may not use this file except in compliance with the License.
 9:  * You may obtain a copy of the License at
10:  * http://www.apache.org/licenses/LICENSE-2.0
11:  * Unless required by applicable law or agreed to in writing, software
12:  * distributed under the License is distributed on an "AS IS" BASIS,
13:  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:  * See the License for the specific language governing permissions and
15:  * limitations under the License.
16:  */
17: package org.jboss.as.quickstarts.kitchensink.test;
18: import jakarta.json.Json;
19: import jakarta.json.JsonObject;
20: import java.util.logging.Logger;
21: import java.net.URI;
22: import java.net.URISyntaxException;
23: import java.net.http.HttpClient;
24: import java.net.http.HttpRequest;
25: import java.net.http.HttpResponse;
26: import org.jboss.as.quickstarts.kitchensink.model.Member;
27: import org.junit.Assert;
28: import org.junit.Test;
29: public class RemoteMemberRegistrationIT {
30:     private static final Logger log = Logger.getLogger(RemoteMemberRegistrationIT.class.getName());
31:     protected URI getHTTPEndpoint() {
32:         String host = getServerHost();
33:         if (host == null) {
34:             host = "http://localhost:8080/kitchensink";
35:         }
36:         try {
37:             return new URI(host + "/rest/members");
38:         } catch (URISyntaxException ex) {
39:             throw new RuntimeException(ex);
40:         }
41:     }
42:     private String getServerHost() {
43:         String host = System.getenv("SERVER_HOST");
44:         if (host == null) {
45:             host = System.getProperty("server.host");
46:         }
47:         return host;
48:     }
49:     @Test
50:     public void testRegister() throws Exception {
51:         Member newMember = new Member();
52:         newMember.setName("Jane Doe");
53:         newMember.setEmail("jane@mailinator.com");
54:         newMember.setPhoneNumber("2125551234");
55:         JsonObject json = Json.createObjectBuilder()
56:                 .add("name", "Jane Doe")
57:                 .add("email", "jane@mailinator.com")
58:                 .add("phoneNumber", "2125551234").build();
59:         HttpRequest request = HttpRequest.newBuilder(getHTTPEndpoint())
60:                 .header("Content-Type", "application/json")
61:                 .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
62:                 .build();
63:         HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
64:         Assert.assertEquals(200, response.statusCode());
65:         Assert.assertEquals("", response.body().toString() );
66:     }
67: }
```

## File: legacy/src/test/resources/META-INF/test-persistence.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <persistence version="2.0"
18:    xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
19:    xsi:schemaLocation="
20:         http://java.sun.com/xml/ns/persistence
21:         http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
22:    <persistence-unit name="primary">
23:       <!-- We use a different datasource for tests, so as to not overwrite
24:          production data. This is an unmanaged data source, backed by H2, an in memory
25:          database. Production applications should use a managed datasource. -->
26:       <!-- The datasource is deployed as WEB-INF/test-ds.xml,
27:          you can find it in the source at src/test/resources/test-ds.xml -->
28:       <jta-data-source>java:jboss/datasources/KitchensinkQuickstartTestDS</jta-data-source>
29:       <properties>
30:          <!-- Properties for Hibernate -->
31:          <property name="hibernate.hbm2ddl.auto" value="create-drop" />
32:          <property name="hibernate.show_sql" value="false" />
33:       </properties>
34:    </persistence-unit>
35: </persistence>
```

## File: legacy/src/test/resources/arquillian.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2017, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <arquillian xmlns="http://jboss.org/schema/arquillian" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
18:             xsi:schemaLocation="http://jboss.org/schema/arquillian
19:     http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
20:     <!-- Uncomment to have test archives exported to the file system for inspection -->
21:     <!--<engine>
22:         <property name="deploymentExportPath">target/</property>
23:     </engine>-->
24:     <!-- Example configuration for a managed JBoss EAP instance -->
25:     <container qualifier="jboss" default="true">
26:         <!-- By default, Arquillian will use the JBOSS_HOME environment variable to find the JBoss EAP installation.
27:              If you prefer not to define the JBOSS_HOME environment variable, alternatively you can uncomment the
28:              following `jbossHome` property and replace EAP_HOME with the path to your JBoss EAP installation. -->
29:         <!--<configuration>
30:             <property name="jbossHome">EAP_HOME</property>
31:         </configuration> -->
32:     </container>
33: </arquillian>
```

## File: legacy/src/test/resources/test-ds.xml
```xml
 1: <?xml version="1.0" encoding="UTF-8"?>
 2: <!--
 3:     JBoss, Home of Professional Open Source
 4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 5:     contributors by the @authors tag. See the copyright.txt in the
 6:     distribution for a full listing of individual contributors.
 7:     Licensed under the Apache License, Version 2.0 (the "License");
 8:     you may not use this file except in compliance with the License.
 9:     You may obtain a copy of the License at
10:     http://www.apache.org/licenses/LICENSE-2.0
11:     Unless required by applicable law or agreed to in writing, software
12:     distributed under the License is distributed on an "AS IS" BASIS,
13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
14:     See the License for the specific language governing permissions and
15:     limitations under the License.
16: -->
17: <!-- This is an unmanaged datasource. It should be used for proofs of concept
18:    or testing only. It uses H2, a lightweight, in-memory, example database that
19:    ships with JBoss EAP. It is not robust or scalable, is not supported,
20:    and should NOT be used in a production environment! -->
21: <datasources xmlns="http://www.jboss.org/ironjacamar/schema"
22:    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
23:    xsi:schemaLocation="http://www.jboss.org/ironjacamar/schema http://docs.jboss.org/ironjacamar/schema/datasources_1_0.xsd">
24:    <!-- The datasource is bound into JNDI at this location. We reference
25:       this in META-INF/test-persistence.xml -->
26:    <datasource jndi-name="java:jboss/datasources/KitchensinkQuickstartTestDS"
27:       pool-name="kitchensink-quickstart-test" enabled="true"
28:       use-java-context="true">
29:       <connection-url>jdbc:h2:mem:kitchensink-quickstart-test;DB_CLOSE_DELAY=-1</connection-url>
30:       <driver>h2</driver>
31:       <security>
32:          <user-name>sa</user-name>
33:          <password>sa</password>
34:       </security>
35:    </datasource>
36: </datasources>
```

## File: legacy/.cheatsheet.xml
```xml
  1: <?xml version="1.0" encoding="UTF-8"?>
  2: <!--
  3:     JBoss, Home of Professional Open Source
  4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
  5:     contributors by the @authors tag. See the copyright.txt in the
  6:     distribution for a full listing of individual contributors.
  7:     Licensed under the Apache License, Version 2.0 (the "License");
  8:     you may not use this file except in compliance with the License.
  9:     You may obtain a copy of the License at
 10:     http://www.apache.org/licenses/LICENSE-2.0
 11:     Unless required by applicable law or agreed to in writing, software
 12:     distributed under the License is distributed on an "AS IS" BASIS,
 13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 14:     See the License for the specific language governing permissions and
 15:     limitations under the License.
 16: -->
 17: <cheatsheet title="CDI + JSF + EJB + JTA + Bean Validation + JAX-RS + Arquillian: Kitchensink quickstart">
 18:   <intro>
 19:     <description>
 20: This quickstart shows off all the new features of Jakarta EE, and makes a great starting point for your project.
 21: <br/><br/>
 22: <b>Bean Validation</b>
 23: <br/><br/>
 24: Bean Validation is a new specification in Jakarta EE, inspired by Hibernate Validator. It allows application developers to specify constraints once (often in their domain model), and have them applied in all layers of the application, protecting data and giving useful feedback to users.
 25: <br/><br/>
 26: <b>JAX-RS: The Java API for RESTful Web Services</b>
 27: <br/><br/>
 28: JAX-RS is a specification in Jakarta EE. It allows application developers to easily expose Java services as RESTful web services.
 29: </description>
 30: </intro>
 31:   <item
 32:         skip="false"
 33:         title="The kitchensink example in depth">
 34:      <description>
 35:        The kitchensink application shows off a number of Jakarta EE technologies such as CDI, JSF, EJB, JTA, JAX-RS and Arquillian.
 36:        It does this by providing a member registration database, available via JSF and JAX-RS.
 37: <br/><br/>
 38: As usual, let&apos;s start by looking at the necessary deployment descriptors.
 39: By now, we're very used to seeing <b>beans.xml</b> and <b>faces-config.xml</b> in <b>WEB-INF/</b>
 40: (which can be found in the <b>src/main/webapp</b> directory of the example).
 41: Notice that, once again, we don&apos;t need a web.xml.
 42: There are two configuration files in <b>WEB-INF/classes/META-INF</b>
 43: (which can be found in the <b>src/main/resources</b> directory of the example) — <b>persistence.xml</b>,
 44: which sets up JPA, and <b>import.sql</b> which Hibernate, the JPA provider in JBoss EAP,
 45: will use to load the initial users into the application when the application starts.
 46: We discussed both of these files in detail in The <b>greeter example in depth</b>, and these are largely the same.
 47:     </description>
 48:     <command
 49:     required="true"
 50:     returns="currentProject"
 51:     serialization="org.jboss.tools.project.examples.cheatsheet.getProjectForCheatsheet"/>
 52:   </item>
 53:    <item
 54:          title="default.xhtml">
 55:       <description>
 56:       Next, let&apos;s take a look at the JSF view the user sees. As usual, we use a template to provide the sidebar and footer. This one lives in <b>WEB-INF/templates/default.xhtml</b>:
 57:       </description>
 58:       <subitem
 59:             label="We have a common &lt;head&gt; element, where we define styles and more. "
 60:             skip="true">
 61:         <command
 62:       required="false"
 63:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/WEB-INF/templates/default.xhtml,fromLine=22,toLine=26)"/>
 64:     </subitem>
 65:     <subitem
 66:             label="This application defines a common sidebar, putting them in the template means we only have to define them once."
 67:             skip="true">
 68:     <command
 69:       required="false"
 70:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/WEB-INF/templates/default.xhtml,fromLine=37,toLine=52)"/>
 71:     </subitem>
 72:     <subitem
 73:             label="and a common footer... "
 74:             skip="true">
 75:         <command
 76:       required="false"
 77:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/WEB-INF/templates/default.xhtml,fromLine=53,toLine=58)"/>
 78:     </subitem>
 79:     <subitem
 80:             label="The content is inserted here, and defined by views using this template. "
 81:             skip="true">
 82:         <command
 83:       required="false"
 84:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/WEB-INF/templates/default.xhtml,fromLine=32,toLine=36)"/>
 85:        </subitem>
 86:   </item>
 87:   <item
 88:       title="index.xhtml">
 89:       <description>
 90:       That leaves the main page, index.xhtml, in which we place the content unique to the main page:
 91:       </description>
 92:       <subitem
 93:             label="The JSF form allows us to register new users. There should be one already created when the application started."
 94:             skip="true">
 95:         <command
 96:       required="false"
 97:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/index.xhtml,fromLine=33,toLine=62)"/>
 98:     </subitem>
 99:     <subitem
100:             label="The application uses Bean Validation to validate data entry. The error messages from Bean Validation are automatically attached to the relevant field by JSF, and adding a messages JSF component will display them."
101:             skip="true">
102:       </subitem>
103:       <subitem
104:             label="Name validation">
105:     <command
106:       required="false"
107:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/index.xhtml,fromLine=39,toLine=40)"/>
108:     </subitem>
109:     <subitem
110:             label="Email validation"
111:             skip="true">
112:     <command
113:       required="false"
114:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/index.xhtml,fromLine=43,toLine=44)"/>
115:     </subitem>
116:     <subitem
117:             label="Phone number validation"
118:             skip="true">
119:     <command
120:       required="false"
121:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/index.xhtml,fromLine=47,toLine=49)"/>
122:     </subitem>
123:     <subitem
124:             label="This application exposes REST endpoints for each registered member. The application helpfully displays the URL to the REST endpoint on this page. "
125:             skip="true">
126:     <command
127:       required="false"
128:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/webapp/index.xhtml,fromLine=86,toLine=90)"/>
129:     </subitem>
130:   </item>
131:   <item
132:         skip="true"
133:         title="Member.java">
134:      <description>
135:        Next, let&apos;s take a look at the Member entity, before we look at how the application is wired together:
136:     </description>
137:     <subitem
138:             label="As usual with JPA, we define that the class is an entity by adding @Entity"
139:             skip="true">
140:     <command
141:       required="false"
142:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java,fromLine=37)"/>
143:   </subitem>
144:   <subitem
145:             label="Members are exposed as a RESTful service using JAX-RS. We can use JAXB to map the object to XML and to do this we need to add @XmlRootElement."
146:             skip="true">
147:     <command
148:       required="false"
149:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java,fromLine=38)"/>
150:   </subitem>
151:   <subitem
152:             label="Bean Validation allows constraints to be defined once (on the entity) and applied everywhere. Here we constrain the person's name to a certain size and regular expression. "
153:             skip="true">
154:     <command
155:       required="false"
156:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java,fromLine=46,toLine=48)"/>
157:   </subitem>
158:   <subitem
159:             label="Hibernate Validator also offers some extra validations such as @Email. "
160:             skip="true">
161:     <command
162:       required="false"
163:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java,fromLine=53,toLine=53)"/>
164:   </subitem>
165:   <subitem
166:             label="@Digits, @NotNull and @Size are further examples of constraints. "
167:             skip="true">
168:     <command
169:       required="false"
170:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/model/Member.java,fromLine=56,toLine=58)"/>
171:   </subitem>
172:   </item>
173:   <item
174:         skip="true"
175:         title="MemberRepository.java">
176:      <description>
177:        Let&apos;s take a look at MemberRepository, which is responsible for interactions with the persistence layer:
178:     </description>
179:     <subitem
180:             label="The bean is application scoped, as it is a singleton."
181:             skip="true">
182:     <command
183:       required="false"
184:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberRepository.java,fromLine=29)"/>
185:   </subitem>
186:   <subitem
187:             label="The entity manager is injected, to allow interaction with JPA."
188:             skip="true">
189:     <command
190:       required="false"
191:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberRepository.java,fromLine=32)"/>
192:   </subitem>
193:   <subitem
194:             label="The JPA criteria api is used to load a member by his or her unique identifier, email address."
195:             skip="true">
196:     <command
197:       required="false"
198:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberRepository.java,fromLine=40,toLine=47)"/>
199:   </subitem>
200:   <subitem
201:             label="The criteria api can also be used to load lists of entities ."
202:             skip="true">
203:     <command
204:       required="false"
205:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberRepository.java,fromLine=51,toLine=58)"/>
206:   </subitem>
207:   </item>
208:    <item
209:         skip="true"
210:         title="MemberListProducer.java">
211:      <description>
212:        Next, let&apos;s take a look at MemberListProducer, which is responsible for building the list of members, ordered by name. It uses JPA 2 criteria to do this.
213:     </description>
214:     <subitem
215:             label="This bean is request scoped, meaning that any fields (such as members) will be stored for the entire request."
216:             skip="true">
217:        <command
218:       required="false"
219:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberListProducer.java,fromLine=30)"/>
220:   </subitem>
221:   <subitem
222:             label="The MemberRepository is responsible for interactions with the persistence layer"
223:             skip="true">
224:        <command
225:       required="false"
226:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberListProducer.java,fromLine=33,toLine=34)"/>
227:   </subitem>
228:   <subitem
229:             label="The list of members is exposed as a producer method. It's also available via EL. "
230:             skip="true">
231:     <command
232:       required="false"
233:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberListProducer.java,fromLine=40,toLine=41)"/>
234:   </subitem>
235:   <subitem
236:             label="The observer method is notified whenever a member is created, removed, or updated. This allows us to refresh the list of members whenever they are needed. This is a good approach as it allows us to cache the list of members, but keep it up to date at the same time."
237:             skip="true">
238:     <command
239:       required="false"
240:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/data/MemberListProducer.java,fromLine=46,toLine=48)"/>
241:   </subitem>
242:   </item>
243:   <item
244:         skip="true"
245:         title="MemberRegistration.java">
246:      <description>
247:        Let&apos;s now look at MemberRegistration, the class that allows us to create new members from the JSF page
248:     </description>
249:     <subitem
250:             label="This bean requires transactions as it needs to write to the database. Making this an EJB gives us access to declarative transactions - much simpler than manual transaction control! "
251:             skip="true">
252:     <command
253:       required="false"
254:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/service/MemberRegistration.java,fromLine=28)"/>
255:   </subitem>
256:   <subitem
257:             label="Here we inject a JDK logger, defined in the Resources class."
258:             skip="true">
259:     <command
260:       required="false"
261:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/service/MemberRegistration.java,fromLine=31)"/>
262:   </subitem>
263:   <subitem
264:             label="An event is sent every time a member is updated. This allows other pieces of code (in this quickstart the member list is refreshed) to react to changes in the member list without any coupling to this class. "
265:             skip="true">
266:     <command
267:       required="false"
268:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/service/MemberRegistration.java,fromLine=43)"/>
269:   </subitem>
270:   </item>
271:   <item
272:         skip="true"
273:         title="Resources.java">
274:      <description>
275:        Now, let's take a look at the Resources class, which provides resources such as the entity manager. CDI recommends using "resource producers", as we do in this example, to alias resources to CDI beans, allowing for a consistent style throughout our application:
276:     </description>
277:     <subitem
278:             label="We use the 'resource producer' pattern, from CDI, to 'alias' the old fashioned @PersistenceContext injection of the entity manager to a CDI style injection. This allows us to use a consistent injection style (@Inject) throughout the application. "
279:             skip="true">
280:       <command
281:       required="false"
282:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/util/Resources.java,fromLine=43)"/>
283:     </subitem>
284:     <subitem
285:             label="We expose a JDK logger for injection. In order to save a bit more boiler plate, we automatically set the logger category as the class name! "
286:             skip="true">
287:       <command
288:       required="false"
289:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/util/Resources.java,fromLine=47)"/>
290:     </subitem>
291:     <subitem
292:             label="We expose the FacesContext via a producer method, which allows it to be injected. If we were adding tests, we could also then mock it out."
293:             skip="true">
294:       <command
295:       required="false"
296:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/util/Resources.java,fromLine=52)"/>
297:     </subitem>
298:     <subitem
299:             label="If you want to define your own datasource, take a look at the Configuration Guide for Red Hat JBoss Enterprise Application Platform."
300:             skip="true">
301:     <command
302:            required="false"
303:            serialization="org.eclipse.ui.browser.openBrowser(url=https://access.redhat.com/documentation/en/red-hat-jboss-enterprise-application-platform/)"/>
304:     </subitem>
305:   </item>
306:   <item
307:         skip="true"
308:         title="MemberController.java">
309:      <description>
310:        Of course, we need to allow JSF to interact with the services. The MemberController class is responsible for this:
311:     </description>
312:     <subitem
313:             label="The MemberController class uses the @Model stereotype, which adds @Named and @RequestScoped to the class. "
314:             skip="true">
315:     <command
316:       required="false"
317:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=34)"/>
318:   </subitem>
319:   <subitem
320:             label="The FacesContext is injected, so that messages can be sent to the user."
321:             skip="true">
322:     <command
323:       required="false"
324:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=37)"/>
325:   </subitem>
326:   <subitem
327:             label="The MemberRegistration bean is injected, to allow the controller to interact with the database."
328:             skip="true">
329:     <command
330:       required="false"
331:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=40)"/>
332:   </subitem>
333:   <subitem
334:             label="The Member class is exposed using a named producer field, which allows access from JSF. Note that the named producer field has dependent scope, so every time it is injected, the field will be read "
335:             skip="true">
336:     <command
337:       required="false"
338:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=43,toLine=45)"/>
339:   </subitem>
340:   <subitem
341:             label="The @PostConstruct annotation causes a new member object to be placed in the newMember field when the bean is instantiated."
342:             skip="true">
343:     <command
344:       required="false"
345:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=47)"/>
346:   </subitem>
347:   <subitem
348:             label="When the register method is called, the newMember object is passed to the persistence service."
349:             skip="true">
350:     <command
351:       required="false"
352:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=54)"/>
353:   </subitem>
354:   <subitem
355:             label="We also send a message to the user, to give them feedback on their actions."
356:             skip="true">
357:     <command
358:       required="false"
359:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=56)"/>
360:   </subitem>
361:   <subitem
362:             label="Finally, we replace the newMember with a new object, thus blanking out the data the user has added so far. This works as the producer field is dependent scoped."
363:             skip="true">
364:     <command
365:       required="false"
366:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/controller/MemberController.java,fromLine=57)"/>
367:   </subitem>
368:   </item>
369:   <item
370:         skip="true"
371:         title="JAX-RS">
372:      <description>
373:        Before we wrap up our tour of the kitchensink example application,
374:        let&apos;s take a look at how the JAX-RS endpoints are created. Firstly, {<b>JaxRSActivator</b>}},
375:        which extends <b>Application</b> and is annotated with <b>@ApplicationPath</b>,
376:        is the Jakarta EE <b>no XML</b> approach to activating JAX-RS.
377:     </description>
378:     <subitem
379:             label="JaxRsActivator.java"
380:             skip="true">
381:     <command
382:       required="false"
383:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/JaxRsActivator.java,fromLine=30, toLine=33)"/>
384:   </subitem>
385:   </item>
386:   <item
387:         skip="true"
388:         title="MemberResourceRESTService.java">
389:      <description>
390:        The real work goes in MemberResourceRESTService, which produces the endpoint:
391:     </description>
392:     <subitem
393:             label="The @Path annotation tells JAX-RS that this class provides a REST endpoint mapped to rest/members (concatenating the path from the activator with the path for this endpoint). "
394:             skip="true">
395:     <command
396:       required="false"
397:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=52)"/>
398:   </subitem>
399:   <subitem
400:             label="The bean is request scoped, as JAX-RS interactions typically don&apos;t hold state between requests."
401:             skip="true">
402:     <command
403:       required="false"
404:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=53)"/>
405:   </subitem>
406:   <subitem
407:             label="JAX-RS endpoints are CDI enabled, and can use CDI-style injection. "
408:             skip="true">
409:     <command
410:       required="false"
411:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=56)"/>
412:   </subitem>
413:   <subitem
414:             label="CDI allows us to inject a Bean Validation Validator instance, which is used to validate the POSTed member before it is persisted."
415:             skip="true">
416:     <command
417:       required="false"
418:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=59)"/>
419:   </subitem>
420:   <subitem
421:             label="MemberRepository is injected to allow us to query the member database "
422:             skip="true">
423:     <command
424:       required="false"
425:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=62)"/>
426:   </subitem>
427:   <subitem
428:             label="MemberRegistration is injected to allow us to alter the member database."
429:             skip="true">
430:     <command
431:       required="false"
432:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=65)"/>
433:   </subitem>
434:   <subitem
435:             label="The listAllMembers() method is called when the raw endpoint is accessed and offers up a list of endpoints. Notice that the object is automatically marshalled to JSON by RESTEasy (the JAX-RS implementation included in Red Hat JBoss Enterprise Application Platform)."
436:             skip="true">
437:     <command
438:       required="false"
439:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=68,toLine=72)"/>
440:   </subitem>
441:   <subitem
442:             label="The lookupMemberById() method is called when the endpoint is accessed with a member id parameter appended (for example rest/members/1). Again, the object is automatically marshalled to JSON by RESTEasy."
443:             skip="true">
444:     <command
445:       required="false"
446:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=74,toLine=83)"/>
447:   </subitem>
448:   <subitem
449:             label="createMember() is called when a POST is performed on the URL. Once again, the object is automatically unmarshalled from JSON."
450:             skip="true">
451:     <command
452:       required="false"
453:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=89,toLine=120)"/>
454:   </subitem>
455:   <subitem
456:             label="In order to ensure that the member is valid, we call the validateMember method, which validates the object, and adds any constraint violations to the response. These can then be handled on the client side, and displayed to the user."
457:             skip="true">
458:     <command
459:       required="false"
460:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=136,toLine=148)"/>
461:   </subitem>
462:   <subitem
463:             label="The object is then passed to the MemberRegistration service to be persisted."
464:             skip="true">
465:     <command
466:       required="false"
467:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=100)"/>
468:   </subitem>
469:   <subitem
470:             label="We then handle any remaining issues with validating the object, which are raised when the object is persisted."
471:             skip="true">
472:     <command
473:       required="false"
474:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/main/java/org/jboss/as/quickstarts/kitchensink/rest/MemberResourceRESTService.java,fromLine=104,toLine=117)"/>
475:   </subitem>
476:   </item>
477:   <item
478:         skip="true"
479:         title="Run and deploy the application">
480:      <description>
481:        Right-click the project and select <b>Run As</b> &gt; <b>Run On Server</b> or click on the &quot;Click to Perform&quot; link below.
482:     </description>
483:     <!-- the runOnServer command is not implemented yet
484:     <command
485:       required="false"
486:       serialization="org.jboss.tools.project.examples.cheatsheet.actions.runOnServer(project=${currentProject})"/>
487:   -->
488:   <action
489:     pluginId="org.jboss.tools.project.examples.cheatsheet"
490:     class="org.jboss.tools.project.examples.cheatsheet.actions.RunOnServer"
491:     param1="${currentProject}"/>
492:   </item>
493:   <item
494:         skip="true"
495:         title="Arquillian">
496:      <description>
497:        If you&apos;ve been following along with the Test Driven Development craze of the past few years,
498:        you&apos;re probably getting a bit nervous by now, wondering how on earth you are going to test your application.
499:        Lucky for you, the Arquillian project is here to help!
500:     <br/><br/>
501:     Arquillian provides all the boiler plate for running your test inside Red Hat JBoss Enterprise Application Platform,
502:     allowing you to concentrate on testing your application.
503:     In order to do that, it utilizes Shrinkwrap, a fluent API for defining packaging,
504:     to create an archive to deploy.
505:     We'll go through the testcase, and how you configure Arquillian in just a moment,
506:     but first let's run the test.
507:     </description>
508:   </item>
509:   <item
510:         skip="true"
511:         title="Start Arquillian tests">
512:     <description>
513:       Arquillian defines two modes, managed and remote.
514:       The managed mode will take care of starting and stopping the server for you,
515:       while the remote mode connects to an already running server.
516:       <br/><br/>
517:       The following action starts the test in the <b>remote</b> mode because you have started the server in the previous step.
518:     <br/>
519:         Right-click the project, select <b>Properties&gt;Maven</b> and
520:         enter <b>arq-remote</b> to the <b>Active Maven Profile</b> field.
521:         After that, right-click the project and select <b>Run As&gt;JUnit test</b>.
522:     </description>
523:     <!-- the launchJUnitTest command is not implemented yey
524:     <command
525:       required="false"
526:       serialization="org.jboss.tools.project.examples.cheatsheet.actions.launchJUnitTest(project=${currentProject}, activateProfile=arq-remote)"/>
527:   -->
528:   <action
529:       pluginId="org.jboss.tools.project.examples.cheatsheet"
530:       class="org.jboss.tools.project.examples.cheatsheet.actions.LaunchJUnitTest"
531:       param1="${currentProject}"
532:         param2="arq-remote"/>
533:   </item>
534:   <item
535:         skip="true"
536:         title="MemberRegistrationTest.java">
537:     <description>
538:       So far so good, the test is running. But what does the test look like?
539:     </description>
540:     <subitem
541:             label="@RunWith(Arquillian.class) tells JUnit to hand control over to Arquillian when executing tests."
542:             skip="true">
543:     <command
544:       required="false"
545:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=37)"/>
546:   </subitem>
547:   <subitem
548:             label="The @Deployment annotation identifies the createTestArchive static method to Arquillian as the one to use to determine which resources and classes to deploy "
549:             skip="true">
550:     <command
551:       required="false"
552:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=39)"/>
553:   </subitem>
554:   <subitem
555:             label="We add just the classes needed for the test, no more "
556:             skip="true">
557:     <command
558:       required="false"
559:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=42)"/>
560:   </subitem>
561:   <subitem
562:             label="We also add persistence.xml as our test is going to use the database "
563:             skip="true">
564:     <command
565:       required="false"
566:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=43)"/>
567:   </subitem>
568:   <subitem
569:             label="Of course, we must add beans.xml to enable CDI."
570:             skip="true">
571:     <command
572:       required="false"
573:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=44)"/>
574:   </subitem>
575:   <subitem
576:             label="Finally, we add a test datasource, so that test data doesn&apos;t overwrite production data."
577:             skip="true">
578:     <command
579:       required="false"
580:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=46)"/>
581:   </subitem>
582:   <subitem
583:             label="Arquillian allows us to inject beans into the test case."
584:             skip="true">
585:     <command
586:       required="false"
587:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=49,toLine=50)"/>
588:   </subitem>
589:   <subitem
590:             label="The test method works as you would expect - creates a new member, registers them, and then verifies that the member was created "
591:             skip="true">
592:     <command
593:       required="false"
594:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/java/org/jboss/as/quickstarts/kitchensink/test/MemberRegistrationTest.java,fromLine=55,toLine=64)"/>
595:   </subitem>
596:   </item>
597:   <item
598:         skip="true"
599:         title="arquillian.xml">
600:     <description>
601:       As you can see, Arquillian has lived up to the promise - the test case is focused on what to test
602:       (the @Deployment method) and how to test (the @Test method).
603:       It&apos;s also worth noting that this isn&apos;t a simplistic unit test - this is a fully fledged integration
604:        test that uses the database.
605:     <br/><br/>
606:     Now, let&apos;s look at how we configure Arquillian.
607:     First of all, let&apos;s take a look at <b>arquillian.xml</b> in <b>src/test/resources</b>.
608:     </description>
609:     <subitem
610:             label="Arquillian deploys the test war to JBoss EAP, and doesn't write it to disk. For debugging, it can be very useful to see exactly what is in your war, so Arquillian allows you to export the war when the tests runs "
611:             skip="true">
612:     <command
613:       required="false"
614:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/src/test/resources/arquillian.xml)"/>
615:   </subitem>
616:   </item>
617:     <item
618:         skip="true"
619:         title="pom.xml">
620:     <description>
621:       Now, we need to look at how we select between containers in the pom.xml:
622:     </description>
623:     <subitem
624:             label="The profile needs an id so we can activate from Eclipse or the command line "
625:             skip="true">
626:     <command
627:       required="false"
628:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/pom.xml,fromLine=238,toLine=314)"/>
629:   </subitem>
630:   <subitem
631:             label="Arquillian decides which container to use depending on your classpath. Here we define the remote JBoss EAP container. "
632:             skip="true">
633:     <command
634:       required="false"
635:       serialization="org.jboss.tools.project.examples.cheatsheet.openFileInEditor(path=/${currentProject}/pom.xml,fromLine=277,toLine=289)"/>
636:   </subitem>
637:   </item>
638:   <item
639:         skip="true"
640:         title="Arquillian project page">
641:      <description>
642:       And that&apos;s it! As you can see Arquillian delivers simple and true testing.
643:       You can concentrate on writing your test functionality, and run your tests in the same environment in which you will run your application.
644:       <br/><br/>
645:       Arquillian also offers other containers, allowing you to run your tests against Weld Embedded (super fast, but your enterprise services are mocked), GlassFish, and more.
646:       <br/><br/>
647:       More info on Arquillian you can find on the Arquillian project page.
648:     </description>
649:     <command
650:            required="false"
651:            serialization="org.eclipse.ui.browser.openBrowser(url=http://www.jboss.org/arquillian)"/>
652:   </item>
653:   <item
654:         skip="true"
655:         title="Creating your own application ">
656:      <description>
657:       What we didn&apos;t tell you about the <b>Kitchensink quickstart</b> is that it is generated from a Maven archetype.
658:       Using this archetype offers you the perfect opportunity to generate your own project.
659:       <br/><br/>
660:       In order to perform that, you should select <b>Help&gt;Red Hat Central</b> and click the <b>Jakarta EE Web Project</b> wizard.
661:       <br/>
662:       You will get a brand new project with the same functionality as <b>kitchensink</b>,
663:       but customized with your details.
664:     </description>
665:   </item>
666: </cheatsheet>
```

## File: legacy/pom.xml
```xml
  1: <?xml version="1.0" encoding="UTF-8"?>
  2: <!--
  3:     JBoss, Home of Professional Open Source
  4:     Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
  5:     contributors by the @authors tag. See the copyright.txt in the
  6:     distribution for a full listing of individual contributors.
  7:     Licensed under the Apache License, Version 2.0 (the "License");
  8:     you may not use this file except in compliance with the License.
  9:     You may obtain a copy of the License at
 10:     http://www.apache.org/licenses/LICENSE-2.0
 11:     Unless required by applicable law or agreed to in writing, software
 12:     distributed under the License is distributed on an "AS IS" BASIS,
 13:     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 14:     See the License for the specific language governing permissions and
 15:     limitations under the License.
 16: -->
 17: <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 18:          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
 19:     <modelVersion>4.0.0</modelVersion>
 20:     <parent>
 21:         <groupId>org.jboss.eap.quickstarts</groupId>
 22:         <artifactId>jboss-eap-quickstart-parent</artifactId>
 23:         <!--
 24:         Maintain separation between the artifact id and the version to help prevent
 25:         merge conflicts between commits changing the GA and those changing the V.
 26:         -->
 27:         <version>6.0.0.redhat-00001</version>
 28:         <relativePath/>
 29:     </parent>
 30:     <artifactId>kitchensink</artifactId>
 31:     <version>8.0.0.GA</version>
 32:     <packaging>war</packaging>
 33:     <name>Quickstart: kitchensink</name>
 34:     <description>A starter Jakarta EE web application project for use in JBoss EAP</description>
 35:     <licenses>
 36:         <license>
 37:             <name>Apache License, Version 2.0</name>
 38:             <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
 39:             <distribution>repo</distribution>
 40:         </license>
 41:     </licenses>
 42:     <properties>
 43:         <!-- The versions for BOMs, Dependencies and Plugins -->
 44:         <version.server.bom>8.0.0.GA-redhat-00009</version.server.bom>
 45:         <version.eap.maven.plugin>1.0.0.Final-redhat-00014</version.eap.maven.plugin>
 46:     </properties>
 47:     <repositories>
 48:         <!-- keep this repository the first -->
 49:         <repository>
 50:             <id>jboss-public-maven-repository</id>
 51:             <name>JBoss Public Maven Repository</name>
 52:             <url>https://repository.jboss.org/nexus/content/groups/public/</url>
 53:             <releases>
 54:                 <enabled>true</enabled>
 55:                 <updatePolicy>never</updatePolicy>
 56:             </releases>
 57:             <snapshots>
 58:                 <enabled>true</enabled>
 59:                 <updatePolicy>never</updatePolicy>
 60:             </snapshots>
 61:             <layout>default</layout>
 62:         </repository>
 63:         <repository>
 64:             <id>redhat-ga-maven-repository</id>
 65:             <name>Red Hat GA Maven Repository</name>
 66:             <url>https://maven.repository.redhat.com/ga/</url>
 67:             <releases>
 68:                 <enabled>true</enabled>
 69:                 <updatePolicy>never</updatePolicy>
 70:             </releases>
 71:             <snapshots>
 72:                 <enabled>true</enabled>
 73:                 <updatePolicy>never</updatePolicy>
 74:             </snapshots>
 75:             <layout>default</layout>
 76:         </repository>
 77:     </repositories>
 78:     <pluginRepositories>
 79:         <!-- keep this repository the first -->
 80:         <pluginRepository>
 81:             <id>jboss-public-maven-repository</id>
 82:             <name>JBoss Public Maven Repository</name>
 83:             <url>https://repository.jboss.org/nexus/content/groups/public/</url>
 84:             <releases>
 85:                 <enabled>true</enabled>
 86:             </releases>
 87:             <snapshots>
 88:                 <enabled>true</enabled>
 89:             </snapshots>
 90:         </pluginRepository>
 91:         <pluginRepository>
 92:             <id>redhat-ga-maven-repository</id>
 93:             <name>Red Hat GA Maven Repository</name>
 94:             <url>https://maven.repository.redhat.com/ga/</url>
 95:             <releases>
 96:                 <enabled>true</enabled>
 97:             </releases>
 98:             <snapshots>
 99:                 <enabled>true</enabled>
100:             </snapshots>
101:         </pluginRepository>
102:     </pluginRepositories>
103:     <dependencyManagement>
104:         <dependencies>
105:             <!-- importing the ee-with-tools BOM adds specs and other useful artifacts as managed dependencies -->
106:             <dependency>
107:                 <groupId>org.jboss.bom</groupId>
108:                 <artifactId>jboss-eap-ee-with-tools</artifactId>
109:                 <version>${version.server.bom}</version>
110:                 <type>pom</type>
111:                 <scope>import</scope>
112:             </dependency>
113:         </dependencies>
114:     </dependencyManagement>
115:     <dependencies>
116:         <!-- First declare the APIs we depend on and need for compilation. All
117:         of them are provided by JBoss EAP -->
118:         <!-- Import the CDI API, we use provided scope as the API is included in
119:         JBoss EAP -->
120:         <dependency>
121:             <groupId>jakarta.enterprise</groupId>
122:             <artifactId>jakarta.enterprise.cdi-api</artifactId>
123:             <scope>provided</scope>
124:         </dependency>
125:         <!-- Needed for running tests (you may also use TestNG) -->
126:         <dependency>
127:             <groupId>junit</groupId>
128:             <artifactId>junit</artifactId>
129:             <scope>test</scope>
130:         </dependency>
131:         <!-- Now we declare any tools needed -->
132:         <!-- Annotation processor to generate the JPA metamodel classes for
133:         typesafe criteria queries -->
134:         <dependency>
135:             <groupId>org.hibernate.orm</groupId>
136:             <artifactId>hibernate-jpamodelgen</artifactId>
137:             <scope>provided</scope>
138:         </dependency>
139:         <!-- Jakarta Activation needed for JPA model generation -->
140:         <dependency>
141:             <groupId>jakarta.activation</groupId>
142:             <artifactId>jakarta.activation-api</artifactId>
143:             <scope>provided</scope>
144:         </dependency>
145:         <!-- Bean Validation Implementation 
146:         Provides portable constraints such as @Email 
147:         Hibernate Validator is shipped in JBoss EAP -->
148:         <dependency>
149:             <groupId>org.hibernate.validator</groupId>
150:             <artifactId>hibernate-validator</artifactId>
151:             <scope>provided</scope>
152:         </dependency>
153:         <!-- hibernate-validator dependencies excluded on server dependency management yet required -->
154:         <dependency>
155:             <groupId>jakarta.validation</groupId>
156:             <artifactId>jakarta.validation-api</artifactId>
157:             <scope>provided</scope>
158:         </dependency>
159:         <!-- Annotation processor that raising compilation errors whenever constraint
160:         annotations are incorrectly used. -->
161:         <dependency>
162:             <groupId>org.hibernate.validator</groupId>
163:             <artifactId>hibernate-validator-annotation-processor</artifactId>
164:             <scope>provided</scope>
165:         </dependency>
166:         <!-- Import the JPA API, we use provided scope as the API is included in
167:         JBoss EAP -->
168:         <dependency>
169:             <groupId>jakarta.persistence</groupId>
170:             <artifactId>jakarta.persistence-api</artifactId>
171:             <scope>provided</scope>
172:         </dependency>
173:         <!-- Optional, but highly recommended -->
174:         <!-- Arquillian allows you to test enterprise code such as EJBs and Transactional(JTA)
175:         JPA from JUnit/TestNG -->
176:         <dependency>
177:             <groupId>org.jboss.arquillian.junit</groupId>
178:             <artifactId>arquillian-junit-container</artifactId>
179:             <scope>test</scope>
180:         </dependency>
181:         <dependency>
182:             <groupId>org.jboss.arquillian.protocol</groupId>
183:             <artifactId>arquillian-protocol-servlet-jakarta</artifactId>
184:             <scope>test</scope>
185:         </dependency>
186:         <!-- Import the Common Annotations API (JSR-250), we use provided scope
187:         as the API is included in JBoss EAP -->
188:         <dependency>
189:             <groupId>jakarta.annotation</groupId>
190:             <artifactId>jakarta.annotation-api</artifactId>
191:             <scope>provided</scope>
192:         </dependency>
193:         <!-- Import the EJB API, we use provided scope as the API is included in
194:         JBoss EAP -->
195:         <dependency>
196:             <groupId>jakarta.ejb</groupId>
197:             <artifactId>jakarta.ejb-api</artifactId>
198:             <scope>provided</scope>
199:         </dependency>
200:         <!-- Import the JSF API, we use provided scope as the API is included in
201:         JBoss EAP -->
202:         <dependency>
203:             <groupId>jakarta.faces</groupId>
204:             <artifactId>jakarta.faces-api</artifactId>
205:             <scope>provided</scope>
206:         </dependency>
207:         <!-- Import the JAX-RS API, we use provided scope as the API is included
208:         in JBoss EAP -->
209:         <dependency>
210:             <groupId>jakarta.ws.rs</groupId>
211:             <artifactId>jakarta.ws.rs-api</artifactId>
212:             <scope>provided</scope>
213:         </dependency>
214:         <dependency>
215:             <groupId>jakarta.xml.bind</groupId>
216:             <artifactId>jakarta.xml.bind-api</artifactId>
217:             <scope>provided</scope>
218:         </dependency>
219:         <dependency>
220:             <groupId>jakarta.json</groupId>
221:             <artifactId>jakarta.json-api</artifactId>
222:             <scope>test</scope>
223:         </dependency>
224:         <dependency>
225:             <groupId>org.eclipse.parsson</groupId>
226:             <artifactId>parsson</artifactId>
227:             <scope>test</scope>
228:         </dependency>
229:     </dependencies>
230:     <profiles>
231:         <profile>
232:             <!-- An optional Arquillian testing profile that executes tests in a remote JBoss EAP instance.
233:             Run with: mvn clean verify -Parq-remote -->
234:             <id>arq-remote</id>
235:             <dependencies>
236:                 <dependency>
237:                     <groupId>org.wildfly.arquillian</groupId>
238:                     <artifactId>wildfly-arquillian-container-remote</artifactId>
239:                     <scope>test</scope>
240:                 </dependency>
241:             </dependencies>
242:             <build>
243:                 <plugins>
244:                     <plugin>
245:                         <groupId>org.apache.maven.plugins</groupId>
246:                         <artifactId>maven-failsafe-plugin</artifactId>
247:                         <version>${version.failsafe.plugin}</version>
248:                         <configuration>
249:                             <includes>
250:                                 <include>**/RemoteMemberRegistrationIT</include>
251:                             </includes>
252:                             <excludes>
253:                                 <exclude>**/MemberRegistrationIT</exclude>
254:                             </excludes>
255:                         </configuration>
256:                         <executions>
257:                             <execution>
258:                                 <goals>
259:                                     <goal>integration-test</goal>
260:                                     <goal>verify</goal>
261:                                 </goals>
262:                             </execution>
263:                         </executions>
264:                     </plugin>
265:                 </plugins>
266:             </build>
267:         </profile>
268:         <profile>
269:             <id>openshift</id>
270:             <build>
271:                 <plugins>
272:                     <plugin>
273:                         <groupId>org.jboss.eap.plugins</groupId>
274:                         <artifactId>eap-maven-plugin</artifactId>
275:                         <version>${version.eap.maven.plugin}</version>
276:                         <configuration>
277:                             <channels>
278:                                 <channel>
279:                                     <manifest>
280:                                         <groupId>org.jboss.eap.channels</groupId>
281:                                         <artifactId>eap-8.0</artifactId>
282:                                     </manifest>
283:                                 </channel>
284:                             </channels>
285:                             <feature-packs>
286:                                 <feature-pack>
287:                                     <location>org.jboss.eap:wildfly-ee-galleon-pack</location>
288:                                 </feature-pack>
289:                                 <feature-pack>
290:                                     <location>org.jboss.eap.cloud:eap-cloud-galleon-pack</location>
291:                                 </feature-pack>
292:                             </feature-packs>
293:                             <layers>
294:                                 <layer>cloud-server</layer>
295:                                 <layer>h2-driver</layer>
296:                                 <layer>ejb</layer>
297:                                 <layer>jsf</layer>
298:                             </layers>
299:                             <filename>ROOT.war</filename>
300:                         </configuration>
301:                         <executions>
302:                             <execution>
303:                                 <goals>
304:                                     <goal>package</goal>
305:                                 </goals>
306:                             </execution>
307:                         </executions>
308:                     </plugin>
309:                 </plugins>
310:             </build>
311:         </profile>
312:     </profiles>
313: </project>
```

## File: legacy/README-source.adoc
```
 1: include::../shared-doc/attributes.adoc[]
 2: 
 3: = kitchensink: Assortment of technologies including Arquillian
 4: :author: Pete Muir
 5: :level: Intermediate
 6: :technologies: CDI, JSF, JPA, EJB, JAX-RS, BV
 7: :openshift: true
 8: 
 9: [abstract]
10: The `kitchensink` quickstart demonstrates a {javaVersion} web-enabled database application using JSF, CDI, EJB, JPA, and Bean Validation.
11: 
12: :standalone-server-type: default
13: :archiveType: war
14: :uses-h2:
15: :uses-ds-xml:
16: 
17: == What is it?
18: 
19: The `kitchensink` quickstart is a deployable Maven 3 project designed to help you get your foot in the door developing with {javaVersion} on {productNameFull}.
20: 
21: It demonstrates how to create a compliant {javaVersion} application using JSF, CDI, JAX-RS, EJB, JPA, and Bean Validation. It also includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.
22: 
23: // Considerations for Use in a Production Environment
24: include::../shared-doc/development-shortcuts.adoc[leveloffset=+1]
25: // System Requirements
26: include::../shared-doc/system-requirements.adoc[leveloffset=+1]
27: //  Use of {jbossHomeName}
28: include::../shared-doc/use-of-jboss-home-name.adoc[leveloffset=+1]
29: 
30: // build and run with standard server distribution
31: [[build_and_run_the_quickstart_with_server_dist]]
32: == Building and running the quickstart application with a {productName} server distribution
33: // Start the {productName} Standalone Server
34: include::../shared-doc/start-the-standalone-server.adoc[leveloffset=+2]
35: // Build and Deploy the Quickstart
36: include::../shared-doc/build-and-deploy-the-quickstart.adoc[leveloffset=+2]
37: 
38: === Access the Application
39: 
40: The application will be running at the following URL: http://localhost:8080/{artifactId}/.
41: 
42: === Server Log: Expected Warnings and Errors
43: 
44: You will see the following warnings in the server log. You can ignore these warnings.
45: 
46: [source,options="nowrap"]
47: ----
48: WFLYJCA0091: -ds.xml file deployments are deprecated. Support may be removed in a future version.
49: ----
50: 
51: // Testing with Arquillian
52: include::../shared-doc/run-arquillian-integration-tests-with-server-distribution.adoc[leveloffset=+2]
53: // Undeploy the Quickstart
54: include::../shared-doc/undeploy-the-quickstart.adoc[leveloffset=+2]
55: 
56: // Build and run sections for other environments/builds
57: ifndef::ProductRelease,EAPXPRelease[]
58: include::../shared-doc/build-and-run-the-quickstart-with-provisioned-server.adoc[leveloffset=+1]
59: endif::[]
60: include::../shared-doc/build-and-run-the-quickstart-with-openshift.adoc[leveloffset=+1]
```

## File: legacy/README.adoc
```
  1: ifdef::env-github[]
  2: :artifactId: kitchensink
  3: endif::[]
  4: 
  5: //***********************************************************************************
  6: // Enable the following flag to build README.html files for JBoss EAP product builds.
  7: // Comment it out for WildFly builds.
  8: //***********************************************************************************
  9: :ProductRelease:
 10: 
 11: //***********************************************************************************
 12: // Enable the following flag to build README.html files for EAP XP product builds.
 13: // Comment it out for WildFly or JBoss EAP product builds.
 14: //***********************************************************************************
 15: //:EAPXPRelease:
 16: 
 17: // This is a universal name for all releases
 18: :ProductShortName: JBoss EAP
 19: // Product names and links are dependent on whether it is a product release (CD or JBoss)
 20: // or the WildFly project.
 21: // The "DocInfo*" attributes are used to build the book links to the product documentation
 22: 
 23: ifdef::ProductRelease[]
 24: // JBoss EAP release
 25: :productName: JBoss EAP
 26: :productNameFull: Red Hat JBoss Enterprise Application Platform
 27: :productVersion: 8.0
 28: :DocInfoProductNumber: {productVersion}
 29: :WildFlyQuickStartRepoTag: 8.0.x
 30: :productImageVersion: 8.0.0
 31: :helmChartName: jboss-eap/eap8
 32: endif::[]
 33: 
 34: ifdef::EAPXPRelease[]
 35: // JBoss EAP XP release
 36: :productName: JBoss EAP XP
 37: :productNameFull: Red Hat JBoss Enterprise Application Platform expansion pack
 38: :productVersion: 3.0
 39: :DocInfoProductNumber: 7.4
 40: :WildFlyQuickStartRepoTag: XP_3.0.0.GA
 41: :productImageVersion: 3.0
 42: :helmChartName: jboss-eap/eap-xp3
 43: endif::[]
 44: 
 45: ifdef::ProductRelease,EAPXPRelease[]
 46: :githubRepoUrl: https://github.com/jboss-developer/jboss-eap-quickstarts/
 47: :githubRepoCodeUrl: https://github.com/jboss-developer/jboss-eap-quickstarts.git
 48: :jbossHomeName: EAP_HOME
 49: :DocInfoProductName: Red Hat JBoss Enterprise Application Platform
 50: :DocInfoProductNameURL: red_hat_jboss_enterprise_application_platform
 51: :DocInfoPreviousProductName: jboss-enterprise-application-platform
 52: :quickstartDownloadName: {productNameFull} {productVersion} Quickstarts
 53: :quickstartDownloadUrl: https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=appplatform&downloadType=distributions
 54: :helmRepoName: jboss-eap
 55: :helmRepoUrl: https://jbossas.github.io/eap-charts/
 56: // END ifdef::ProductRelease,EAPXPRelease[]
 57: endif::[]
 58: 
 59: ifndef::ProductRelease,EAPXPRelease[]
 60: // WildFly project
 61: :productName: WildFly
 62: :productNameFull: WildFly Application Server
 63: :ProductShortName: {productName}
 64: :jbossHomeName: WILDFLY_HOME
 65: :productVersion: 28
 66: :productImageVersion: 28.0
 67: :githubRepoUrl: https://github.com/wildfly/quickstart/
 68: :githubRepoCodeUrl: https://github.com/wildfly/quickstart.git
 69: :WildFlyQuickStartRepoTag: 28.0.1.Final
 70: :DocInfoProductName: Red Hat JBoss Enterprise Application Platform
 71: :DocInfoProductNameURL: red_hat_jboss_enterprise_application_platform
 72: // Do not update the following until after the 7.4 docs are published!
 73: :DocInfoProductNumber: 7.4
 74: :DocInfoPreviousProductName: jboss-enterprise-application-platform
 75: :helmRepoName: wildfly
 76: :helmRepoUrl: http://docs.wildfly.org/wildfly-charts/
 77: :helmChartName: wildfly/wildfly
 78: // END ifndef::ProductRelease,EAPCDRelease,EAPXPRelease[]
 79: endif::[]
 80: 
 81: :source: {githubRepoUrl}
 82: 
 83: // Values for Openshift S2i sections attributes
 84: :CDProductName:  {productNameFull} for OpenShift
 85: :CDProductShortName: {ProductShortName} for OpenShift
 86: :CDProductTitle: {CDProductName}
 87: :CDProductNameSentence: Openshift release for {ProductShortName}
 88: :CDProductAcronym: {CDProductShortName}
 89: :CDProductVersion: {productVersion}
 90: :EapForOpenshiftBookName: {productNameFull} for OpenShift
 91: :EapForOpenshiftOnlineBookName: {EapForOpenshiftBookName} Online
 92: :xpaasproduct: {productNameFull} for OpenShift
 93: :xpaasproductOpenShiftOnline: {xpaasproduct} Online
 94: :xpaasproduct-shortname: {CDProductShortName}
 95: :xpaasproductOpenShiftOnline-shortname: {xpaasproduct-shortname} Online
 96: :ContainerRegistryName: Red Hat Container Registry
 97: :EapForOpenshiftBookName: Getting Started with {ProductShortName} for OpenShift Container Platform
 98: :EapForOpenshiftOnlineBookName: Getting Started with {ProductShortName} for OpenShift Online
 99: :OpenShiftOnlinePlatformName: Red Hat OpenShift Container Platform
100: :OpenShiftOnlineName: Red Hat OpenShift Online
101: :ImagePrefixVersion: eap80
102: :ImageandTemplateImportBaseURL: https://raw.githubusercontent.com/jboss-container-images/jboss-eap-openshift-templates
103: :ImageandTemplateImportURL: {ImageandTemplateImportBaseURL}/{ImagePrefixVersion}/
104: :BuildImageStream: jboss-{ImagePrefixVersion}-openjdk11-openshift
105: :RuntimeImageStream: jboss-{ImagePrefixVersion}-openjdk11-runtime-openshift
106: 
107: // OpenShift repository and reference for quickstarts
108: :EAPQuickStartRepo: https://github.com/jboss-developer/jboss-eap-quickstarts
109: :EAPQuickStartRepoRef: 8.0.x
110: :EAPQuickStartRepoTag: EAP_8.0.0.GA
111: // Links to the OpenShift documentation
112: :LinkOpenShiftGuide: https://access.redhat.com/documentation/en-us/{DocInfoProductNameURL}/{DocInfoProductNumber}/html-single/getting_started_with_jboss_eap_for_openshift_container_platform/
113: :LinkOpenShiftOnlineGuide: https://access.redhat.com/documentation/en-us/{DocInfoProductNameURL}/{DocInfoProductNumber}/html-single/getting_started_with_jboss_eap_for_openshift_online/
114: 
115: ifdef::EAPXPRelease[]
116: // Attributes for XP releases
117: :EapForOpenshiftBookName: {productNameFull} for OpenShift
118: :EapForOpenshiftOnlineBookName: {productNameFull} for OpenShift Online
119: :xpaasproduct: {productNameFull} for OpenShift
120: :xpaasproductOpenShiftOnline: {productNameFull} for OpenShift Online
121: :xpaasproduct-shortname: {ProductShortName} for OpenShift
122: :xpaasproductOpenShiftOnline-shortname: {ProductShortName} for OpenShift Online
123: :ContainerRegistryName: Red Hat Container Registry
124: :EapForOpenshiftBookName: {productNameFull} for OpenShift
125: :EapForOpenshiftOnlineBookName: {productNameFull} for OpenShift Online
126: :ImagePrefixVersion: eap-xp3
127: :ImageandTemplateImportURL: {ImageandTemplateImportBaseURL}/{ImagePrefixVersion}/
128: :BuildImageStream: jboss-{ImagePrefixVersion}-openjdk11-openshift
129: :RuntimeImageStream: jboss-{ImagePrefixVersion}-openjdk11-runtime-openshift
130: // OpenShift repository and reference for quickstarts
131: :EAPQuickStartRepoRef: xp-3.0.x
132: // Links to the OpenShift documentation
133: :LinkOpenShiftGuide: https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/{DocInfoProductNumber}/html/using_eclipse_microprofile_in_jboss_eap/using-the-openshift-image-for-jboss-eap-xp_default
134: :LinkOpenShiftOnlineGuide: https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/{DocInfoProductNumber}/html/using_eclipse_microprofile_in_jboss_eap/using-the-openshift-image-for-jboss-eap-xp_default
135: endif::[]
136: 
137: ifndef::ProductRelease,EAPCDRelease,EAPXPRelease[]
138: :ImageandTemplateImportURL: https://raw.githubusercontent.com/wildfly/wildfly-s2i/v{productVersion}.0/
139: endif::[]
140: 
141: //*************************
142: // Other values
143: //*************************
144: :buildRequirements: Java 11.0 (Java SDK 11) or later and Maven 3.6.0 or later
145: :jbdsEapServerName: Red Hat JBoss Enterprise Application Platform 7.3
146: :javaVersion: Jakarta EE 10
147: ifdef::EAPXPRelease[]
148: :javaVersion: Eclipse MicroProfile
149: endif::[]
150: :githubRepoBranch: master
151: :guidesBaseUrl: https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/
152: :useEclipseUrl: {guidesBaseUrl}USE_JBDS.adoc#use_red_hat_jboss_developer_studio_or_eclipse_to_run_the_quickstarts
153: :useEclipseDeployJavaClientDocUrl: {guidesBaseUrl}USE_JBDS.adoc#deploy_and_undeploy_a_quickstart_containing_server_and_java_client_projects
154: :useEclipseDeployEARDocUrl: {guidesBaseUrl}USE_JBDS.adoc#deploy_and_undeploy_a_quickstart_ear_project
155: :useProductHomeDocUrl: {guidesBaseUrl}USE_OF_{jbossHomeName}.adoc#use_of_product_home_and_jboss_home_variables
156: :configureMavenDocUrl: {guidesBaseUrl}CONFIGURE_MAVEN_JBOSS_EAP.adoc#configure_maven_to_build_and_deploy_the_quickstarts
157: :arquillianTestsDocUrl: {guidesBaseUrl}RUN_ARQUILLIAN_TESTS.adoc#run_the_arquillian_tests
158: :addUserDocUrl: {guidesBaseUrl}CREATE_USERS.adoc#create_users_required_by_the_quickstarts
159: :addApplicationUserDocUrl: {guidesBaseUrl}CREATE_USERS.adoc#add_an_application_user
160: :addManagementUserDocUrl: {guidesBaseUrl}CREATE_USERS.adoc#add_an_management_user
161: :startServerDocUrl: {guidesBaseUrl}START_JBOSS_EAP.adoc#start_the_jboss_eap_server
162: :configurePostgresDocUrl: {guidesBaseUrl}CONFIGURE_POSTGRESQL_JBOSS_EAP.adoc#configure_the_postgresql_database_for_use_with_the_quickstarts
163: :configurePostgresDownloadDocUrl: {guidesBaseUrl}CONFIGURE_POSTGRESQL_JBOSS_EAP.adoc#download_and_install_postgresql
164: :configurePostgresCreateUserDocUrl: {guidesBaseUrl}CONFIGURE_POSTGRESQL_JBOSS_EAP.adoc#create_a_database_user
165: :configurePostgresAddModuleDocUrl: {guidesBaseUrl}CONFIGURE_POSTGRESQL_JBOSS_EAP.adoc#add_the_postgres_module_to_the_jboss_eap_server
166: :configurePostgresDriverDocUrl: {guidesBaseUrl}CONFIGURE_POSTGRESQL_JBOSS_EAP.adoc#configure_the_postgresql_driver_in_the_jboss_eap_server
167: :configureBytemanDownloadDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#download_and_configure_byteman
168: :configureBytemanDisableDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#disable_the_byteman_script
169: :configureBytemanClearDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#clear_the_transaction_object_store
170: :configureBytemanQuickstartDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#configure_byteman_for_use_with_the_quickstarts
171: :configureBytemanHaltDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#use_byteman_to_halt_the_application[
172: :configureBytemanQuickstartsDocUrl: {guidesBaseUrl}CONFIGURE_BYTEMAN.adoc#configure_byteman_for_use_with_the_quickstarts
173: 
174: :EESubsystemNamespace: urn:jboss:domain:ee:4.0
175: :IiopOpenJdkSubsystemNamespace: urn:jboss:domain:iiop-openjdk:2.0
176: :MailSubsystemNamespace: urn:jboss:domain:mail:3.0
177: :SingletonSubsystemNamespace: urn:jboss:domain:singleton:1.0
178: :TransactionsSubsystemNamespace: urn:jboss:domain:transactions:4.0
179: 
180: // LinkProductDocHome: https://access.redhat.com/documentation/en/red-hat-jboss-enterprise-application-platform/
181: :LinkProductDocHome: https://access.redhat.com/documentation/en/jboss-enterprise-application-platform-continuous-delivery
182: :LinkConfigGuide: https://access.redhat.com/documentation/en-us/{DocInfoProductNameURL}/{DocInfoProductNumber}/html-single/configuration_guide/
183: :LinkDevelopmentGuide: https://access.redhat.com/documentation/en-us/{DocInfoProductNameURL}/{DocInfoProductNumber}/html-single/development_guide/
184: :LinkGettingStartedGuide: https://access.redhat.com/documentation/en-us/{DocInfoProductNameURL}/{DocInfoProductNumber}/html-single/getting_started_guide/
185: :LinkOpenShiftWelcome: https://docs.openshift.com/online/welcome/index.html
186: :LinkOpenShiftSignup: https://docs.openshift.com/online/getting_started/choose_a_plan.html
187: :OpenShiftTemplateName: JBoss EAP CD (no https)
188: 
189: :ConfigBookName: Configuration Guide
190: :DevelopmentBookName: Development Guide
191: :GettingStartedBookName: Getting Started Guide
192: 
193: :JBDSProductName: Red Hat CodeReady Studio
194: :JBDSVersion: 12.15
195: :LinkJBDSInstall:  https://access.redhat.com/documentation/en-us/red_hat_codeready_studio/{JBDSVersion}/html-single/installation_guide/
196: :JBDSInstallBookName: Installation Guide
197: :LinkJBDSGettingStarted: https://access.redhat.com/documentation/en-us/red_hat_codeready_studio/{JBDSVersion}/html-single/getting_started_with_codeready_studio_tools/
198: :JBDSGettingStartedBookName: Getting Started with CodeReady Studio Tools
199: 
200: = kitchensink: Assortment of technologies including Arquillian
201: :author: Pete Muir
202: :level: Intermediate
203: :technologies: CDI, JSF, JPA, EJB, JAX-RS, BV
204: :openshift: true
205: 
206: [abstract]
207: The `kitchensink` quickstart demonstrates a {javaVersion} web-enabled database application using JSF, CDI, EJB, JPA, and Bean Validation.
208: 
209: :standalone-server-type: default
210: :archiveType: war
211: :uses-h2:
212: :uses-ds-xml:
213: 
214: == What is it?
215: 
216: The `kitchensink` quickstart is a deployable Maven 3 project designed to help you get your foot in the door developing with {javaVersion} on {productNameFull}.
217: 
218: It demonstrates how to create a compliant {javaVersion} application using JSF, CDI, JAX-RS, EJB, JPA, and Bean Validation. It also includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.
219: 
220: // Considerations for Use in a Production Environment
221: :leveloffset: +1
222: 
223: [[considerations_for_use_in_a_production_environment]]
224: = Considerations for Use in a Production Environment
225: //******************************************************************************
226: // Include this template if your quickstart:
227: // * Uses the h2 database: Be sure to define the `uses-h2` attribute.
228: // * Uses an `*-ds.xml file`: Be sure to define the `uses-ds-xml` attribute.
229: // * Has performance or scalability concerns: Be sure to define the `uses-ds-xml` attribute.
230: //******************************************************************************
231: 
232: ifdef::uses-h2[]
233: 
234: H2 Database:: This quickstart uses the H2 database included with {productNameFull} {productVersion}. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment.
235: 
236: endif::uses-h2[]
237: 
238: ifdef::uses-ds-xml[]
239: 
240: Datasource Configuration File:: This quickstart uses a `*-ds.xml` datasource configuration file for convenience and ease of database configuration. These files are deprecated in {productName} and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the {LinkConfigGuide}[__{ConfigBookName}__].
241: 
242: endif::uses-ds-xml[]
243: 
244: ifdef::performance-scalability[]
245: 
246: Performance and Scalability:: A Jakarta EE container is designed with robustness in mind, so you should carefully analyze the scalabiltiy, concurrency, and performance needs of your application before taking advantage of these techniques in your own applications.
247: 
248: endif::performance-scalability[]
249: 
250: :leveloffset!:
251: // System Requirements
252: :leveloffset: +1
253: 
254: [[system_requirements]]
255: = System Requirements
256: //******************************************************************************
257: // Include this template to describe the standard system requirements for
258: // running the quickstarts.
259: //
260: // The Forge quickstarts define a `forge-from-scratch` attribute because they
261: // run entirely in CodeReady Studio and have different requirements .
262: //******************************************************************************
263: 
264: The application this project produces is designed to be run on {productNameFull} {productVersion} or later.
265: 
266: All you need to build this project is {buildRequirements}. See link:{configureMavenDocUrl}[Configure Maven to Build and Deploy the Quickstarts] to make sure you are configured correctly for testing the quickstarts.
267: 
268: :leveloffset!:
269: //  Use of {jbossHomeName}
270: :leveloffset: +1
271: 
272: ifdef::requires-multiple-servers[]
273: [[use_of_jboss_home_name]]
274: = Use of the {jbossHomeName}_1, {jbossHomeName}_2, and QUICKSTART_HOME Variables
275: 
276: This quickstart requires that you clone your `__{jbossHomeName}__` installation directory and run two servers. The installation path is described in detail here: link:{useProductHomeDocUrl}[Use of __{jbossHomeName}__ and __JBOSS_HOME__ Variables].
277: 
278: In the following instructions, replace `__{jbossHomeName}_1__` with the path to your first {productName} server and replace `__{jbossHomeName}_2__` with the path to your second cloned {productName} server.
279: 
280: When you see the replaceable variable __QUICKSTART_HOME__, replace it with the path to the root directory of all of the quickstarts.
281: endif::[]
282: 
283: ifdef::optional-domain-or-multiple-servers[]
284: [[use_of_jboss_home_name]]
285: = Use of the {jbossHomeName}_1, {jbossHomeName}_2, and QUICKSTART_HOME Variables
286: 
287: When deploying this quickstart to a managed domain, replace `__{jbossHomeName}__` with the actual path to your {productName} installation. The installation path is described in detail here: link:{useProductHomeDocUrl}[Use of __{jbossHomeName}__ and __JBOSS_HOME__ Variables].
288: 
289: When deploying this quickstart to multiple standalone servers, this quickstart requires that you clone your `__{jbossHomeName}__` installation directory and run two servers. In the following instructions, replace `__{jbossHomeName}_1__` with the path to your first {productName} server and replace `__{jbossHomeName}_2__` with the path to your second cloned {productName} server.
290: 
291: When you see the replaceable variable __QUICKSTART_HOME__, replace it with the path to the root directory of all of the quickstarts.
292: endif::[]
293: 
294: ifndef::requires-multiple-servers,optional-domain-or-multiple-servers[]
295: [[use_of_jboss_home_name]]
296: = Use of the {jbossHomeName} and QUICKSTART_HOME Variables
297: 
298: In the following instructions, replace `__{jbossHomeName}__` with the actual path to your {productName} installation. The installation path is described in detail here: link:{useProductHomeDocUrl}[Use of __{jbossHomeName}__ and __JBOSS_HOME__ Variables].
299: 
300: When you see the replaceable variable __QUICKSTART_HOME__, replace it with the path to the root directory of all of the quickstarts.
301: endif::[]
302: 
303: :leveloffset!:
304: 
305: // build and run with standard server distribution
306: [[build_and_run_the_quickstart_with_server_dist]]
307: == Building and running the quickstart application with a {productName} server distribution
308: // Start the {productName} Standalone Server
309: :leveloffset: +2
310: 
311: [[start_the_eap_standalone_server]]
312: = Start the {productName} Standalone Server
313: //******************************************************************************
314: // Include this template if your quickstart requires a normal start of a single
315: // standalone server.
316: //
317: // You must define the `standalone-server-type`. Supported values are:
318: //    default
319: //    full
320: //    full-ha
321: //    ha
322: //    custom
323: //
324: // * For mobile applications, you can define the `mobileApp` variable in the
325: //   `README.adoc` file to add `-b 0.0.0.0` to the command line. This allows
326: //    external clients, such as phones, tablets, and desktops, to connect
327: //    to the application through through your local network
328: //    ::mobileApp: {artifactId}-service
329: //
330: //******************************************************************************
331: 
332: //******************************************************************************
333: // This template sets attributes for the different standalone server profiles.
334: //
335: // You must define the `standalone-server-type`. Supported values are:
336: //    default
337: //    full
338: //    full-ha
339: //    ha
340: //    microprofile
341: //    custom
342: //******************************************************************************
343: 
344: // Standalone server with the default profile.
345: ifeval::["{standalone-server-type}"=="default"]
346: :serverProfile: default profile
347: :configFileName: standalone/configuration/standalone.xml
348: :serverArguments:
349: endif::[]
350: 
351: // Standalone server with the full profile.
352: ifeval::["{standalone-server-type}"=="full"]
353: :serverProfile: full profile
354: :configFileName: standalone/configuration/standalone-full.xml
355: :serverArguments:  -c standalone-full.xml
356: endif::[]
357: 
358: // Standalone server with the full HA profile.
359: ifeval::["{standalone-server-type}"=="full-ha"]
360: :serverProfile: full HA profile
361: :configFileName: standalone/configuration/standalone-full-ha.xml
362: :serverArguments:  -c standalone-full-ha.xml
363: endif::[]
364: 
365: // Start the standalone server with the HA profile.
366: ifeval::["{standalone-server-type}"=="ha"]
367: :serverProfile: HA profile
368: :configFileName: standalone/configuration/standalone-ha.xml
369: :serverArguments:  -c standalone-ha.xml
370: endif::[]
371: 
372: // Start the standalone server with the Eclipse MicroProfile profile.
373: ifeval::["{standalone-server-type}"=="microprofile"]
374: :serverProfile: MicroProfile profile
375: :configFileName: standalone/configuration/standalone-microprofile.xml
376: :serverArguments:  -c standalone-microprofile.xml
377: endif::[]
378: 
379: // Standalone server with the custom profile.
380: // NOTE: This profile requires that you define the `serverArguments` variable
381: // within the quickstart README.adoc file. For example:
382: //  :serverArguments: --server-config=../../docs/examples/configs/standalone-xts.xml
383: ifeval::["{standalone-server-type}"=="custom"]
384: :serverProfile: custom profile
385: endif::[]
386: 
387: // If there is no match, use the default profile.
388: ifndef::serverProfile[]
389: :standalone-server-type:  default
390: :serverProfile: default profile
391: :configFileName: standalone/configuration/standalone.xml
392: :serverArguments:
393: endif::serverProfile[]
394: 
395: . Open a terminal and navigate to the root of the {productName} directory.
396: . Start the {productName} server with the {serverProfile} by typing the following command.
397: +
398: ifdef::uses-jaeger[]
399: [source,subs="+quotes,attributes+",options="nowrap"]
400: ----
401: $ __JAEGER_REPORTER_LOG_SPANS=true JAEGER_SAMPLER_TYPE=const JAEGER_SAMPLER_PARAM=1__ __{jbossHomeName}__/bin/standalone.sh {serverArguments}
402: ----
403: endif::[]
404: ifndef::uses-jaeger[]
405: [source,subs="+quotes,attributes+",options="nowrap"]
406: ----
407: $ __{jbossHomeName}__/bin/standalone.sh {serverArguments}
408: ----
409: endif::[]
410: +
411: NOTE: For Windows, use the `__{jbossHomeName}__\bin\standalone.bat` script.
412: 
413: ifdef::mobileApp[]
414: +
415: Adding `-b 0.0.0.0` to the above command allows external clients, such as phones, tablets, and desktops, to connect through your local network. For example:
416: +
417: [source,subs="+quotes,attributes+",options="nowrap"]
418: ----
419: $ __{jbossHomeName}__/bin/standalone.sh {serverArguments} -b 0.0.0.0
420: ----
421: endif::[]
422: 
423: :leveloffset!:
424: // Build and Deploy the Quickstart
425: :leveloffset: +2
426: 
427: [[build_and_deploy_the_quickstart]]
428: = Build and Deploy the Quickstart
429: //******************************************************************************
430: // Include this template if your quickstart does a normal deployment of a archive.
431: //
432: // * Define the `archiveType` variable in the quickstart README file.
433: //   Supported values:
434: //    :archiveType: ear
435: //    :archiveType: war
436: //    :archiveType: jar
437: //
438: // * To override the archive name, which defaults to the {artifactId),
439: //   define the `archiveName` variable, for example:
440: //    :archiveName: {artifactId}-service
441: //
442: // * To override the archive output directory,
443: //   define the `archiveDir` variable, for example:
444: //    :archiveDir: ear/target
445: //
446: // * To override the Maven command, define the `mavenCommand` variable,
447: //   for example:
448: //    :mavenCommand: clean install wildfly:deploy
449: //******************************************************************************
450: 
451: // The archive name defaults to the artifactId if not overridden
452: ifndef::archiveName[]
453: :archiveName: {artifactId}
454: endif::archiveName[]
455: 
456: // The archive type defaults to war if not overridden
457: ifndef::archiveType[]
458: :archiveType: war
459: endif::archiveType[]
460: 
461: // Define the archive file name as the concatenation of "archiveName" + "." + "archiveType+
462: :archiveFileName: {archiveName}.{archiveType}
463: 
464: // If they have not defined the target archive directory, make it the default for the archive type.
465: ifndef::archiveDir[]
466: 
467: ifeval::["{archiveType}"=="ear"]
468: :archiveDir: {artifactId}/ear/target
469: endif::[]
470: 
471: ifeval::["{archiveType}"=="war"]
472: :archiveDir: {artifactId}/target
473: endif::[]
474: 
475: ifeval::["{archiveType}"=="jar"]
476: :archiveDir: {artifactId}/target
477: endif::[]
478: 
479: endif::archiveDir[]
480: 
481: ifndef::mavenCommand[]
482: ifeval::["{archiveType}"=="ear"]
483: :mavenCommand: clean install
484: endif::[]
485: 
486: ifeval::["{archiveType}"=="war"]
487: :mavenCommand: clean package
488: endif::[]
489: 
490: ifeval::["{archiveType}"=="jar"]
491: :mavenCommand: clean install
492: endif::[]
493: 
494: endif::mavenCommand[]
495: 
496: . Make sure you xref:start_the_eap_standalone_server[start the {productName} server] as described above.
497: . Open a terminal and navigate to the root directory of this quickstart.
498: ifdef::reactive-messaging[]
499: . Run this command to enable the MicroProfile Reactive Messaging functionality on the server
500: +
501: [source,subs="attributes+",options="nowrap"]
502: ----
503: $ __{jbossHomeName}__/bin/jboss-cli.sh --connect --file=enable-reactive-messaging.cli
504: ----
505: endif::reactive-messaging[]
506: . Type the following command to build the quickstart.
507: +
508: [source,subs="attributes+",options="nowrap"]
509: ----
510: $ mvn {mavenCommand}
511: ----
512: 
513: . Type the following command to deploy the quickstart.
514: +
515: [source,subs="attributes+",options="nowrap"]
516: ----
517: $ mvn wildfly:deploy
518: ----
519: 
520: ifdef::rest-client-qs[]
521: This builds and deploys the `country-server` and `country-client` to the running instance of the server.
522: 
523: You should see a message in the server log indicating that the archives deployed successfully.
524: endif::[]
525: ifndef::rest-client-qs[]
526: This deploys the `{archiveDir}/{archiveFileName}` to the running instance of the server.
527: 
528: You should see a message in the server log indicating that the archive deployed successfully.
529: endif::[]
530: 
531: 
532: :leveloffset!:
533: 
534: === Access the Application
535: 
536: The application will be running at the following URL: http://localhost:8080/{artifactId}/.
537: 
538: === Server Log: Expected Warnings and Errors
539: 
540: You will see the following warnings in the server log. You can ignore these warnings.
541: 
542: [source,options="nowrap"]
543: ----
544: WFLYJCA0091: -ds.xml file deployments are deprecated. Support may be removed in a future version.
545: ----
546: 
547: // Testing with Arquillian
548: :leveloffset: +2
549: 
550: [[run_the_arquillian_integration_tests_with_server_distribution]]
551: = Run the Arquillian Integration Tests
552: //******************************************************************************
553: // Include this template if your quickstart provides standard Arquillian
554: // integration tests.
555: //******************************************************************************
556: 
557: This quickstart includes Arquillian integration tests. They are located under the  `src/test/` directory. The integration tests verify that the quickstart runs correctly when deployed on the server.
558: 
559: Follow these steps to run the integration tests.
560: 
561: . Make sure you start the {productName} server, as previously described.
562: 
563: . Make sure you build and deploy the quickstart, as previously described.
564: 
565: . Type the following command to run the `verify` goal with the `arq-remote` profile activated.
566: +
567: [source,options="nowrap"]
568: ----
569: $ mvn verify -Parq-remote
570: ----
571: 
572: [NOTE]
573: ====
574: You may also use the environment variable `SERVER_HOST` or the system property `server.host` to define the target host of the tests.
575: ====
576: 
577: :leveloffset!:
578: // Undeploy the Quickstart
579: :leveloffset: +2
580: 
581: [[undeploy_the_quickstart]]
582: = Undeploy the Quickstart
583: 
584: //*******************************************************************************
585: // Include this template if your quickstart does a normal undeployment of an archive.
586: //*******************************************************************************
587: When you are finished testing the quickstart, follow these steps to undeploy the archive.
588: 
589: . Make sure you xref:start_the_eap_standalone_server[start the {productName} server] as described above.
590: . Open a terminal and navigate to the root directory of this quickstart.
591: . Type this command to undeploy the archive:
592: +
593: [source,options="nowrap"]
594: ----
595: $ mvn wildfly:undeploy
596: ----
597: 
598: :leveloffset!:
599: 
600: // Build and run sections for other environments/builds
601: ifndef::ProductRelease,EAPXPRelease[]
602: include::../shared-doc/build-and-run-the-quickstart-with-provisioned-server.adoc[leveloffset=+1]
603: endif::[]
604: :leveloffset: +1
605: 
606: [[build_and_run_the_quickstart_on_openshift]]
607: = Building and running the quickstart application with OpenShift
608: // The openshift profile
609: :leveloffset: +1
610: 
611: [[build-the-quickstart-for-openshift]]
612: == Build the {productName} Source-to-Image (S2I) Quickstart to OpenShift with Helm Charts
613: 
614: On OpenShift, the S2I build with Apache Maven will use an `openshift` profile used to provision a {productName} server to deploy and run the quickstart in OpenShift environment.
615: You can activate the Maven profile named `openshift` when building the quickstart:
616: 
617: [source,subs="attributes+",options="nowrap"]
618: ----
619: $ mvn clean package -Popenshift
620: ----
621: 
622: The provisioned {productName} server for OpenShift, with the quickstart deployed, can then be found in the `target/server` directory, and its usage is similar to a standard server distribution.
623: ifndef::ProductRelease,EAPXPRelease[]
624: You may note that unlike the `provisioned-server` profile it uses the cloud feature pack which enables a configuration tuned for OpenShift environment.
625: endif::[]
626: ifdef::ProductRelease,EAPXPRelease[]
627: You may note that it uses the cloud feature pack which enables a configuration tuned for OpenShift environment.
628: endif::[]
629: 
630: ifndef::ProductRelease,EAPXPRelease[]
631: The server provisioning functionality is provided by the WildFly Maven Plugin, and you may find its configuration in the quickstart `pom.xml`:
632: endif::[]
633: 
634: ifdef::ProductRelease,EAPXPRelease[]
635: The server provisioning functionality is provided by the EAP Maven Plugin, and you may find its configuration in the quickstart `pom.xml`:
636: endif::[]
637: 
638: ifndef::ProductRelease,EAPXPRelease[]
639: [source,xml,subs="attributes+"]
640: ----
641:         <profile>
642:             <id>openshift</id>
643:             <build>
644:                 <plugins>
645:                     <plugin>
646:                         <groupId>org.jboss.eap.plugins</groupId>
647:                         <artifactId>eap-maven-plugin</artifactId>
648:                         <version>${version.eap.maven.plugin}</version>
649:                         <configuration>
650:                             <feature-packs>
651:                                 <feature-pack>
652:                                     <location>org.wildfly:wildfly-galleon-pack:${version.server}</location>
653:                                 </feature-pack>
654:                                 <feature-pack>
655:                                     <location>org.wildfly.cloud:wildfly-cloud-galleon-pack:${version.cloud.fp}</location>
656:                                 </feature-pack>
657:                             </feature-packs>
658:                             <layers>
659:                                 <layer>cloud-server</layer>
660:                             </layers>
661:                             <filename>ROOT.war</filename>
662:                         </configuration>
663:                         <executions>
664:                             <execution>
665:                                 <goals>
666:                                     <goal>package</goal>
667:                                 </goals>
668:                             </execution>
669:                         </executions>
670:                     </plugin>
671:                 </plugins>
672:             </build>
673:         </profile>
674: ----
675: endif::[]
676: 
677: ifdef::ProductRelease,EAPXPRelease[]
678: [source,xml,subs="attributes+"]
679: ----
680:         <profile>
681:             <id>openshift</id>
682:             <build>
683:                 <plugins>
684:                     <plugin>
685:                         <groupId>org.jboss.eap.plugins</groupId>
686:                         <artifactId>eap-maven-plugin</artifactId>
687:                         <version>${version.eap.maven.plugin}</version>
688:                         <configuration>
689:                             <feature-packs>
690:                                 <feature-pack>
691:                                     <location>org.jboss.eap:wildfly-ee-galleon-pack</location>
692:                                 </feature-pack>
693:                                 <feature-pack>
694:                                     <location>org.jboss.eap.cloud:eap-cloud-galleon-pack</location>
695:                                 </feature-pack>
696:                             </feature-packs>
697:                             <layers>
698:                                 <layer>cloud-server</layer>
699:                             </layers>
700:                             <filename>ROOT.war</filename>
701:                         </configuration>
702:                         <executions>
703:                             <execution>
704:                                 <goals>
705:                                     <goal>package</goal>
706:                                 </goals>
707:                             </execution>
708:                         </executions>
709:                     </plugin>
710:                 </plugins>
711:             </build>
712:         </profile>
713: ----
714: endif::[]
715: 
716: [NOTE]
717: ====
718: Since the plugin configuration above deploys quickstart on root web context of the provisioned server, the URL to access the application should not have the `/{artifactId}` path segment after `HOST:PORT`.
719: ====
720: 
721: :leveloffset: 1
722: // Getting Started with Helm
723: :leveloffset: +1
724: 
725: [[getting_started_with_helm]]
726: = Getting Started with {xpaasproduct-shortname} and Helm Charts
727: 
728: This section contains the basic instructions to build and deploy this quickstart to {xpaasproduct-shortname} or {xpaasproductOpenShiftOnline-shortname} using Helm Charts.
729: 
730: [[prerequisites_helm_openshift]]
731: == Prerequisites
732: 
733: * You must be logged in OpenShift and have an `oc` client to connect to OpenShift
734: * https://helm.sh[Helm] must be installed to deploy the backend on OpenShift.
735: 
736: Once you have installed Helm, you need to add the repository that provides Helm Charts for {productName}.
737: 
738: ifndef::ProductRelease,EAPXPRelease[]
739: [source,options="nowrap"]
740: ----
741: $ helm repo add wildfly https://docs.wildfly.org/wildfly-charts/
742: "wildfly" has been added to your repositories
743: $ helm search repo wildfly
744: NAME                    CHART VERSION   APP VERSION     DESCRIPTION
745: wildfly/wildfly         ...             ...            Build and Deploy WildFly applications on OpenShift
746: wildfly/wildfly-common  ...             ...            A library chart for WildFly-based applications
747: ----
748: endif::[]
749: ifdef::ProductRelease[]
750: [source,options="nowrap",subs="+attributes"]
751: ----
752: $ helm repo add jboss-eap https://jbossas.github.io/eap-charts/
753: "jboss-eap" has been added to your repositories
754: $ helm search repo jboss-eap
755: NAME                    CHART VERSION   APP VERSION     DESCRIPTION
756: {helmChartName}         ...             ...             A Helm chart to build and deploy EAP {productVersion} applications
757: ----
758: endif::[]
759: ifdef::EAPXPRelease[]
760: [source,options="nowrap",subs="+attributes"]
761: ----
762: $ helm repo add jboss-eap https://jbossas.github.io/eap-charts/
763: "jboss-eap" has been added to your repositories
764: $ helm search repo jboss-eap
765: NAME                    CHART VERSION   APP VERSION     DESCRIPTION
766: {helmChartName}         ...             ...             A Helm chart to build and deploy EAP XP {productVersion} applications
767: ----
768: endif::[]
769: 
770: :leveloffset: 1
771: //Prepare Helm for Quickstart Deployment
772: :leveloffset: +1
773: 
774: [[deploy_helm]]
775: == Deploy the {ProductShortName} Source-to-Image (S2I) Quickstart to OpenShift with Helm Charts
776: 
777: Log in to your OpenShift instance using the `oc login` command.
778: The backend will be built and deployed on OpenShift with a Helm Chart for {productName}.
779: 
780: Navigate to the root directory of this quickstart and run the following command:
781: [source,options="nowrap",subs="+attributes"]
782: ----
783: $ helm install {artifactId} -f charts/helm.yaml {helmChartName}
784: NAME: {artifactId}
785: ...
786: STATUS: deployed
787: REVISION: 1
788: ----
789: 
790: The Helm Chart for this quickstart contains all the information to build an image from the source code using S2I on Java 17:
791: 
792: [source,options="nowrap",subs="+attributes"]
793: ----
794: build:
795:   uri: {githubRepoCodeUrl}
796:   ref: {WildFlyQuickStartRepoTag}
797:   contextDir: {artifactId}
798: deploy:
799:   replicas: 1
800: ----
801: 
802: This will create a new deployment on OpenShift and deploy the application.
803: 
804: If you want to see all the configuration elements to customize your deployment you can use the following command:
805: [source,options="nowrap",subs="+attributes"]
806: ----
807: $ helm show readme {helmChartName}
808: ----
809: 
810: Let’s wait for the application to be built and deployed:
811: [source,options="nowrap",subs="+attributes"]
812: ----
813: $ oc get deployment {artifactId} -w
814: NAME         DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
815: {artifactId}   1         1         1            0           12s
816: ...
817: {artifactId}   1         1         1            1           2m
818: ----
819: 
820: Get the URL of the route to the deployment.
821: 
822: [source,options="nowrap",subs="+attributes"]
823: ----
824: $ oc get route {artifactId} -o jsonpath="{.spec.host}"
825: ----
826: Access the application in your web browser using the displayed URL.
827: 
828: [NOTE]
829: ====
830: The Maven profile named `openshift` is used by the Helm chart to provision the server with the quickstart deployed on the root web context, and thus the application should be accessed with the URL without the `/{artifactId}` path segment after `HOST:PORT`.
831: ====
832: 
833: [[undeploy_helm]]
834: == Undeploy the {ProductShortName} Source-to-Image (S2I) Quickstart from OpenShift with Helm Charts
835: 
836: 
837: [source,options="nowrap",subs="+attributes"]
838: ----
839: $ helm uninstall {artifactId}
840: ----
841: 
842: :leveloffset: 1
843: // Testing on Openshift
844: :leveloffset: +1
845: 
846: [[run_the_arquillian_integration_tests_with_openshift]]
847: = Run the Arquillian Integration Tests with OpenShift
848: //******************************************************************************
849: // Include this template if your quickstart provides Openshift Arquillian
850: // integration tests.
851: //******************************************************************************
852: 
853: This quickstart includes Arquillian integration tests. They are located under the  `src/test/` directory. The integration tests verify that the quickstart runs correctly when deployed on the server.
854: 
855: [NOTE]
856: ====
857: The Arquillian integration tests expect a deployed application, so make sure you have deployed the quickstart on OpenShift before you begin.
858: ====
859: 
860: Run the integration tests using the following command to run the `verify` goal with the `arq-remote` profile activated and the proper URL:
861: [source,options="nowrap",subs="+attributes"]
862: ----
863: $ mvn clean verify -Parq-remote -Dserver.host=https://$(oc get route {artifactId} --template='{{ .spec.host }}')
864: ----
865: 
866: [NOTE]
867: ====
868: The tests are using SSL to connect to the quickstart running on OpenShift. So you need the certificates to be trusted by the machine the tests are run from.
869: ====
870: 
871: :leveloffset: 1
872: 
873: :leveloffset!:
```

## File: legacy/README.html
```html
  1: <!DOCTYPE html>
  2: <html lang="en">
  3: <head>
  4: <meta charset="UTF-8">
  5: <meta http-equiv="X-UA-Compatible" content="IE=edge">
  6: <meta name="viewport" content="width=device-width, initial-scale=1.0">
  7: <meta name="generator" content="Asciidoctor 2.0.10">
  8: <meta name="author" content="Pete Muir">
  9: <title>kitchensink: Assortment of technologies including Arquillian</title>
 10: <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic,600,600italic%7CNoto+Serif:400,400italic,700,700italic%7CDroid+Sans+Mono:400,700">
 11: <style>
 12: /* Asciidoctor default stylesheet | MIT License | https://asciidoctor.org */
 13: /* Uncomment @import statement to use as custom stylesheet */
 14: /*@import "https://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic,600,600italic%7CNoto+Serif:400,400italic,700,700italic%7CDroid+Sans+Mono:400,700";*/
 15: article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}
 16: audio,video{display:inline-block}
 17: audio:not([controls]){display:none;height:0}
 18: html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}
 19: a{background:none}
 20: a:focus{outline:thin dotted}
 21: a:active,a:hover{outline:0}
 22: h1{font-size:2em;margin:.67em 0}
 23: abbr[title]{border-bottom:1px dotted}
 24: b,strong{font-weight:bold}
 25: dfn{font-style:italic}
 26: hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}
 27: mark{background:#ff0;color:#000}
 28: code,kbd,pre,samp{font-family:monospace;font-size:1em}
 29: pre{white-space:pre-wrap}
 30: q{quotes:"\201C" "\201D" "\2018" "\2019"}
 31: small{font-size:80%}
 32: sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}
 33: sup{top:-.5em}
 34: sub{bottom:-.25em}
 35: img{border:0}
 36: svg:not(:root){overflow:hidden}
 37: figure{margin:0}
 38: fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}
 39: legend{border:0;padding:0}
 40: button,input,select,textarea{font-family:inherit;font-size:100%;margin:0}
 41: button,input{line-height:normal}
 42: button,select{text-transform:none}
 43: button,html input[type="button"],input[type="reset"],input[type="submit"]{-webkit-appearance:button;cursor:pointer}
 44: button[disabled],html input[disabled]{cursor:default}
 45: input[type="checkbox"],input[type="radio"]{box-sizing:border-box;padding:0}
 46: button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}
 47: textarea{overflow:auto;vertical-align:top}
 48: table{border-collapse:collapse;border-spacing:0}
 49: *,*::before,*::after{-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}
 50: html,body{font-size:100%}
 51: body{background:#fff;color:rgba(0,0,0,.8);padding:0;margin:0;font-family:"Noto Serif","DejaVu Serif",serif;font-weight:400;font-style:normal;line-height:1;position:relative;cursor:auto;tab-size:4;-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased}
 52: a:hover{cursor:pointer}
 53: img,object,embed{max-width:100%;height:auto}
 54: object,embed{height:100%}
 55: img{-ms-interpolation-mode:bicubic}
 56: .left{float:left!important}
 57: .right{float:right!important}
 58: .text-left{text-align:left!important}
 59: .text-right{text-align:right!important}
 60: .text-center{text-align:center!important}
 61: .text-justify{text-align:justify!important}
 62: .hide{display:none}
 63: img,object,svg{display:inline-block;vertical-align:middle}
 64: textarea{height:auto;min-height:50px}
 65: select{width:100%}
 66: .center{margin-left:auto;margin-right:auto}
 67: .stretch{width:100%}
 68: .subheader,.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{line-height:1.45;color:#7a2518;font-weight:400;margin-top:0;margin-bottom:.25em}
 69: div,dl,dt,dd,ul,ol,li,h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6,pre,form,p,blockquote,th,td{margin:0;padding:0;direction:ltr}
 70: a{color:#2156a5;text-decoration:underline;line-height:inherit}
 71: a:hover,a:focus{color:#1d4b8f}
 72: a img{border:0}
 73: p{font-family:inherit;font-weight:400;font-size:1em;line-height:1.6;margin-bottom:1.25em;text-rendering:optimizeLegibility}
 74: p aside{font-size:.875em;line-height:1.35;font-style:italic}
 75: h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{font-family:"Open Sans","DejaVu Sans",sans-serif;font-weight:300;font-style:normal;color:#ba3925;text-rendering:optimizeLegibility;margin-top:1em;margin-bottom:.5em;line-height:1.0125em}
 76: h1 small,h2 small,h3 small,#toctitle small,.sidebarblock>.content>.title small,h4 small,h5 small,h6 small{font-size:60%;color:#e99b8f;line-height:0}
 77: h1{font-size:2.125em}
 78: h2{font-size:1.6875em}
 79: h3,#toctitle,.sidebarblock>.content>.title{font-size:1.375em}
 80: h4,h5{font-size:1.125em}
 81: h6{font-size:1em}
 82: hr{border:solid #dddddf;border-width:1px 0 0;clear:both;margin:1.25em 0 1.1875em;height:0}
 83: em,i{font-style:italic;line-height:inherit}
 84: strong,b{font-weight:bold;line-height:inherit}
 85: small{font-size:60%;line-height:inherit}
 86: code{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;font-weight:400;color:rgba(0,0,0,.9)}
 87: ul,ol,dl{font-size:1em;line-height:1.6;margin-bottom:1.25em;list-style-position:outside;font-family:inherit}
 88: ul,ol{margin-left:1.5em}
 89: ul li ul,ul li ol{margin-left:1.25em;margin-bottom:0;font-size:1em}
 90: ul.square li ul,ul.circle li ul,ul.disc li ul{list-style:inherit}
 91: ul.square{list-style-type:square}
 92: ul.circle{list-style-type:circle}
 93: ul.disc{list-style-type:disc}
 94: ol li ul,ol li ol{margin-left:1.25em;margin-bottom:0}
 95: dl dt{margin-bottom:.3125em;font-weight:bold}
 96: dl dd{margin-bottom:1.25em}
 97: abbr,acronym{text-transform:uppercase;font-size:90%;color:rgba(0,0,0,.8);border-bottom:1px dotted #ddd;cursor:help}
 98: abbr{text-transform:none}
 99: blockquote{margin:0 0 1.25em;padding:.5625em 1.25em 0 1.1875em;border-left:1px solid #ddd}
100: blockquote cite{display:block;font-size:.9375em;color:rgba(0,0,0,.6)}
101: blockquote cite::before{content:"\2014 \0020"}
102: blockquote cite a,blockquote cite a:visited{color:rgba(0,0,0,.6)}
103: blockquote,blockquote p{line-height:1.6;color:rgba(0,0,0,.85)}
104: @media screen and (min-width:768px){h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2}
105: h1{font-size:2.75em}
106: h2{font-size:2.3125em}
107: h3,#toctitle,.sidebarblock>.content>.title{font-size:1.6875em}
108: h4{font-size:1.4375em}}
109: table{background:#fff;margin-bottom:1.25em;border:solid 1px #dedede}
110: table thead,table tfoot{background:#f7f8f7}
111: table thead tr th,table thead tr td,table tfoot tr th,table tfoot tr td{padding:.5em .625em .625em;font-size:inherit;color:rgba(0,0,0,.8);text-align:left}
112: table tr th,table tr td{padding:.5625em .625em;font-size:inherit;color:rgba(0,0,0,.8)}
113: table tr.even,table tr.alt{background:#f8f8f7}
114: table thead tr th,table tfoot tr th,table tbody tr td,table tr td,table tfoot tr td{display:table-cell;line-height:1.6}
115: h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2;word-spacing:-.05em}
116: h1 strong,h2 strong,h3 strong,#toctitle strong,.sidebarblock>.content>.title strong,h4 strong,h5 strong,h6 strong{font-weight:400}
117: .clearfix::before,.clearfix::after,.float-group::before,.float-group::after{content:" ";display:table}
118: .clearfix::after,.float-group::after{clear:both}
119: :not(pre):not([class^=L])>code{font-size:.9375em;font-style:normal!important;letter-spacing:0;padding:.1em .5ex;word-spacing:-.15em;background:#f7f7f8;-webkit-border-radius:4px;border-radius:4px;line-height:1.45;text-rendering:optimizeSpeed;word-wrap:break-word}
120: :not(pre)>code.nobreak{word-wrap:normal}
121: :not(pre)>code.nowrap{white-space:nowrap}
122: pre{color:rgba(0,0,0,.9);font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;line-height:1.45;text-rendering:optimizeSpeed}
123: pre code,pre pre{color:inherit;font-size:inherit;line-height:inherit}
124: pre>code{display:block}
125: pre.nowrap,pre.nowrap pre{white-space:pre;word-wrap:normal}
126: em em{font-style:normal}
127: strong strong{font-weight:400}
128: .keyseq{color:rgba(51,51,51,.8)}
129: kbd{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;display:inline-block;color:rgba(0,0,0,.8);font-size:.65em;line-height:1.45;background:#f7f7f7;border:1px solid #ccc;-webkit-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em white inset;box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em #fff inset;margin:0 .15em;padding:.2em .5em;vertical-align:middle;position:relative;top:-.1em;white-space:nowrap}
130: .keyseq kbd:first-child{margin-left:0}
131: .keyseq kbd:last-child{margin-right:0}
132: .menuseq,.menuref{color:#000}
133: .menuseq b:not(.caret),.menuref{font-weight:inherit}
134: .menuseq{word-spacing:-.02em}
135: .menuseq b.caret{font-size:1.25em;line-height:.8}
136: .menuseq i.caret{font-weight:bold;text-align:center;width:.45em}
137: b.button::before,b.button::after{position:relative;top:-1px;font-weight:400}
138: b.button::before{content:"[";padding:0 3px 0 2px}
139: b.button::after{content:"]";padding:0 2px 0 3px}
140: p a>code:hover{color:rgba(0,0,0,.9)}
141: #header,#content,#footnotes,#footer{width:100%;margin-left:auto;margin-right:auto;margin-top:0;margin-bottom:0;max-width:62.5em;*zoom:1;position:relative;padding-left:.9375em;padding-right:.9375em}
142: #header::before,#header::after,#content::before,#content::after,#footnotes::before,#footnotes::after,#footer::before,#footer::after{content:" ";display:table}
143: #header::after,#content::after,#footnotes::after,#footer::after{clear:both}
144: #content{margin-top:1.25em}
145: #content::before{content:none}
146: #header>h1:first-child{color:rgba(0,0,0,.85);margin-top:2.25rem;margin-bottom:0}
147: #header>h1:first-child+#toc{margin-top:8px;border-top:1px solid #dddddf}
148: #header>h1:only-child,body.toc2 #header>h1:nth-last-child(2){border-bottom:1px solid #dddddf;padding-bottom:8px}
149: #header .details{border-bottom:1px solid #dddddf;line-height:1.45;padding-top:.25em;padding-bottom:.25em;padding-left:.25em;color:rgba(0,0,0,.6);display:-ms-flexbox;display:-webkit-flex;display:flex;-ms-flex-flow:row wrap;-webkit-flex-flow:row wrap;flex-flow:row wrap}
150: #header .details span:first-child{margin-left:-.125em}
151: #header .details span.email a{color:rgba(0,0,0,.85)}
152: #header .details br{display:none}
153: #header .details br+span::before{content:"\00a0\2013\00a0"}
154: #header .details br+span.author::before{content:"\00a0\22c5\00a0";color:rgba(0,0,0,.85)}
155: #header .details br+span#revremark::before{content:"\00a0|\00a0"}
156: #header #revnumber{text-transform:capitalize}
157: #header #revnumber::after{content:"\00a0"}
158: #content>h1:first-child:not([class]){color:rgba(0,0,0,.85);border-bottom:1px solid #dddddf;padding-bottom:8px;margin-top:0;padding-top:1rem;margin-bottom:1.25rem}
159: #toc{border-bottom:1px solid #e7e7e9;padding-bottom:.5em}
160: #toc>ul{margin-left:.125em}
161: #toc ul.sectlevel0>li>a{font-style:italic}
162: #toc ul.sectlevel0 ul.sectlevel1{margin:.5em 0}
163: #toc ul{font-family:"Open Sans","DejaVu Sans",sans-serif;list-style-type:none}
164: #toc li{line-height:1.3334;margin-top:.3334em}
165: #toc a{text-decoration:none}
166: #toc a:active{text-decoration:underline}
167: #toctitle{color:#7a2518;font-size:1.2em}
168: @media screen and (min-width:768px){#toctitle{font-size:1.375em}
169: body.toc2{padding-left:15em;padding-right:0}
170: #toc.toc2{margin-top:0!important;background:#f8f8f7;position:fixed;width:15em;left:0;top:0;border-right:1px solid #e7e7e9;border-top-width:0!important;border-bottom-width:0!important;z-index:1000;padding:1.25em 1em;height:100%;overflow:auto}
171: #toc.toc2 #toctitle{margin-top:0;margin-bottom:.8rem;font-size:1.2em}
172: #toc.toc2>ul{font-size:.9em;margin-bottom:0}
173: #toc.toc2 ul ul{margin-left:0;padding-left:1em}
174: #toc.toc2 ul.sectlevel0 ul.sectlevel1{padding-left:0;margin-top:.5em;margin-bottom:.5em}
175: body.toc2.toc-right{padding-left:0;padding-right:15em}
176: body.toc2.toc-right #toc.toc2{border-right-width:0;border-left:1px solid #e7e7e9;left:auto;right:0}}
177: @media screen and (min-width:1280px){body.toc2{padding-left:20em;padding-right:0}
178: #toc.toc2{width:20em}
179: #toc.toc2 #toctitle{font-size:1.375em}
180: #toc.toc2>ul{font-size:.95em}
181: #toc.toc2 ul ul{padding-left:1.25em}
182: body.toc2.toc-right{padding-left:0;padding-right:20em}}
183: #content #toc{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}
184: #content #toc>:first-child{margin-top:0}
185: #content #toc>:last-child{margin-bottom:0}
186: #footer{max-width:100%;background:rgba(0,0,0,.8);padding:1.25em}
187: #footer-text{color:rgba(255,255,255,.8);line-height:1.44}
188: #content{margin-bottom:.625em}
189: .sect1{padding-bottom:.625em}
190: @media screen and (min-width:768px){#content{margin-bottom:1.25em}
191: .sect1{padding-bottom:1.25em}}
192: .sect1:last-child{padding-bottom:0}
193: .sect1+.sect1{border-top:1px solid #e7e7e9}
194: #content h1>a.anchor,h2>a.anchor,h3>a.anchor,#toctitle>a.anchor,.sidebarblock>.content>.title>a.anchor,h4>a.anchor,h5>a.anchor,h6>a.anchor{position:absolute;z-index:1001;width:1.5ex;margin-left:-1.5ex;display:block;text-decoration:none!important;visibility:hidden;text-align:center;font-weight:400}
195: #content h1>a.anchor::before,h2>a.anchor::before,h3>a.anchor::before,#toctitle>a.anchor::before,.sidebarblock>.content>.title>a.anchor::before,h4>a.anchor::before,h5>a.anchor::before,h6>a.anchor::before{content:"\00A7";font-size:.85em;display:block;padding-top:.1em}
196: #content h1:hover>a.anchor,#content h1>a.anchor:hover,h2:hover>a.anchor,h2>a.anchor:hover,h3:hover>a.anchor,#toctitle:hover>a.anchor,.sidebarblock>.content>.title:hover>a.anchor,h3>a.anchor:hover,#toctitle>a.anchor:hover,.sidebarblock>.content>.title>a.anchor:hover,h4:hover>a.anchor,h4>a.anchor:hover,h5:hover>a.anchor,h5>a.anchor:hover,h6:hover>a.anchor,h6>a.anchor:hover{visibility:visible}
197: #content h1>a.link,h2>a.link,h3>a.link,#toctitle>a.link,.sidebarblock>.content>.title>a.link,h4>a.link,h5>a.link,h6>a.link{color:#ba3925;text-decoration:none}
198: #content h1>a.link:hover,h2>a.link:hover,h3>a.link:hover,#toctitle>a.link:hover,.sidebarblock>.content>.title>a.link:hover,h4>a.link:hover,h5>a.link:hover,h6>a.link:hover{color:#a53221}
199: details,.audioblock,.imageblock,.literalblock,.listingblock,.stemblock,.videoblock{margin-bottom:1.25em}
200: details>summary:first-of-type{cursor:pointer;display:list-item;outline:none;margin-bottom:.75em}
201: .admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{text-rendering:optimizeLegibility;text-align:left;font-family:"Noto Serif","DejaVu Serif",serif;font-size:1rem;font-style:italic}
202: table.tableblock.fit-content>caption.title{white-space:nowrap;width:0}
203: .paragraph.lead>p,#preamble>.sectionbody>[class="paragraph"]:first-of-type p{font-size:1.21875em;line-height:1.6;color:rgba(0,0,0,.85)}
204: table.tableblock #preamble>.sectionbody>[class="paragraph"]:first-of-type p{font-size:inherit}
205: .admonitionblock>table{border-collapse:separate;border:0;background:none;width:100%}
206: .admonitionblock>table td.icon{text-align:center;width:80px}
207: .admonitionblock>table td.icon img{max-width:none}
208: .admonitionblock>table td.icon .title{font-weight:bold;font-family:"Open Sans","DejaVu Sans",sans-serif;text-transform:uppercase}
209: .admonitionblock>table td.content{padding-left:1.125em;padding-right:1.25em;border-left:1px solid #dddddf;color:rgba(0,0,0,.6)}
210: .admonitionblock>table td.content>:last-child>:last-child{margin-bottom:0}
211: .exampleblock>.content{border-style:solid;border-width:1px;border-color:#e6e6e6;margin-bottom:1.25em;padding:1.25em;background:#fff;-webkit-border-radius:4px;border-radius:4px}
212: .exampleblock>.content>:first-child{margin-top:0}
213: .exampleblock>.content>:last-child{margin-bottom:0}
214: .sidebarblock{border-style:solid;border-width:1px;border-color:#dbdbd6;margin-bottom:1.25em;padding:1.25em;background:#f3f3f2;-webkit-border-radius:4px;border-radius:4px}
215: .sidebarblock>:first-child{margin-top:0}
216: .sidebarblock>:last-child{margin-bottom:0}
217: .sidebarblock>.content>.title{color:#7a2518;margin-top:0;text-align:center}
218: .exampleblock>.content>:last-child>:last-child,.exampleblock>.content .olist>ol>li:last-child>:last-child,.exampleblock>.content .ulist>ul>li:last-child>:last-child,.exampleblock>.content .qlist>ol>li:last-child>:last-child,.sidebarblock>.content>:last-child>:last-child,.sidebarblock>.content .olist>ol>li:last-child>:last-child,.sidebarblock>.content .ulist>ul>li:last-child>:last-child,.sidebarblock>.content .qlist>ol>li:last-child>:last-child{margin-bottom:0}
219: .literalblock pre,.listingblock>.content>pre{-webkit-border-radius:4px;border-radius:4px;word-wrap:break-word;overflow-x:auto;padding:1em;font-size:.8125em}
220: @media screen and (min-width:768px){.literalblock pre,.listingblock>.content>pre{font-size:.90625em}}
221: @media screen and (min-width:1280px){.literalblock pre,.listingblock>.content>pre{font-size:1em}}
222: .literalblock pre,.listingblock>.content>pre:not(.highlight),.listingblock>.content>pre[class="highlight"],.listingblock>.content>pre[class^="highlight "]{background:#f7f7f8}
223: .literalblock.output pre{color:#f7f7f8;background:rgba(0,0,0,.9)}
224: .listingblock>.content{position:relative}
225: .listingblock code[data-lang]::before{display:none;content:attr(data-lang);position:absolute;font-size:.75em;top:.425rem;right:.5rem;line-height:1;text-transform:uppercase;color:inherit;opacity:.5}
226: .listingblock:hover code[data-lang]::before{display:block}
227: .listingblock.terminal pre .command::before{content:attr(data-prompt);padding-right:.5em;color:inherit;opacity:.5}
228: .listingblock.terminal pre .command:not([data-prompt])::before{content:"$"}
229: .listingblock pre.highlightjs{padding:0}
230: .listingblock pre.highlightjs>code{padding:1em;-webkit-border-radius:4px;border-radius:4px}
231: .listingblock pre.prettyprint{border-width:0}
232: .prettyprint{background:#f7f7f8}
233: pre.prettyprint .linenums{line-height:1.45;margin-left:2em}
234: pre.prettyprint li{background:none;list-style-type:inherit;padding-left:0}
235: pre.prettyprint li code[data-lang]::before{opacity:1}
236: pre.prettyprint li:not(:first-child) code[data-lang]::before{display:none}
237: table.linenotable{border-collapse:separate;border:0;margin-bottom:0;background:none}
238: table.linenotable td[class]{color:inherit;vertical-align:top;padding:0;line-height:inherit;white-space:normal}
239: table.linenotable td.code{padding-left:.75em}
240: table.linenotable td.linenos{border-right:1px solid currentColor;opacity:.35;padding-right:.5em}
241: pre.pygments .lineno{border-right:1px solid currentColor;opacity:.35;display:inline-block;margin-right:.75em}
242: pre.pygments .lineno::before{content:"";margin-right:-.125em}
243: .quoteblock{margin:0 1em 1.25em 1.5em;display:table}
244: .quoteblock:not(.excerpt)>.title{margin-left:-1.5em;margin-bottom:.75em}
245: .quoteblock blockquote,.quoteblock p{color:rgba(0,0,0,.85);font-size:1.15rem;line-height:1.75;word-spacing:.1em;letter-spacing:0;font-style:italic;text-align:justify}
246: .quoteblock blockquote{margin:0;padding:0;border:0}
247: .quoteblock blockquote::before{content:"\201c";float:left;font-size:2.75em;font-weight:bold;line-height:.6em;margin-left:-.6em;color:#7a2518;text-shadow:0 1px 2px rgba(0,0,0,.1)}
248: .quoteblock blockquote>.paragraph:last-child p{margin-bottom:0}
249: .quoteblock .attribution{margin-top:.75em;margin-right:.5ex;text-align:right}
250: .verseblock{margin:0 1em 1.25em}
251: .verseblock pre{font-family:"Open Sans","DejaVu Sans",sans;font-size:1.15rem;color:rgba(0,0,0,.85);font-weight:300;text-rendering:optimizeLegibility}
252: .verseblock pre strong{font-weight:400}
253: .verseblock .attribution{margin-top:1.25rem;margin-left:.5ex}
254: .quoteblock .attribution,.verseblock .attribution{font-size:.9375em;line-height:1.45;font-style:italic}
255: .quoteblock .attribution br,.verseblock .attribution br{display:none}
256: .quoteblock .attribution cite,.verseblock .attribution cite{display:block;letter-spacing:-.025em;color:rgba(0,0,0,.6)}
257: .quoteblock.abstract blockquote::before,.quoteblock.excerpt blockquote::before,.quoteblock .quoteblock blockquote::before{display:none}
258: .quoteblock.abstract blockquote,.quoteblock.abstract p,.quoteblock.excerpt blockquote,.quoteblock.excerpt p,.quoteblock .quoteblock blockquote,.quoteblock .quoteblock p{line-height:1.6;word-spacing:0}
259: .quoteblock.abstract{margin:0 1em 1.25em;display:block}
260: .quoteblock.abstract>.title{margin:0 0 .375em;font-size:1.15em;text-align:center}
261: .quoteblock.excerpt>blockquote,.quoteblock .quoteblock{padding:0 0 .25em 1em;border-left:.25em solid #dddddf}
262: .quoteblock.excerpt,.quoteblock .quoteblock{margin-left:0}
263: .quoteblock.excerpt blockquote,.quoteblock.excerpt p,.quoteblock .quoteblock blockquote,.quoteblock .quoteblock p{color:inherit;font-size:1.0625rem}
264: .quoteblock.excerpt .attribution,.quoteblock .quoteblock .attribution{color:inherit;text-align:left;margin-right:0}
265: table.tableblock{max-width:100%;border-collapse:separate}
266: p.tableblock:last-child{margin-bottom:0}
267: td.tableblock>.content>:last-child{margin-bottom:-1.25em}
268: td.tableblock>.content>:last-child.sidebarblock{margin-bottom:0}
269: table.tableblock,th.tableblock,td.tableblock{border:0 solid #dedede}
270: table.grid-all>thead>tr>.tableblock,table.grid-all>tbody>tr>.tableblock{border-width:0 1px 1px 0}
271: table.grid-all>tfoot>tr>.tableblock{border-width:1px 1px 0 0}
272: table.grid-cols>*>tr>.tableblock{border-width:0 1px 0 0}
273: table.grid-rows>thead>tr>.tableblock,table.grid-rows>tbody>tr>.tableblock{border-width:0 0 1px}
274: table.grid-rows>tfoot>tr>.tableblock{border-width:1px 0 0}
275: table.grid-all>*>tr>.tableblock:last-child,table.grid-cols>*>tr>.tableblock:last-child{border-right-width:0}
276: table.grid-all>tbody>tr:last-child>.tableblock,table.grid-all>thead:last-child>tr>.tableblock,table.grid-rows>tbody>tr:last-child>.tableblock,table.grid-rows>thead:last-child>tr>.tableblock{border-bottom-width:0}
277: table.frame-all{border-width:1px}
278: table.frame-sides{border-width:0 1px}
279: table.frame-topbot,table.frame-ends{border-width:1px 0}
280: table.stripes-all tr,table.stripes-odd tr:nth-of-type(odd),table.stripes-even tr:nth-of-type(even),table.stripes-hover tr:hover{background:#f8f8f7}
281: th.halign-left,td.halign-left{text-align:left}
282: th.halign-right,td.halign-right{text-align:right}
283: th.halign-center,td.halign-center{text-align:center}
284: th.valign-top,td.valign-top{vertical-align:top}
285: th.valign-bottom,td.valign-bottom{vertical-align:bottom}
286: th.valign-middle,td.valign-middle{vertical-align:middle}
287: table thead th,table tfoot th{font-weight:bold}
288: tbody tr th{display:table-cell;line-height:1.6;background:#f7f8f7}
289: tbody tr th,tbody tr th p,tfoot tr th,tfoot tr th p{color:rgba(0,0,0,.8);font-weight:bold}
290: p.tableblock>code:only-child{background:none;padding:0}
291: p.tableblock{font-size:1em}
292: ol{margin-left:1.75em}
293: ul li ol{margin-left:1.5em}
294: dl dd{margin-left:1.125em}
295: dl dd:last-child,dl dd:last-child>:last-child{margin-bottom:0}
296: ol>li p,ul>li p,ul dd,ol dd,.olist .olist,.ulist .ulist,.ulist .olist,.olist .ulist{margin-bottom:.625em}
297: ul.checklist,ul.none,ol.none,ul.no-bullet,ol.no-bullet,ol.unnumbered,ul.unstyled,ol.unstyled{list-style-type:none}
298: ul.no-bullet,ol.no-bullet,ol.unnumbered{margin-left:.625em}
299: ul.unstyled,ol.unstyled{margin-left:0}
300: ul.checklist{margin-left:.625em}
301: ul.checklist li>p:first-child>.fa-square-o:first-child,ul.checklist li>p:first-child>.fa-check-square-o:first-child{width:1.25em;font-size:.8em;position:relative;bottom:.125em}
302: ul.checklist li>p:first-child>input[type="checkbox"]:first-child{margin-right:.25em}
303: ul.inline{display:-ms-flexbox;display:-webkit-box;display:flex;-ms-flex-flow:row wrap;-webkit-flex-flow:row wrap;flex-flow:row wrap;list-style:none;margin:0 0 .625em -1.25em}
304: ul.inline>li{margin-left:1.25em}
305: .unstyled dl dt{font-weight:400;font-style:normal}
306: ol.arabic{list-style-type:decimal}
307: ol.decimal{list-style-type:decimal-leading-zero}
308: ol.loweralpha{list-style-type:lower-alpha}
309: ol.upperalpha{list-style-type:upper-alpha}
310: ol.lowerroman{list-style-type:lower-roman}
311: ol.upperroman{list-style-type:upper-roman}
312: ol.lowergreek{list-style-type:lower-greek}
313: .hdlist>table,.colist>table{border:0;background:none}
314: .hdlist>table>tbody>tr,.colist>table>tbody>tr{background:none}
315: td.hdlist1,td.hdlist2{vertical-align:top;padding:0 .625em}
316: td.hdlist1{font-weight:bold;padding-bottom:1.25em}
317: .literalblock+.colist,.listingblock+.colist{margin-top:-.5em}
318: .colist td:not([class]):first-child{padding:.4em .75em 0;line-height:1;vertical-align:top}
319: .colist td:not([class]):first-child img{max-width:none}
320: .colist td:not([class]):last-child{padding:.25em 0}
321: .thumb,.th{line-height:0;display:inline-block;border:solid 4px #fff;-webkit-box-shadow:0 0 0 1px #ddd;box-shadow:0 0 0 1px #ddd}
322: .imageblock.left{margin:.25em .625em 1.25em 0}
323: .imageblock.right{margin:.25em 0 1.25em .625em}
324: .imageblock>.title{margin-bottom:0}
325: .imageblock.thumb,.imageblock.th{border-width:6px}
326: .imageblock.thumb>.title,.imageblock.th>.title{padding:0 .125em}
327: .image.left,.image.right{margin-top:.25em;margin-bottom:.25em;display:inline-block;line-height:0}
328: .image.left{margin-right:.625em}
329: .image.right{margin-left:.625em}
330: a.image{text-decoration:none;display:inline-block}
331: a.image object{pointer-events:none}
332: sup.footnote,sup.footnoteref{font-size:.875em;position:static;vertical-align:super}
333: sup.footnote a,sup.footnoteref a{text-decoration:none}
334: sup.footnote a:active,sup.footnoteref a:active{text-decoration:underline}
335: #footnotes{padding-top:.75em;padding-bottom:.75em;margin-bottom:.625em}
336: #footnotes hr{width:20%;min-width:6.25em;margin:-.25em 0 .75em;border-width:1px 0 0}
337: #footnotes .footnote{padding:0 .375em 0 .225em;line-height:1.3334;font-size:.875em;margin-left:1.2em;margin-bottom:.2em}
338: #footnotes .footnote a:first-of-type{font-weight:bold;text-decoration:none;margin-left:-1.05em}
339: #footnotes .footnote:last-of-type{margin-bottom:0}
340: #content #footnotes{margin-top:-.625em;margin-bottom:0;padding:.75em 0}
341: .gist .file-data>table{border:0;background:#fff;width:100%;margin-bottom:0}
342: .gist .file-data>table td.line-data{width:99%}
343: div.unbreakable{page-break-inside:avoid}
344: .big{font-size:larger}
345: .small{font-size:smaller}
346: .underline{text-decoration:underline}
347: .overline{text-decoration:overline}
348: .line-through{text-decoration:line-through}
349: .aqua{color:#00bfbf}
350: .aqua-background{background:#00fafa}
351: .black{color:#000}
352: .black-background{background:#000}
353: .blue{color:#0000bf}
354: .blue-background{background:#0000fa}
355: .fuchsia{color:#bf00bf}
356: .fuchsia-background{background:#fa00fa}
357: .gray{color:#606060}
358: .gray-background{background:#7d7d7d}
359: .green{color:#006000}
360: .green-background{background:#007d00}
361: .lime{color:#00bf00}
362: .lime-background{background:#00fa00}
363: .maroon{color:#600000}
364: .maroon-background{background:#7d0000}
365: .navy{color:#000060}
366: .navy-background{background:#00007d}
367: .olive{color:#606000}
368: .olive-background{background:#7d7d00}
369: .purple{color:#600060}
370: .purple-background{background:#7d007d}
371: .red{color:#bf0000}
372: .red-background{background:#fa0000}
373: .silver{color:#909090}
374: .silver-background{background:#bcbcbc}
375: .teal{color:#006060}
376: .teal-background{background:#007d7d}
377: .white{color:#bfbfbf}
378: .white-background{background:#fafafa}
379: .yellow{color:#bfbf00}
380: .yellow-background{background:#fafa00}
381: span.icon>.fa{cursor:default}
382: a span.icon>.fa{cursor:inherit}
383: .admonitionblock td.icon [class^="fa icon-"]{font-size:2.5em;text-shadow:1px 1px 2px rgba(0,0,0,.5);cursor:default}
384: .admonitionblock td.icon .icon-note::before{content:"\f05a";color:#19407c}
385: .admonitionblock td.icon .icon-tip::before{content:"\f0eb";text-shadow:1px 1px 2px rgba(155,155,0,.8);color:#111}
386: .admonitionblock td.icon .icon-warning::before{content:"\f071";color:#bf6900}
387: .admonitionblock td.icon .icon-caution::before{content:"\f06d";color:#bf3400}
388: .admonitionblock td.icon .icon-important::before{content:"\f06a";color:#bf0000}
389: .conum[data-value]{display:inline-block;color:#fff!important;background:rgba(0,0,0,.8);-webkit-border-radius:100px;border-radius:100px;text-align:center;font-size:.75em;width:1.67em;height:1.67em;line-height:1.67em;font-family:"Open Sans","DejaVu Sans",sans-serif;font-style:normal;font-weight:bold}
390: .conum[data-value] *{color:#fff!important}
391: .conum[data-value]+b{display:none}
392: .conum[data-value]::after{content:attr(data-value)}
393: pre .conum[data-value]{position:relative;top:-.125em}
394: b.conum *{color:inherit!important}
395: .conum:not([data-value]):empty{display:none}
396: dt,th.tableblock,td.content,div.footnote{text-rendering:optimizeLegibility}
397: h1,h2,p,td.content,span.alt{letter-spacing:-.01em}
398: p strong,td.content strong,div.footnote strong{letter-spacing:-.005em}
399: p,blockquote,dt,td.content,span.alt{font-size:1.0625rem}
400: p{margin-bottom:1.25rem}
401: .sidebarblock p,.sidebarblock dt,.sidebarblock td.content,p.tableblock{font-size:1em}
402: .exampleblock>.content{background:#fffef7;border-color:#e0e0dc;-webkit-box-shadow:0 1px 4px #e0e0dc;box-shadow:0 1px 4px #e0e0dc}
403: .print-only{display:none!important}
404: @page{margin:1.25cm .75cm}
405: @media print{*{-webkit-box-shadow:none!important;box-shadow:none!important;text-shadow:none!important}
406: html{font-size:80%}
407: a{color:inherit!important;text-decoration:underline!important}
408: a.bare,a[href^="#"],a[href^="mailto:"]{text-decoration:none!important}
409: a[href^="http:"]:not(.bare)::after,a[href^="https:"]:not(.bare)::after{content:"(" attr(href) ")";display:inline-block;font-size:.875em;padding-left:.25em}
410: abbr[title]::after{content:" (" attr(title) ")"}
411: pre,blockquote,tr,img,object,svg{page-break-inside:avoid}
412: thead{display:table-header-group}
413: svg{max-width:100%}
414: p,blockquote,dt,td.content{font-size:1em;orphans:3;widows:3}
415: h2,h3,#toctitle,.sidebarblock>.content>.title{page-break-after:avoid}
416: #toc,.sidebarblock,.exampleblock>.content{background:none!important}
417: #toc{border-bottom:1px solid #dddddf!important;padding-bottom:0!important}
418: body.book #header{text-align:center}
419: body.book #header>h1:first-child{border:0!important;margin:2.5em 0 1em}
420: body.book #header .details{border:0!important;display:block;padding:0!important}
421: body.book #header .details span:first-child{margin-left:0!important}
422: body.book #header .details br{display:block}
423: body.book #header .details br+span::before{content:none!important}
424: body.book #toc{border:0!important;text-align:left!important;padding:0!important;margin:0!important}
425: body.book #toc,body.book #preamble,body.book h1.sect0,body.book .sect1>h2{page-break-before:always}
426: .listingblock code[data-lang]::before{display:block}
427: #footer{padding:0 .9375em}
428: .hide-on-print{display:none!important}
429: .print-only{display:block!important}
430: .hide-for-print{display:none!important}
431: .show-for-print{display:inherit!important}}
432: @media print,amzn-kf8{#header>h1:first-child{margin-top:1.25rem}
433: .sect1{padding:0!important}
434: .sect1+.sect1{border:0}
435: #footer{background:none}
436: #footer-text{color:rgba(0,0,0,.6);font-size:.9em}}
437: @media amzn-kf8{#header,#content,#footnotes,#footer{padding:0}}
438: </style>
439: </head>
440: <body class="article">
441: <div id="header">
442: <h1>kitchensink: Assortment of technologies including Arquillian</h1>
443: <div class="details">
444: <span id="author" class="author">Pete Muir</span><br>
445: </div>
446: </div>
447: <div id="content">
448: <div id="preamble">
449: <div class="sectionbody">
450: <div class="quoteblock abstract">
451: <blockquote>
452: The <code>kitchensink</code> quickstart demonstrates a Jakarta EE 10 web-enabled database application using JSF, CDI, EJB, JPA, and Bean Validation.
453: </blockquote>
454: </div>
455: </div>
456: </div>
457: <div class="sect1">
458: <h2 id="_what_is_it">What is it?</h2>
459: <div class="sectionbody">
460: <div class="paragraph">
461: <p>The <code>kitchensink</code> quickstart is a deployable Maven 3 project designed to help you get your foot in the door developing with Jakarta EE 10 on Red Hat JBoss Enterprise Application Platform.</p>
462: </div>
463: <div class="paragraph">
464: <p>It demonstrates how to create a compliant Jakarta EE 10 application using JSF, CDI, JAX-RS, EJB, JPA, and Bean Validation. It also includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.</p>
465: </div>
466: </div>
467: </div>
468: <div class="sect1">
469: <h2 id="considerations_for_use_in_a_production_environment">Considerations for Use in a Production Environment</h2>
470: <div class="sectionbody">
471: <div class="dlist">
472: <dl>
473: <dt class="hdlist1">H2 Database</dt>
474: <dd>
475: <p>This quickstart uses the H2 database included with Red Hat JBoss Enterprise Application Platform 8.0. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment.</p>
476: </dd>
477: <dt class="hdlist1">Datasource Configuration File</dt>
478: <dd>
479: <p>This quickstart uses a <code>*-ds.xml</code> datasource configuration file for convenience and ease of database configuration. These files are deprecated in JBoss EAP and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the <a href="https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/8.0/html-single/configuration_guide/"><em>Configuration Guide</em></a>.</p>
480: </dd>
481: </dl>
482: </div>
483: </div>
484: </div>
485: <div class="sect1">
486: <h2 id="system_requirements">System Requirements</h2>
487: <div class="sectionbody">
488: <div class="paragraph">
489: <p>The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 8.0 or later.</p>
490: </div>
491: <div class="paragraph">
492: <p>All you need to build this project is Java 11.0 (Java SDK 11) or later and Maven 3.6.0 or later. See <a href="https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP.adoc#configure_maven_to_build_and_deploy_the_quickstarts">Configure Maven to Build and Deploy the Quickstarts</a> to make sure you are configured correctly for testing the quickstarts.</p>
493: </div>
494: </div>
495: </div>
496: <div class="sect1">
497: <h2 id="use_of_jboss_home_name">Use of the EAP_HOME and QUICKSTART_HOME Variables</h2>
498: <div class="sectionbody">
499: <div class="paragraph">
500: <p>In the following instructions, replace <code><em>EAP_HOME</em></code> with the actual path to your JBoss EAP installation. The installation path is described in detail here: <a href="https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP_HOME.adoc#use_of_product_home_and_jboss_home_variables">Use of <em>EAP_HOME</em> and <em>JBOSS_HOME</em> Variables</a>.</p>
501: </div>
502: <div class="paragraph">
503: <p>When you see the replaceable variable <em>QUICKSTART_HOME</em>, replace it with the path to the root directory of all of the quickstarts.</p>
504: </div>
505: </div>
506: </div>
507: <div class="sect1">
508: <h2 id="build_and_run_the_quickstart_with_server_dist">Building and running the quickstart application with a JBoss EAP server distribution</h2>
509: <div class="sectionbody">
510: <div class="sect2">
511: <h3 id="start_the_eap_standalone_server">Start the JBoss EAP Standalone Server</h3>
512: <div class="olist arabic">
513: <ol class="arabic">
514: <li>
515: <p>Open a terminal and navigate to the root of the JBoss EAP directory.</p>
516: </li>
517: <li>
518: <p>Start the JBoss EAP server with the default profile by typing the following command.</p>
519: <div class="listingblock">
520: <div class="content">
521: <pre class="highlight nowrap"><code>$ <em>EAP_HOME</em>/bin/standalone.sh </code></pre>
522: </div>
523: </div>
524: <div class="admonitionblock note">
525: <table>
526: <tr>
527: <td class="icon">
528: <div class="title">Note</div>
529: </td>
530: <td class="content">
531: For Windows, use the <code><em>EAP_HOME</em>\bin\standalone.bat</code> script.
532: </td>
533: </tr>
534: </table>
535: </div>
536: </li>
537: </ol>
538: </div>
539: </div>
540: <div class="sect2">
541: <h3 id="build_and_deploy_the_quickstart">Build and Deploy the Quickstart</h3>
542: <div class="olist arabic">
543: <ol class="arabic">
544: <li>
545: <p>Make sure you <a href="#start_the_eap_standalone_server">start the JBoss EAP server</a> as described above.</p>
546: </li>
547: <li>
548: <p>Open a terminal and navigate to the root directory of this quickstart.</p>
549: </li>
550: <li>
551: <p>Type the following command to build the quickstart.</p>
552: <div class="listingblock">
553: <div class="content">
554: <pre class="highlight nowrap"><code>$ mvn clean package</code></pre>
555: </div>
556: </div>
557: </li>
558: <li>
559: <p>Type the following command to deploy the quickstart.</p>
560: <div class="listingblock">
561: <div class="content">
562: <pre class="highlight nowrap"><code>$ mvn wildfly:deploy</code></pre>
563: </div>
564: </div>
565: </li>
566: </ol>
567: </div>
568: <div class="paragraph">
569: <p>This deploys the <code>kitchensink/target/kitchensink.war</code> to the running instance of the server.</p>
570: </div>
571: <div class="paragraph">
572: <p>You should see a message in the server log indicating that the archive deployed successfully.</p>
573: </div>
574: </div>
575: <div class="sect2">
576: <h3 id="_access_the_application">Access the Application</h3>
577: <div class="paragraph">
578: <p>The application will be running at the following URL: <a href="http://localhost:8080/kitchensink/" class="bare">http://localhost:8080/kitchensink/</a>.</p>
579: </div>
580: </div>
581: <div class="sect2">
582: <h3 id="_server_log_expected_warnings_and_errors">Server Log: Expected Warnings and Errors</h3>
583: <div class="paragraph">
584: <p>You will see the following warnings in the server log. You can ignore these warnings.</p>
585: </div>
586: <div class="listingblock">
587: <div class="content">
588: <pre class="highlight nowrap"><code>WFLYJCA0091: -ds.xml file deployments are deprecated. Support may be removed in a future version.</code></pre>
589: </div>
590: </div>
591: </div>
592: <div class="sect2">
593: <h3 id="run_the_arquillian_integration_tests_with_server_distribution">Run the Arquillian Integration Tests</h3>
594: <div class="paragraph">
595: <p>This quickstart includes Arquillian integration tests. They are located under the  <code>src/test/</code> directory. The integration tests verify that the quickstart runs correctly when deployed on the server.</p>
596: </div>
597: <div class="paragraph">
598: <p>Follow these steps to run the integration tests.</p>
599: </div>
600: <div class="olist arabic">
601: <ol class="arabic">
602: <li>
603: <p>Make sure you start the JBoss EAP server, as previously described.</p>
604: </li>
605: <li>
606: <p>Make sure you build and deploy the quickstart, as previously described.</p>
607: </li>
608: <li>
609: <p>Type the following command to run the <code>verify</code> goal with the <code>arq-remote</code> profile activated.</p>
610: <div class="listingblock">
611: <div class="content">
612: <pre class="highlight nowrap"><code>$ mvn verify -Parq-remote</code></pre>
613: </div>
614: </div>
615: </li>
616: </ol>
617: </div>
618: <div class="admonitionblock note">
619: <table>
620: <tr>
621: <td class="icon">
622: <div class="title">Note</div>
623: </td>
624: <td class="content">
625: <div class="paragraph">
626: <p>You may also use the environment variable <code>SERVER_HOST</code> or the system property <code>server.host</code> to define the target host of the tests.</p>
627: </div>
628: </td>
629: </tr>
630: </table>
631: </div>
632: </div>
633: <div class="sect2">
634: <h3 id="undeploy_the_quickstart">Undeploy the Quickstart</h3>
635: <div class="paragraph">
636: <p>When you are finished testing the quickstart, follow these steps to undeploy the archive.</p>
637: </div>
638: <div class="olist arabic">
639: <ol class="arabic">
640: <li>
641: <p>Make sure you <a href="#start_the_eap_standalone_server">start the JBoss EAP server</a> as described above.</p>
642: </li>
643: <li>
644: <p>Open a terminal and navigate to the root directory of this quickstart.</p>
645: </li>
646: <li>
647: <p>Type this command to undeploy the archive:</p>
648: <div class="listingblock">
649: <div class="content">
650: <pre class="highlight nowrap"><code>$ mvn wildfly:undeploy</code></pre>
651: </div>
652: </div>
653: </li>
654: </ol>
655: </div>
656: </div>
657: </div>
658: </div>
659: <div class="sect1">
660: <h2 id="build_and_run_the_quickstart_on_openshift">Building and running the quickstart application with OpenShift</h2>
661: <div class="sectionbody">
662: <div class="sect3">
663: <h4 id="build-the-quickstart-for-openshift">Build the JBoss EAP Source-to-Image (S2I) Quickstart to OpenShift with Helm Charts</h4>
664: <div class="paragraph">
665: <p>On OpenShift, the S2I build with Apache Maven will use an <code>openshift</code> profile used to provision a JBoss EAP server to deploy and run the quickstart in OpenShift environment.
666: You can activate the Maven profile named <code>openshift</code> when building the quickstart:</p>
667: </div>
668: <div class="listingblock">
669: <div class="content">
670: <pre class="highlight nowrap"><code>$ mvn clean package -Popenshift</code></pre>
671: </div>
672: </div>
673: <div class="paragraph">
674: <p>The provisioned JBoss EAP server for OpenShift, with the quickstart deployed, can then be found in the <code>target/server</code> directory, and its usage is similar to a standard server distribution.
675: You may note that it uses the cloud feature pack which enables a configuration tuned for OpenShift environment.</p>
676: </div>
677: <div class="paragraph">
678: <p>The server provisioning functionality is provided by the EAP Maven Plugin, and you may find its configuration in the quickstart <code>pom.xml</code>:</p>
679: </div>
680: <div class="listingblock">
681: <div class="content">
682: <pre class="highlight"><code class="language-xml" data-lang="xml">        &lt;profile&gt;
683:             &lt;id&gt;openshift&lt;/id&gt;
684:             &lt;build&gt;
685:                 &lt;plugins&gt;
686:                     &lt;plugin&gt;
687:                         &lt;groupId&gt;org.jboss.eap.plugins&lt;/groupId&gt;
688:                         &lt;artifactId&gt;eap-maven-plugin&lt;/artifactId&gt;
689:                         &lt;version&gt;${version.eap.maven.plugin}&lt;/version&gt;
690:                         &lt;configuration&gt;
691:                             &lt;feature-packs&gt;
692:                                 &lt;feature-pack&gt;
693:                                     &lt;location&gt;org.jboss.eap:wildfly-ee-galleon-pack&lt;/location&gt;
694:                                 &lt;/feature-pack&gt;
695:                                 &lt;feature-pack&gt;
696:                                     &lt;location&gt;org.jboss.eap.cloud:eap-cloud-galleon-pack&lt;/location&gt;
697:                                 &lt;/feature-pack&gt;
698:                             &lt;/feature-packs&gt;
699:                             &lt;layers&gt;
700:                                 &lt;layer&gt;cloud-server&lt;/layer&gt;
701:                             &lt;/layers&gt;
702:                             &lt;filename&gt;ROOT.war&lt;/filename&gt;
703:                         &lt;/configuration&gt;
704:                         &lt;executions&gt;
705:                             &lt;execution&gt;
706:                                 &lt;goals&gt;
707:                                     &lt;goal&gt;package&lt;/goal&gt;
708:                                 &lt;/goals&gt;
709:                             &lt;/execution&gt;
710:                         &lt;/executions&gt;
711:                     &lt;/plugin&gt;
712:                 &lt;/plugins&gt;
713:             &lt;/build&gt;
714:         &lt;/profile&gt;</code></pre>
715: </div>
716: </div>
717: <div class="admonitionblock note">
718: <table>
719: <tr>
720: <td class="icon">
721: <div class="title">Note</div>
722: </td>
723: <td class="content">
724: <div class="paragraph">
725: <p>Since the plugin configuration above deploys quickstart on root web context of the provisioned server, the URL to access the application should not have the <code>/kitchensink</code> path segment after <code>HOST:PORT</code>.</p>
726: </div>
727: </td>
728: </tr>
729: </table>
730: </div>
731: </div>
732: <div class="sect2">
733: <h3 id="getting_started_with_helm">Getting Started with JBoss EAP for OpenShift and Helm Charts</h3>
734: <div class="paragraph">
735: <p>This section contains the basic instructions to build and deploy this quickstart to JBoss EAP for OpenShift or JBoss EAP for OpenShift Online using Helm Charts.</p>
736: </div>
737: <div class="sect3">
738: <h4 id="prerequisites_helm_openshift">Prerequisites</h4>
739: <div class="ulist">
740: <ul>
741: <li>
742: <p>You must be logged in OpenShift and have an <code>oc</code> client to connect to OpenShift</p>
743: </li>
744: <li>
745: <p><a href="https://helm.sh">Helm</a> must be installed to deploy the backend on OpenShift.</p>
746: </li>
747: </ul>
748: </div>
749: <div class="paragraph">
750: <p>Once you have installed Helm, you need to add the repository that provides Helm Charts for JBoss EAP.</p>
751: </div>
752: <div class="listingblock">
753: <div class="content">
754: <pre class="highlight nowrap"><code>$ helm repo add jboss-eap https://jbossas.github.io/eap-charts/
755: "jboss-eap" has been added to your repositories
756: $ helm search repo jboss-eap
757: NAME                    CHART VERSION   APP VERSION     DESCRIPTION
758: jboss-eap/eap8         ...             ...             A Helm chart to build and deploy EAP 8.0 applications</code></pre>
759: </div>
760: </div>
761: </div>
762: <div class="sect3">
763: <h4 id="deploy_helm">Deploy the JBoss EAP Source-to-Image (S2I) Quickstart to OpenShift with Helm Charts</h4>
764: <div class="paragraph">
765: <p>Log in to your OpenShift instance using the <code>oc login</code> command.
766: The backend will be built and deployed on OpenShift with a Helm Chart for JBoss EAP.</p>
767: </div>
768: <div class="paragraph">
769: <p>Navigate to the root directory of this quickstart and run the following command:</p>
770: </div>
771: <div class="listingblock">
772: <div class="content">
773: <pre class="highlight nowrap"><code>$ helm install kitchensink -f charts/helm.yaml jboss-eap/eap8
774: NAME: kitchensink
775: ...
776: STATUS: deployed
777: REVISION: 1</code></pre>
778: </div>
779: </div>
780: <div class="paragraph">
781: <p>The Helm Chart for this quickstart contains all the information to build an image from the source code using S2I on Java 17:</p>
782: </div>
783: <div class="listingblock">
784: <div class="content">
785: <pre class="highlight nowrap"><code>build:
786:   uri: https://github.com/jboss-developer/jboss-eap-quickstarts.git
787:   ref: 8.0.x
788:   contextDir: kitchensink
789: deploy:
790:   replicas: 1</code></pre>
791: </div>
792: </div>
793: <div class="paragraph">
794: <p>This will create a new deployment on OpenShift and deploy the application.</p>
795: </div>
796: <div class="paragraph">
797: <p>If you want to see all the configuration elements to customize your deployment you can use the following command:</p>
798: </div>
799: <div class="listingblock">
800: <div class="content">
801: <pre class="highlight nowrap"><code>$ helm show readme jboss-eap/eap8</code></pre>
802: </div>
803: </div>
804: <div class="paragraph">
805: <p>Let’s wait for the application to be built and deployed:</p>
806: </div>
807: <div class="listingblock">
808: <div class="content">
809: <pre class="highlight nowrap"><code>$ oc get deployment kitchensink -w
810: NAME         DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
811: kitchensink   1         1         1            0           12s
812: ...
813: kitchensink   1         1         1            1           2m</code></pre>
814: </div>
815: </div>
816: <div class="paragraph">
817: <p>Get the URL of the route to the deployment.</p>
818: </div>
819: <div class="listingblock">
820: <div class="content">
821: <pre class="highlight nowrap"><code>$ oc get route kitchensink -o jsonpath="{.spec.host}"</code></pre>
822: </div>
823: </div>
824: <div class="paragraph">
825: <p>Access the application in your web browser using the displayed URL.</p>
826: </div>
827: <div class="admonitionblock note">
828: <table>
829: <tr>
830: <td class="icon">
831: <div class="title">Note</div>
832: </td>
833: <td class="content">
834: <div class="paragraph">
835: <p>The Maven profile named <code>openshift</code> is used by the Helm chart to provision the server with the quickstart deployed on the root web context, and thus the application should be accessed with the URL without the <code>/kitchensink</code> path segment after <code>HOST:PORT</code>.</p>
836: </div>
837: </td>
838: </tr>
839: </table>
840: </div>
841: </div>
842: <div class="sect3">
843: <h4 id="undeploy_helm">Undeploy the JBoss EAP Source-to-Image (S2I) Quickstart from OpenShift with Helm Charts</h4>
844: <div class="listingblock">
845: <div class="content">
846: <pre class="highlight nowrap"><code>$ helm uninstall kitchensink</code></pre>
847: </div>
848: </div>
849: </div>
850: </div>
851: <div class="sect2">
852: <h3 id="run_the_arquillian_integration_tests_with_openshift">Run the Arquillian Integration Tests with OpenShift</h3>
853: <div class="paragraph">
854: <p>This quickstart includes Arquillian integration tests. They are located under the  <code>src/test/</code> directory. The integration tests verify that the quickstart runs correctly when deployed on the server.</p>
855: </div>
856: <div class="admonitionblock note">
857: <table>
858: <tr>
859: <td class="icon">
860: <div class="title">Note</div>
861: </td>
862: <td class="content">
863: <div class="paragraph">
864: <p>The Arquillian integration tests expect a deployed application, so make sure you have deployed the quickstart on OpenShift before you begin.</p>
865: </div>
866: </td>
867: </tr>
868: </table>
869: </div>
870: <div class="paragraph">
871: <p>Run the integration tests using the following command to run the <code>verify</code> goal with the <code>arq-remote</code> profile activated and the proper URL:</p>
872: </div>
873: <div class="listingblock">
874: <div class="content">
875: <pre class="highlight nowrap"><code>$ mvn clean verify -Parq-remote -Dserver.host=https://$(oc get route kitchensink --template='{{ .spec.host }}')</code></pre>
876: </div>
877: </div>
878: <div class="admonitionblock note">
879: <table>
880: <tr>
881: <td class="icon">
882: <div class="title">Note</div>
883: </td>
884: <td class="content">
885: <div class="paragraph">
886: <p>The tests are using SSL to connect to the quickstart running on OpenShift. So you need the certificates to be trusted by the machine the tests are run from.</p>
887: </div>
888: </td>
889: </tr>
890: </table>
891: </div>
892: </div>
893: </div>
894: </div>
895: </div>
896: <div id="footer">
897: <div id="footer-text">
898: Last updated 2024-01-19 11:44:45 UTC
899: </div>
900: </div>
901: </body>
902: </html>
```

## File: modern/placeholder.txt
```
1: placeholder file for git
```

## File: readme.md
```markdown
1: This is the modernisation challenge git repository with the following top level folder-structure:
2: 
3: - legacy (contains the legacy code)
4: - modern (this is the target folder for the modernised quarkus project)
5: - workflow (here I keep migration blueprints, prompts, repomix files, etc.)
```
