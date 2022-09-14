package ca.ulaval.glo4002.game.breeding.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.rest.assemblers.BreedingAssembler;
import ca.ulaval.glo4002.game.breeding.useCases.BreedingUseCase;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BreedingResourceTest {
  private final BreedingAssembler breedingAssembler = mock(BreedingAssembler.class);
  private final BreedingUseCase breedingUseCase = mock(BreedingUseCase.class);

  private final BreedingResource breedingResource =
      new BreedingResource(breedingAssembler, breedingUseCase);

  private final String A_NAME = "pablo";
  private final BreedingRequest A_BREEDING_REQUEST = new BreedingRequest(A_NAME, A_NAME, A_NAME);
  private final BreedingAttempt A_BREEDING_ATTEMPT = new BreedingAttempt(A_NAME, A_NAME, A_NAME);

  @BeforeEach
  public void setUp() {
    when(breedingAssembler.toDomain(A_BREEDING_REQUEST)).thenReturn(A_BREEDING_ATTEMPT);
  }

  @Test
  public void
      givenABreedingRequest_whenCreateBreedDinosaursAction_thenShouldAssembleBreedingRequestToDomain() {
    breedingResource.createBreedDinosaursAction(A_BREEDING_REQUEST);

    verify(breedingAssembler).toDomain(A_BREEDING_REQUEST);
  }

  @Test
  public void
      givenABreedingRequest_whenCreateBreedDinosaursAction_thenShouldCreateBreedDinosaurAction() {
    breedingResource.createBreedDinosaursAction(A_BREEDING_REQUEST);

    verify(breedingUseCase).createBreedDinosaurAction(A_BREEDING_ATTEMPT);
  }

  @Test
  public void
      givenAValidBreedingRequest_whenCreateBreedDinosaursAction_thenShouldReturnResponseWithCode200() {
    breedingResource.createBreedDinosaursAction(A_BREEDING_REQUEST);

    Response expectedResponse = Response.ok().build();
    Response actualResponse = breedingResource.createBreedDinosaursAction(A_BREEDING_REQUEST);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }
}
