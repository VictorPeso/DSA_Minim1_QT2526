package edu.upc.dsa.services;


import edu.upc.dsa.BiblioManager;
import edu.upc.dsa.BiblioManagerImpl;
import io.swagger.annotations.Api;

import javax.ws.rs.*;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class TracksService {

    private BiblioManager tm;

    public TracksService() {
        this.tm = BiblioManagerImpl.getInstance();
        if (tm.size_munt()==0) {
            //this.tm.addTrack("La Barbacoa", "Georgie Dann");
            //this.tm.addTrack("Despacito", "Luis Fonsi");
            //this.tm.addTrack("Enter Sandman", "Metallica");
        }


    }
//
//    @GET
//    @ApiOperation(value = "get all Track", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response = Llibre.class, responseContainer="List"),
//    })
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTracks() {
//
//        List<Llibre> llibres = this.tm.findAll();
//
//        GenericEntity<List<Llibre>> entity = new GenericEntity<List<Llibre>>(llibres) {};
//        return Response.status(201).entity(entity).build()  ;
//
//    }
//
//    @GET
//    @ApiOperation(value = "get a Track", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response = Llibre.class),
//            @ApiResponse(code = 404, message = "Track not found")
//    })
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTrack(@PathParam("id") String id) {
//        Llibre t = this.tm.getTrack(id);
//        if (t == null) return Response.status(404).build();
//        else  return Response.status(201).entity(t).build();
//    }
//
//    @DELETE
//    @ApiOperation(value = "delete a Track", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "Track not found")
//    })
//    @Path("/{id}")
//    public Response deleteTrack(@PathParam("id") String id) {
//        Llibre t = this.tm.getTrack(id);
//        if (t == null) return Response.status(404).build();
//        else this.tm.deleteTrack(id);
//        return Response.status(201).build();
//    }
//
//    @PUT
//    @ApiOperation(value = "update a Track", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "Track not found")
//    })
//    @Path("/")
//    public Response updateTrack(Llibre llibre) {
//
//        Llibre t = this.tm.updateTrack(llibre);
//
//        if (t == null) return Response.status(404).build();
//
//        return Response.status(201).build();
//    }
//
//
//
//    @POST
//    @ApiOperation(value = "create a new Track", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response= Llibre.class),
//            @ApiResponse(code = 500, message = "Validation Error")
//
//    })
//
//    @Path("/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response newTrack(Llibre llibre) {
//
//        if (llibre.getAutor()==null || llibre.getTitle()==null)  return Response.status(500).entity(llibre).build();
//        this.tm.addTrack(llibre);
//        return Response.status(201).entity(llibre).build();
//    }

}