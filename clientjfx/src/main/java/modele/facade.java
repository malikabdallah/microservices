package modele;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import vues.Historique;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

public class facade {

    private static final String URI_SERVICE="http://localhost:8765/api/musee";

    private static final String OEUVRES= "/oeuvres";
    private static final String SALLES= "/salles";
    private static final String EXPOSITIONS= "/expositions";
    private static final String RESERVES= "/reserves";
    private static final String PRETS="/prets";


    private HttpClient httpClient = HttpClient.newHttpClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    public facade() {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    }





    public int login(String login,String pass){



        String plainClientCredentials=login+":"+pass;
        String base64ClientCredentials = new String(Base64.getEncoder().encode(plainClientCredentials.getBytes()));
/*
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8765/api/user/service/login")).
                        header("Authorization", "Basic " + base64ClientCredentials)

                       // .header("Authorization", basicAuth("username", "password"))
                        .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();


        byte[] encodedBytes = Base64.getEncoder().encode("selam selam".getBytes());

*/
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8765/api/user/service/login"))
                    .GET()
                    .headers("authorization", "Basic "+base64ClientCredentials).
                    build();

            HttpResponse<String> response;
            try {

                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
               // User user = objectMapper.readValue(response.body(),new TypeReference<User>(){});
                System.out.println("response "+response.statusCode());


                return response.statusCode() ;

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                throw new UnexpectedError();
            }
    }




    private String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes() );
    }




    ///expositions/{id}/oeuvres

    public List<Oeuvre> getOeuvresExp(String id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE+"/expositions/"+id+"/oeuvres"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre>rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }



    public List<Salle> getSalleExpo(String id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE+"/expositions/"+id+"/salles"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Salle>rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Salle>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }

    public int lieoeuvresalle(String idoeuvres,String salle) {
        try {
            //String rendezV = objectMapper.writeValueAsString(oeuvre);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE +"/salles/"+salle+"/oeuvres/"+idoeuvres))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpResponse<String>  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Salle rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Salle>(){});


            /***********************************************************/


            Oeuvre oeuvre=this.getOeuvreById(Long.parseLong(idoeuvres));
            Salle salle1=this.getSalleById(Long.parseLong(salle));


            Evenement evenement=new Evenement();
            evenement.setIdOeuvre(rdvDTOList.getId());
            evenement.setEvenement(oeuvre.getNomOeuvre()+" a ete expose dans la salle "+salle1.getNomSalle());
            this.enregistreEvenement(evenement);


            /*************************************************************/

            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }







    public int logout() {
        try {
            //String rendezV = objectMapper.writeValueAsString(oeuvre);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8765/api/user/service/logout"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }






    public List<Oeuvre> getAllOeuvres() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + OEUVRES))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre> rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public Oeuvre getOeuvreById(long id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + OEUVRES+"/"+id))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Oeuvre rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Oeuvre>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }




    public Oeuvre mettreEnRestauration(long id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + "/restauration"+"/"+id))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Oeuvre rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Oeuvre>(){});


            /***********************************************************/


            Evenement evenement=new Evenement();
            evenement.setIdOeuvre(rdvDTOList.getId());
            evenement.setEvenement(rdvDTOList.getNomOeuvre()+" a ete mise en restauration");

            /*************************************************************/

            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }



    public Oeuvre MettreEnReserves(long id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + "/reserves"+"/"+id))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Oeuvre rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Oeuvre>(){});


            /***********************************************************/



            Evenement evenement=new Evenement();
            evenement.setIdOeuvre(rdvDTOList.getId());
            evenement.setEvenement(rdvDTOList.getNomOeuvre()+" a ete mise en reserve");


            this.enregistreEvenement(evenement);




            /*************************************************************/
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public List<Oeuvre> getExposes() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + "/exposes"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre> rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public Salle getSalleById(long id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + SALLES+"/"+id))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Salle rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Salle>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }



    public List<Salle> getSallesDisponible() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + SALLES+"/disponible"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Salle>rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Salle>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public List<Oeuvre> getReserves() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + RESERVES))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre> rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }



    public List<Salle> getAllSalles() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + SALLES))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Salle> listSalles = objectMapper.readValue(response.body(),new TypeReference<List<Salle>>(){});
            return listSalles;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }





    public Exposition getExpoParId(String id) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + EXPOSITIONS+"/"+id))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Exposition listeExpositions = objectMapper.readValue(response.body(),new TypeReference<Exposition>(){});
            return listeExpositions;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }




    public List<Exposition> getAllExposition() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + EXPOSITIONS))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Exposition> listeExpositions = objectMapper.readValue(response.body(),new TypeReference<List<Exposition>>(){});
            return listeExpositions;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public int updatesalle(Salle salle) {
        try {
            String rendezV = objectMapper.writeValueAsString(salle);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + SALLES+"/"+salle.id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }



    public int updateExposition(Exposition exposition) {
        try {
            String rendezV = objectMapper.writeValueAsString(exposition);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + EXPOSITIONS+"/"+exposition.getId()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }








    public List<Oeuvre> oeuvreEnRestauration(){


        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8765/api/musee/restauration/oeuvres"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre> listeExpositions = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return listeExpositions;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }




    public int creerUneSalle(Salle salle) {
        try {
            String rendezV = objectMapper.writeValueAsString(salle);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + SALLES))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }





    public int creerUneOeuvre(Oeuvre oeuvre) {
        try {
            String rendezV = objectMapper.writeValueAsString(oeuvre);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + OEUVRES))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();

            HttpResponse<String>  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Oeuvre rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Oeuvre>(){});




            /***********************************************************/




            Evenement evenement=new Evenement();
           evenement.setIdOeuvre(rdvDTOList.getId());
            evenement.setEvenement(oeuvre.getNomOeuvre()+" a ete achete");

            this.enregistreEvenement(evenement);



            /*************************************************************/
           // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }



    public int creerUneExposition(Exposition exposition) {
        try {
            String rendezV = objectMapper.writeValueAsString(exposition);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + EXPOSITIONS))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }



    public int emprunterOeuvre(Oeuvre oeuvre) {
        try {
            String rendezV = objectMapper.writeValueAsString(oeuvre);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE + PRETS))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();


            HttpResponse<String>  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Oeuvre rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<Oeuvre>(){});




            /***********************************************************/



            Evenement evenement=new Evenement();
            evenement.setIdOeuvre(rdvDTOList.getId());
            evenement.setEvenement(oeuvre.getNomOeuvre()+" a ete emprunte");



            this.enregistreEvenement(evenement);




            /*************************************************************/
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

    private int enregistreEvenement(Evenement evenement) {
        try {
            String rendezV = objectMapper.writeValueAsString(evenement);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_SERVICE +"/evenements"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rendezV))
                    .build();
            HttpResponse  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // String location = response.headers().firstValue("location").get();
            //String[] locations = location.split("/");
            //int idUser = Integer.parseInt(locations[locations.length-1]);



            return 1;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    //oeuvre d une salle http://localhost:8765/api/musee/salles/1/oeuvres



    public List<Oeuvre> getOeuvresSalles(String idsalle) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE + SALLES+"/"+idsalle+OEUVRES))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Oeuvre> rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Oeuvre>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }

    }


    public List<Evenement> getHistoriqueOeuvre(int idOeuvre) {

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URI_SERVICE +"/evenements/oeuvres/"+idOeuvre))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<Evenement> rdvDTOList = objectMapper.readValue(response.body(),new TypeReference<List<Evenement>>(){});
            return rdvDTOList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new UnexpectedError();
        }
    }
}
