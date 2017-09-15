package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import facade.CompanyMapper;
import facade.Facade;
import facade.PersonMapper;
import java.util.List;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

    Facade facade = new Facade(emf, new PersonMapper(), new CompanyMapper());
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
        JsonArray ja = personListToJson(pl);
        return gs.toJson(ja);
    }

    @Path("complete/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id) {
        Person person = facade.getPerson(id);
        JsonObject jo = personToJson(person);
        JsonObject[] temp = new JsonObject[1];
        temp[0] = jo;
        return gs.toJson(temp);
    }

    @Path("phonenumber/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhoneNumber(@PathParam("number") int number) {
        Person person = facade.getPersonByPhoneNumber(number);
        JsonObject jo = personToJson(person);
        JsonObject[] temp = new JsonObject[1];
        temp[0] = jo;
        return gs.toJson(temp);
    }

    @Path("hobby/{hobby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByHobby(@PathParam("hobby") String hobby) {
        List<Person> pl = facade.getPersonsByHobbies(hobby);
        JsonArray ja = personListToJson(pl);
        return gs.toJson(ja);
    }

    @Path("hobby/count/{hobby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonCountByHobby(@PathParam("hobby") String hobby) {
        List<Person> pl = facade.getPersonsByHobbies(hobby);
        return "{" + pl.size() + "}";
    }

    @Path("zipcode/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByZipCode(@PathParam("number") int number) {
        List<Person> pl = facade.getPersonsByZipcode(number);
        JsonArray ja = personListToJson(pl);
        return gs.toJson(ja);
    }

    @Path("contactinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo() {
        List<Person> pl = facade.getAllPersons();
        JsonArray ja = new JsonArray();
        for (Person person : pl) {
            JsonObject jo = new JsonObject();
            jo.addProperty("Email", person.getEmail());
            List<Phone> phoneL = person.getPhones();

            JsonArray ja2 = new JsonArray();
            System.out.println(phoneL.size());
            for (int i = 0; i < phoneL.size(); i++) {
                JsonObject jo2 = new JsonObject();
                jo2.addProperty("phoneNumber", phoneL.get(i).getNumber());
                ja2.add(jo2);
            }

            jo.add("phoneNumbers", ja2);
            ja.add(jo);
        }
        return gs.toJson(ja);
    }

    @Path("contactinfo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo(@PathParam("id") int id) {
        Person p = facade.getPerson(id);
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();

        jo.addProperty("Email", p.getEmail());

        JsonArray ja2 = new JsonArray();
        List<Phone> phoneL = p.getPhones();
        for (int i = 0; i < phoneL.size(); i++) {
            JsonObject jo2 = new JsonObject();
            jo2.addProperty("phoneNumber" + i, phoneL.get(i).getNumber());
            ja2.add(jo2);
        }

        jo.add("phoneNumbers", ja2);
        ja.add(jo);

        return gs.toJson(ja);
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     */
    @Path("insertperson")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        JsonObject jsonPerson = new JsonParser().parse(content).getAsJsonObject();
        String firstName = "";
        if (jsonPerson.has("firstname")) {
            firstName = jsonPerson.get("firstname").getAsString();
        }
        String lastName = "";
        if (jsonPerson.has("lastname")) {
            lastName = jsonPerson.get("lastname").getAsString();
        }
        String email = "";
        if (jsonPerson.has("email")) {
            email = jsonPerson.get("email").getAsString();
        }
        List<Phone> phoneNumbers = new ArrayList();
        if (jsonPerson.has("phones")) {
            System.out.println("has phones !!!!!!!!!!!!!");
            JsonArray jsonPhoneNumbers = jsonPerson.get("phones").getAsJsonArray();

            if (jsonPhoneNumbers != null) {
                int len = jsonPhoneNumbers.size();
                 for (int i = 0; i < len; i++) {
                    JsonObject jo = jsonPhoneNumbers.get(i).getAsJsonObject();
                    Phone phone = new Phone(jo.get("number").getAsInt(), jo.get("description").getAsString());
                    phoneNumbers.add(phone);
                }

            }

        }
        Address entityAddress = new Address();
        if (jsonPerson.has("address")) {
            JsonObject address = jsonPerson.getAsJsonObject("address");          //not sure this work ????
            entityAddress = new Address(address.get("street").getAsString(), new CityInfo(address.get("zip").getAsInt(), address.get("city").getAsString()), address.get("info").getAsString());
        }
        List<Hobby> entityHobbies = new ArrayList();
        if(jsonPerson.has("hobbies")){
            JsonArray jsonHobbies = jsonPerson.get("hobbies").getAsJsonArray();
            if(jsonHobbies != null){
                int length = jsonHobbies.size();
                
                for (int i = 0; i < length; i++) {
                    JsonObject jo = jsonHobbies.get(i).getAsJsonObject();
                    Hobby hobby = new Hobby(jo.get("name").getAsString(),jo.get("description").getAsString());
                    entityHobbies.add(hobby);
                    
                    
                }
            }
            
        }entityAddress,firstName,lastName, entityHobbies);
        facade.addPerson(person);
    }

    private JsonObject personToJson(Person person) {
        JsonObject jo = new JsonObject();
        jo.addProperty("id", person.getId());
        jo.addProperty("email", person.getEmail());
        jo.addProperty("firstname", person.getFirstName());
        jo.addProperty("lastname", person.getLastName());

        JsonObject addressObj = new JsonObject();
        addressObj.addProperty("id", person.getAddress().getId());
        addressObj.addProperty("street", person.getAddress().getStreet());
        addressObj.addProperty("city", person.getAddress().getCityInfo().getCity());
        addressObj.addProperty("zip", person.getAddress().getCityInfo().getZipCode());
        addressObj.addProperty("info", person.getAddress().getAdditionalInfo());

        jo.add("address", addressObj);

        JsonArray phones = new JsonArray();
        List<Phone> phoneL = person.getPhones();
        for (Phone phone : phoneL) {
            JsonObject phoneObj = new JsonObject();
            phoneObj.addProperty("id", phone.getId());
            phoneObj.addProperty("description", phone.getDescription());
            phoneObj.addProperty("number", phone.getNumber());
            phones.add(phoneObj);
        }
        jo.add("phones", phones);

        JsonArray hobbies = new JsonArray();
        List<Hobby> hobbyL = person.getHobbies();
        for (Hobby hobby : hobbyL) {
            JsonObject hobbyObj = new JsonObject();
            hobbyObj.addProperty("id", hobby.getId());
            hobbyObj.addProperty("name", hobby.getName());
            hobbyObj.addProperty("description", hobby.getDescription());
            hobbies.add(hobbyObj);
        }
        jo.add("hobbies", hobbies);
        return jo;
    }

    private JsonArray personListToJson(List<Person> pl) {
        JsonArray ja = new JsonArray();
        for (Person person : pl) {
            JsonObject jo = new JsonObject();
            jo.addProperty("id", person.getId());
            jo.addProperty("email", person.getEmail());
            jo.addProperty("firstname", person.getFirstName());
            jo.addProperty("lastname", person.getLastName());

            JsonObject addressObj = new JsonObject();
            addressObj.addProperty("id", person.getAddress().getId());
            addressObj.addProperty("street", person.getAddress().getStreet());
            addressObj.addProperty("city", person.getAddress().getCityInfo().getCity());
            addressObj.addProperty("zip", person.getAddress().getCityInfo().getZipCode());
            addressObj.addProperty("info", person.getAddress().getAdditionalInfo());

            jo.add("address", addressObj);

            JsonArray phones = new JsonArray();
            List<Phone> phoneL = person.getPhones();
            for (Phone phone : phoneL) {
                JsonObject phoneObj = new JsonObject();
                phoneObj.addProperty("id", phone.getId());
                phoneObj.addProperty("description", phone.getDescription());
                phoneObj.addProperty("number", phone.getNumber());
                phones.add(phoneObj);
            }
            jo.add("phones", phones);

            JsonArray hobbies = new JsonArray();
            List<Hobby> hobbyL = person.getHobbies();
            for (Hobby hobby : hobbyL) {
                JsonObject hobbyObj = new JsonObject();
                hobbyObj.addProperty("id", hobby.getId());
                hobbyObj.addProperty("name", hobby.getName());
                hobbyObj.addProperty("description", hobby.getDescription());
                hobbies.add(hobbyObj);
            }
            jo.add("hobbies", hobbies);
            ja.add(jo);
        }
        return ja;
    }
}
