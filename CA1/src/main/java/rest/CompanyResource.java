package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Company;
import entity.Phone;
import facade.CompanyMapper;
import facade.Facade;
import facade.PersonMapper;
import java.util.List;
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

@Path("company")
public class CompanyResource {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
    
    Facade facade = new Facade(emf, new PersonMapper(), new CompanyMapper());
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    @Path("complete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Company> list = facade.getAllCompanies();
        return gson.toJson(list);
    }
    
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id) {
        Company company = facade.getCompany(id);
        return gson.toJson(company);
    }
    
    @Path("contactinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo() {
        List<Company> cl = facade.getAllCompanies();
        JsonArray ja = new JsonArray();

        for (Company company : cl) {
            JsonObject jo = new JsonObject();
            jo.addProperty("Email", company.getEmail());
            
            JsonArray phoneNumbers = new JsonArray();
            List<Phone> phoneL = company.getPhones();
            for (int i = 0; i < phoneL.size(); i++) {
                JsonObject phoneNumber = new JsonObject();
                phoneNumber.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
                phoneNumbers.add(phoneNumber);
            }
            
            jo.add("phoneNumbers", phoneNumbers);
            ja.add(jo);
        }
        return gson.toJson(ja);
    }

    @Path("contactinfo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo(@PathParam("id") int id) {
        Company c = facade.getCompany(id);
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();
        
        jo.addProperty("Email", c.getEmail());

        JsonArray phoneNumbers = new JsonArray();
        List<Phone> phoneL = c.getPhones();
        for (int i = 0; i < phoneL.size(); i++) {
            JsonObject phoneNumber = new JsonObject();
            phoneNumber.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
            phoneNumbers.add(phoneNumber);
        }
        
        jo.add("phoneNumbers", phoneNumbers);
        ja.add(jo);

        return gson.toJson(ja);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
    }
}
