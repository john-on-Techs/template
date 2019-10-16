package com.creativityskills.john.on.techs.fees.api;

import com.creativityskills.john.on.techs.fees.model.Fee;
import com.creativityskills.john.on.techs.fees.model.Payment;
import com.creativityskills.john.on.techs.fees.beans.PaymentBeanI;
import com.creativityskills.john.on.techs.fees.beans.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;


@Path("/payments")
public class PaymentResource {
    @EJB
    private PaymentBeanI paymentBeanI;

    @POST
    @Path("/collect-fee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response collectFee(String json) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("Failed");
        customResponse.setData(null);
        customResponse.setStatus(true);
        try {
            if (json != null) {
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

                String registrationNumber = jsonObject.get("registrationNumber").getAsString();
                String studentJsonString = new Util().getStudentByRegistrationNumber(registrationNumber);
                if (studentJsonString != null) {
                    long studentId = new JsonParser().parse(studentJsonString).getAsJsonObject().get("id").getAsLong();
                    Fee fee = new Gson().fromJson(jsonObject.get("fee").getAsJsonObject().toString(), Fee.class);
                    Payment payment = new Payment();
                    payment.setStudentId(studentId);
                    payment.setFee(fee);
                    payment.setPaymentAmount(jsonObject.get("paymentAmount").getAsBigDecimal());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    payment.setPaymentDate(df.parse(jsonObject.get("paymentDate").getAsString()));
                    customResponse.setMessage("OK");
                    customResponse.setData(paymentBeanI.create(payment));
                    customResponse.setStatus(true);
                    return Response.ok().entity(customResponse).build();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPayments() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("Failed");
        customResponse.setData(null);
        customResponse.setStatus(true);
        try {
            customResponse.setMessage("OK");
            customResponse.setData(paymentBeanI.getPaymentsList());
            customResponse.setStatus(true);
            return Response.ok().entity(customResponse).build();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();

    }

    @GET
    @Path("/{paymentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentDetail(@PathParam("paymentId") long paymentId) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("Failed");
        customResponse.setData(null);
        customResponse.setStatus(true);
        try {
            customResponse.setMessage("OK");
            customResponse.setData(paymentBeanI.read(paymentId));
            customResponse.setStatus(true);
            return Response.ok().entity(customResponse).build();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();
    }
}
