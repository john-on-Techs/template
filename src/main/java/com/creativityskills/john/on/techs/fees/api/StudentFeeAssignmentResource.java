package com.creativityskills.john.on.techs.fees.api;

import com.creativityskills.john.on.techs.fees.model.Fee;
import com.creativityskills.john.on.techs.fees.model.StudentFee;
import com.creativityskills.john.on.techs.fees.beans.StudentFeeBeanI;
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

@Path("/assign-student-fee")

@RequestScoped
public class StudentFeeAssignmentResource {
    @EJB
    private StudentFeeBeanI studentFeeBeanI;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFee(String feeString) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(false);
        customResponse.setData(null);
        customResponse.setMessage("Failed");
        try {
            if (feeString != null) {
                JsonObject jsonObject = new JsonParser().parse(feeString).getAsJsonObject();
                String registrationNumber = jsonObject.get("registrationNumber").getAsString();
                String fees = jsonObject.get("fee").getAsJsonObject().toString();
                Fee fee = new Gson().fromJson(fees, Fee.class);
                String studentString = new Util().getStudentByRegistrationNumber(registrationNumber);
                if (studentString != null) {
                    long studentId = new JsonParser()
                            .parse(studentString)
                            .getAsJsonObject()
                            .get("id")
                            .getAsLong();
                    StudentFee studentFee = new StudentFee();
                    studentFee.setFee(fee);
                    studentFee.setStudentId(studentId);
                    customResponse.setStatus(true);
                    customResponse.setMessage("Ok");
                    customResponse.setData(studentFeeBeanI.create(studentFee));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return Response.ok().entity(customResponse).build();
    }

}
