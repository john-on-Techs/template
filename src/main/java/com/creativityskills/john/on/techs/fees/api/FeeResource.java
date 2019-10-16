package com.creativityskills.john.on.techs.fees.api;

import com.creativityskills.john.on.techs.fees.model.Fee;
import com.creativityskills.john.on.techs.fees.model.FeeType;
import com.creativityskills.john.on.techs.fees.model.Payment;
import com.creativityskills.john.on.techs.fees.beans.DueI;
import com.creativityskills.john.on.techs.fees.beans.FeeBeanI;
import com.creativityskills.john.on.techs.fees.beans.PaymentBeanI;
import com.creativityskills.john.on.techs.fees.beans.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/fee")

@RequestScoped
public class FeeResource {
    @EJB
    private FeeBeanI feeBeanI;
    @EJB
    private DueI dueI;
    @EJB
    private PaymentBeanI paymentBeanI;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add-fee")
    public Response createFee(String feeString) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(false);
        customResponse.setData(null);
        customResponse.setMessage("Failed");
        try {
            if (feeString != null) {
                Fee fee = new Gson().fromJson(feeString, Fee.class);
                customResponse.setData(feeBeanI.create(fee));
                customResponse.setMessage("OK");
                customResponse.setStatus(true);
                return Response.ok().entity(customResponse).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();


    }

    @POST
    @Path("/due")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeeDue(String json) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(false);
        customResponse.setData(null);
        customResponse.setMessage("Failed");
        try {
            JsonParser jsonParser = new JsonParser();
            if (json != null) {
                JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
                String registrationNumber = jsonObject.get("registrationNumber").getAsString();
                FeeType feeType = FeeType.valueOf(jsonObject.get("feeType").getAsString());
                customResponse.setData(dueI.getDueAmount(registrationNumber, feeType));
                customResponse.setMessage("OK");
                return Response.ok().entity(customResponse).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();

    }

    @POST
    @Path("/statement")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeeStatement(String json) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(false);
        customResponse.setData(null);
        customResponse.setMessage("Failed");
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            String registrationNumber = jsonObject.get("registrationNumber").getAsString();
            String studentString = new Util().getStudentByRegistrationNumber(registrationNumber);
            if (studentString != null) {
                List<Payment> payments = paymentBeanI.getPayments(registrationNumber);
                Map<String, String> studentPayments = new HashMap<>();
                studentPayments.put("student", studentString);
                studentPayments.put("payments", new Gson().toJson(payments));
                customResponse.setData(studentPayments);
                customResponse.setStatus(true);
                customResponse.setMessage("OK");
                return Response.ok().entity(customResponse).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }
        return Response.ok().entity(customResponse).build();


    }

}
