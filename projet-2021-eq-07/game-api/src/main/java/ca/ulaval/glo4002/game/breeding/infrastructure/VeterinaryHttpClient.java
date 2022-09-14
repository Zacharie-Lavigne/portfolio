package ca.ulaval.glo4002.game.breeding.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryHttpClient {

  private static final String VET_SERVER_URL = "http://localhost:8080";

  private final HttpClient httpClient =
      HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

  @SneakyThrows
  public Option<VeterinaryBreedingResponse> breedDinosaurs(
      VeterinaryBreedingDto veterinaryBreedingDto) {
    Map<Object, Object> data = new HashMap<>();
    data.put("fatherSpecies", veterinaryBreedingDto.fatherSpecies);
    data.put("motherSpecies", veterinaryBreedingDto.motherSpecies);

    ObjectMapper objectMapper = new ObjectMapper();

    String requestBody = objectMapper.writeValueAsString(data);

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(VET_SERVER_URL + "/breed"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == Response.Status.OK.getStatusCode()) {
      return Option.of(objectMapper.readValue(response.body(), VeterinaryBreedingResponse.class));
    } else {
      return Option.none();
    }
  }
}
