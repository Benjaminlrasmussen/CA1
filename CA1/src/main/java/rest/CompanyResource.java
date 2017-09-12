package rest;

import com.google.gson.Gson;
import entity.Company;
import facade.CompanyMapper;
import facade.Facade;
import facade.PersonMapper;
import java.util.List;
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
@Path("company")
public class CompanyResource {
    
    Facade facade = new Facade(new PersonMapper(), new CompanyMapper());
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    public CompanyResource() {
        
    }

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
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
    }
}
