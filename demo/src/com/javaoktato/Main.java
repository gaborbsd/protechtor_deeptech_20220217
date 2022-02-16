package com.javaoktato;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        // var: type inference
        var s = "Hello, world!";
        var count = 5L;
        var isr = new InputStreamReader(System.in);

        System.out.println(s.getClass());
        System.out.println(isr.getClass());

        // HttpClient
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .uri(new URI("http://dummy.restapiexample.com/api/v1/employees"))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.printf("Response status: %d,\n\n headers: %s,\n\n body: %s\n\n",
                response.statusCode(),
                response.headers().map(),
                response.body());

        // switch
        var season = switch (LocalDate.now().getMonth()) {
            case DECEMBER, JANUARY, FEBRUARY -> "Winter";
            case MARCH, APRIL, MAY -> "Spring";
            case JUNE, JULY, AUGUST -> "Summer";
            case SEPTEMBER, OCTOBER, NOVEMBER -> {
                // We can write more complex code here
                // and the use yield to assign a value
                yield "Fall";
            }
        };

        // no need for break
        switch (season) {
            case "Winter" -> System.out.println("Tél van");
            case "Spring" -> System.out.println("Ősz van");
            case "Summer" -> System.out.println("Nyár van");
            case "Fall" -> System.out.println("Tavasz van");
        }

        // sealed classes
        SampleService service = new SampleServiceImpl();
        service.operation();

        // does not compile; cannot implement sealed interface
        /*
        var service2 = new SampleService() {
            @Override
            public void operation() {

            }
        };
         */

        // text blocks
        var name = "John Doe";
        var email = "johndoe@example.com";
        var message = """
                Dear %s,
                we have received your registration attempt with
                email %s. Your registration request is under evaluation.
                We will send you another email when the review is complete
                and your registration is approved or rejected.
                                
                Kind Regards,
                Acme Inc.
                """.formatted(name, email);
        System.out.println(message);

        // instanceof pattern matching
        Object file = new File("readme.txt");

        if (file instanceof File f) {
            System.out.println(f.exists());
        }

        // records
        var userData = getCurrentUser();
        System.out.printf("You are signed in as %s and you are assigned the role %s\n", userData.username(), userData.role());
    }

    private static UserData getCurrentUser() {
        return new UserData("john", "ADMIN");
    }
}
