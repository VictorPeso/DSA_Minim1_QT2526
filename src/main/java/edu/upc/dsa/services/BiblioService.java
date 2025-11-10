package edu.upc.dsa.services;


import edu.upc.dsa.BiblioManager;
import edu.upc.dsa.BiblioManagerImpl;
import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;
import edu.upc.dsa.models.Prestac;
import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Llibre;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Api(value = "/Biblio", description = "Endpoint to Biblio Service")
@Path("/Biblio")
public class BiblioService {

    private BiblioManager bm;

    public BiblioService() {
        this.bm = BiblioManagerImpl.getInstance();
        if (bm.size_munt()==0 && bm.size_biblio()==0 && bm.size_catalog()==0) {
            String[][] booksData1 = {
                    {"JV7d", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                    {"JV4a", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                    {"JV1", "Journey to the Center of the Earth", "Dover Publications", "First Edition", "1864", "978-0486268685", "Jules Verne", "Adventures"},
                    {"JV3", "Around the World in Eighty Days", "CreateSpace", "First Edition", "1872", "978-1516887907", "Jules Verne", "Adventures"},
                    {"JV4c", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                    {"JV8", "The Begum's Fortune", "BiblioBazaar", "First Edition", "1879", "978-1103325575", "Jules Verne", "Adventures"},
                    {"JV7c", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                    {"JV5", "The Adventures of Captain Hatteras", "Wordsworth Editions", "First Edition", "1866", "978-1853260257", "Jules Verne", "Adventures"},
                    {"JV2b", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                    {"JV2c", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                    // numStack: 0
                    {"JV2a", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                    {"JV6", "From the Earth to the Moon", "Oxford University Press", "First Edition", "1865", "978-0199538474", "Jules Verne", "Adventures"},
                    {"JV7a", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                    {"JV4b", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                    {"JV7b", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"}
            };
            for (String[] vec: booksData1){
                this.bm.addLlibre(vec[0], vec[5], vec[1], vec[6], vec[2], vec[4], vec[7], vec[3]);
            }
            this.bm.addLector("111", "Victor", "Peso Keyer", "45794453G", "08/05/2001", "Castelldefels");
        }
    }



    @POST
    @ApiOperation(value = "Afegir un nou lector", notes = "Aquesta operació afegirà un nou lector amb els paràmetres introduïts a la base de dades de lectors de la biblioteca.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Lector.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/lector")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLector(Lector lector) {

        if ((lector.getId()==null) || (lector.getNom()==null) || (lector.getCognoms()==null) || (lector.getDni()==null) || (lector.getBirthdate()==null) || (lector.getDireccio()==null)) { return Response.status(500).entity(lector).build(); }
        this.bm.addLector(lector.getId(), lector.getNom(), lector.getCognoms(), lector.getDni(), lector.getBirthdate(), lector.getDireccio());
        return Response.status(200).entity(lector).build();
    }



    @POST
    @ApiOperation(value = "Emmagatzemar un llibre", notes = "Aquesta operació introduirà el llibre afegit a la pila de munts i farà les operacions pertinents per si en cas de completar una pila, passar a la següent.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Llibre.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/llibre")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLlibre(Llibre llibre) {

        if ((llibre.getId()==null) || (llibre.getIsbn()==null) || (llibre.getTitle()==null) || (llibre.getAutor()==null) || (llibre.getEditorial()==null) || (llibre.getAny_publicacio()==null) || (llibre.getTematica()==null) || (llibre.getNum_edicio()==null)) { return Response.status(500).entity(llibre).build(); }
        this.bm.addLlibre(llibre.getId(), llibre.getIsbn(), llibre.getTitle(), llibre.getAutor(), llibre.getEditorial(), llibre.getAny_publicacio(), llibre.getTematica(), llibre.getNum_edicio());
        return Response.status(200).entity(llibre).build();
    }



    @GET
    @ApiOperation(value = "Catalogar un llibre", notes = "Executar la catalogació sistemàtica de les piles de llibres i obtenir la llista resultant.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Llibre.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No hi han llibres per catalogar.")
    })
    @Path("/catalog")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLlibre() {
        try {
            this.bm.saveLlibre();
            GenericEntity<List<Llibre>> entity = new GenericEntity<List<Llibre>>(this.bm.get_catalog()) {};
            return Response.status(200).entity(entity).build();

        } catch (EmptyBookListException e) {
            return Response.status(404).build();
        }
    }



    @POST
    @ApiOperation(value = "Prestar un llibre", notes = "Crea un préstec on s'assigna un Llibre a un Lector. Posteriorment, aquest préstec queda enregistrat a la base de dades de préstecs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Prestac.class),
            @ApiResponse(code = 405, message = "Lector no enregistrat"),
            @ApiResponse(code = 406, message = "Llibre no disponible"),
            @ApiResponse(code = 407, message = "Validation error")
    })
    @Path("/prestac")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doPrestac(Prestac prestac) {
        try {
            Prestac p = this.bm.doPrestac(prestac.getId(), prestac.getId_lector(), prestac.getId_llibre(), prestac.getData_prestac(), prestac.getData_devolucio());
            return Response.status(200).entity(p).build();
        } catch (CantDoPrestacException e) {
            if (e.getMessage().equals("Lector no enregistrat.")){
                return Response.status(405).build();
            }
            else if (e.getMessage().equals("Llibre no disponible.")){
                return Response.status(406).build();
            }
            else {
                return Response.status(407).build();
            }
        }
    }



    @GET
    @ApiOperation(value = "Consultar tots els préstecs que ha realitzat un lector", notes = "Llistar tots els préstecs de dintre del registre a la base de dades efectuats per l'usuari amb {id} introduït.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Prestac.class, responseContainer="List"),
    })
    @Path("/prestacs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLLibresByUser(@PathParam("id") String id) {
        Lector lector = new Lector();
        for (Lector l : this.bm.get_lectors()) {
            if (l.getId().equals(id)){
                lector = l;
            }
        }

        List<Prestac> prestecs = this.bm.getLLibresByUser(lector);
        GenericEntity<List<Prestac>> entity = new GenericEntity<List<Prestac>>(prestecs) {};
        return Response.status(200).entity(entity).build()  ;
    }
}