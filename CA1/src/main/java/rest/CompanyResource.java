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
public class CompanyResource
{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");

    Facade facade = new Facade(emf, new PersonMapper(), new CompanyMapper());
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    @Path("complete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson()
    {
        List<Company> list = facade.getAllCompanies();
        return gson.toJson(list);
    }

    @Path("complete/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id)
    {
        Company company = facade.getCompany(id);
        JsonObject jasonObject = companyToJson(company);  //LOOK AT THE NAMES JOACHIM !!! LOOOK!!!
        JsonObject[] jasonArray = new JsonObject[1];
        jasonArray[0] = jasonObject;
        return gson.toJson(jasonArray);
    }

    @Path("contactinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo()
    {
        List<Company> cl = facade.getAllCompanies();
        JsonArray ja = new JsonArray();

        for (Company company : cl)
            
        {
            JsonObject jo = new JsonObject();
            jo.addProperty("Email", company.getEmail());

            JsonArray phoneNumbers = new JsonArray();
            List<Phone> phoneL = company.getPhones();
            for (int i = 0; i < phoneL.size(); i++)
            {
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
    public String getContactinfo(@PathParam("id") int id)
    {
        Company c = facade.getCompany(id);
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();

        jo.addProperty("Email", c.getEmail());

        JsonArray phoneNumbers = new JsonArray();
        List<Phone> phoneL = c.getPhones();
        for (int i = 0; i < phoneL.size(); i++)
        {
            JsonObject phoneNumber = new JsonObject();
            phoneNumber.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
            phoneNumbers.add(phoneNumber);
        }

        jo.add("phoneNumbers", phoneNumbers);
        ja.add(jo);

        return gson.toJson(ja);
    }

    @Path("employees/{x}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompaniesWithMoreThanXEmployees(@PathParam("x") int x)
    {
        List<Company> companies = facade.getCompaniesWithMoreThanXEmployees(x);
        return gson.toJson(companies);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {

    }
    
    private JsonObject companyToJson(Company company)
    {
        JsonObject jo = new JsonObject();
        jo.addProperty("id", company.getId());
        jo.addProperty("email", company.getEmail());
        jo.addProperty("name", company.getName());
        jo.addProperty("cvr", company.getCvr());
        jo.addProperty("description", company.getDescription());
        jo.addProperty("marketvalue", company.getMarketValue());
        jo.addProperty("employees", company.getNumEmployees());

        JsonObject addressObj = new JsonObject();
        addressObj.addProperty("id", company.getAddress().getId());
        addressObj.addProperty("street", company.getAddress().getStreet());
        addressObj.addProperty("city", company.getAddress().getCityInfo().getCity());
        addressObj.addProperty("zip", company.getAddress().getCityInfo().getZipCode());
        addressObj.addProperty("info", company.getAddress().getAdditionalInfo());

        jo.add("address", addressObj);

        JsonArray phones = new JsonArray();
        List<Phone> phoneL = company.getPhones();
        for (Phone phone : phoneL)
        {
            JsonObject phoneObj = new JsonObject();
            phoneObj.addProperty("id", phone.getId());
            phoneObj.addProperty("description", phone.getDescription());
            phoneObj.addProperty("number", phone.getNumber());
            phones.add(phoneObj);
        }
        jo.add("phones", phones);

        return jo;
    }
    
}
