package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import facade.CompanyMapper;
import facade.Facade;
import facade.PersonMapper;
import java.util.List;
import entity.Person;
import entity.Phone;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author
 */
@Path("person")
public class PersonResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");

    Facade facade = new Facade(new PersonMapper(emf), new CompanyMapper());
    Gson gs = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @Path("complete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Person> pl = facade.getAllPersons();
        return gs.toJson(pl);
    }

    @Path("complete/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id) {
        Person p = facade.getPerson(id);
        return gs.toJson(p);
    }

    @Path("conactinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo() {
        List<Person> pl = facade.getAllPersons();
        JsonArray ja = new JsonArray();
        for (Person person : pl) {
            JsonObject jo = new JsonObject();
            jo.addProperty("Email", person.getEmail());
            List<Phone> phoneL = person.getPhones();
            for (int i = 0; i < phoneL.size(); i++) {
                jo.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
            }
            ja.add(jo);
        }
        return gs.toJson(ja);
    }

    @Path("conactinfo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo(@PathParam("id") int id) {
        Person p = facade.getPerson(id);
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();
        
        jo.addProperty("Email", p.getEmail());

        List<Phone> phoneL = p.getPhones();
        for (int i = 0; i < phoneL.size(); i++) {
            jo.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
        }
        ja.add(jo);

        return gs.toJson(ja);
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
