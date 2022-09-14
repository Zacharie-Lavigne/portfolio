package ca.ulaval.glo4002.game.dinosaurs.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DeltaWeightAssembler;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.dinosaurs.useCases.DinosaurUseCase;
import io.vavr.collection.List;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

class DinosaurResourceTest {
  private final DinosaurAssembler dinosaurAssembler = mock(DinosaurAssembler.class);
  private final DinosaurUseCase dinosaurUseCase = mock(DinosaurUseCase.class);
  private final DeltaWeightAssembler deltaWeightAssembler = mock(DeltaWeightAssembler.class);

  private final DinosaurResource dinosaurResource =
      new DinosaurResource(dinosaurAssembler, dinosaurUseCase, deltaWeightAssembler);

  private final String A_NAME = "Pauly";
  private final int A_WEIGHT = 200;
  private final String A_GENDER = "m";
  private final String A_SPECIES = "T-rex";
  private final DinosaurRequest A_DINOSAUR_REQUEST =
      new DinosaurRequest(A_NAME, A_WEIGHT, A_GENDER, A_SPECIES);
  private final DinosaurChangeWeightRequest A_WEIGHT_CHANGE_REQUEST =
      new DinosaurChangeWeightRequest(300);
  private final Dinosaur A_DINOSAUR = new DinosaurBuilder().build();
  private final List<Dinosaur> DINOSAURS = List.of(A_DINOSAUR, A_DINOSAUR);
  private final DinosaurResponse A_DINOSAUR_RESPONSE =
      new DinosaurResponse(A_NAME, A_WEIGHT, A_GENDER, A_SPECIES);

  @Test
  public void
      givenADinosaurRequest_whenCreateAddDinosaurAction_thenShouldAssembleDinosaurRequestToDomain() {
    dinosaurResource.createAddDinosaurAction(A_DINOSAUR_REQUEST);

    verify(dinosaurAssembler).toDomain(A_DINOSAUR_REQUEST);
  }

  @Test
  public void
      givenADinosaurRequest_whenCreateAddDinosaurAction_thenShouldCreateAddDinosaurAction() {
    when(dinosaurAssembler.toDomain(A_DINOSAUR_REQUEST)).thenReturn(A_DINOSAUR);

    dinosaurResource.createAddDinosaurAction(A_DINOSAUR_REQUEST);

    verify(dinosaurUseCase).createAddDinosaurAction(A_DINOSAUR);
  }

  @Test
  public void
      givenADinosaurRequest_whenCreateAddDinosaurAction_thenShouldReturnResponseWithCode200() {
    Response expectedResponse = Response.ok().build();
    Response actualResponse = dinosaurResource.createAddDinosaurAction(A_DINOSAUR_REQUEST);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  @Test
  public void whenGettingDinosaurs_thenShouldGetAllDinosaurs() {
    givenDinosaurs();

    dinosaurResource.getDinosaurs();

    verify(dinosaurUseCase).getAllDinosaurs();
  }

  @Test
  public void whenGettingDinosaurs_thenShouldAssembleDinosaursToResponse() {
    givenDinosaurs();

    dinosaurResource.getDinosaurs();

    verify(dinosaurAssembler, times(DINOSAURS.length())).toResponse(A_DINOSAUR);
  }

  @Test
  public void whenGettingDinosaurs_thenShouldReturnDinosaursResponseWithSameEntity() {
    givenDinosaurs();
    givenADinosaurResponse();

    Response expectedResponse =
        Response.ok().entity(List.of(A_DINOSAUR_RESPONSE, A_DINOSAUR_RESPONSE).asJava()).build();
    Response actualResponse = dinosaurResource.getDinosaurs();

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }

  @Test
  public void whenGettingDinosaurs_thenShouldReturnDinosaursResponseWithCode200() {
    givenDinosaurs();
    givenADinosaurResponse();

    Response expectedResponse =
        Response.ok().entity(List.of(A_DINOSAUR_RESPONSE, A_DINOSAUR_RESPONSE).asJava()).build();
    Response actualResponse = dinosaurResource.getDinosaurs();

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  @Test
  public void givenAName_whenGettingADinosaurByName_thenShouldGetDinosaurByName() {
    dinosaurResource.getDinosaurByName(A_NAME);

    verify(dinosaurUseCase).getDinosaurByName(A_NAME);
  }

  @Test
  public void givenAName_whenGettingADinosaurByName_thenShouldAssembleDinosaurToResponse() {
    givenADinosaur();

    dinosaurResource.getDinosaurByName(A_NAME);

    verify(dinosaurAssembler).toResponse(A_DINOSAUR);
  }

  @Test
  public void givenAName_whenGettingADinosaurByName_thenShouldReturnDinosaurResponseWithEntity() {
    givenADinosaur();
    givenADinosaurResponse();

    Response expectedResponse = Response.ok().entity(A_DINOSAUR_RESPONSE).build();
    Response actualResponse = dinosaurResource.getDinosaurByName(A_NAME);

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }

  @Test
  public void givenAName_whenGettingADinosaurByName_thenShouldReturnDinosaurResponseWithCode200() {
    givenADinosaur();
    givenADinosaurResponse();

    Response expectedResponse = Response.ok().entity(A_DINOSAUR_RESPONSE).build();
    Response actualResponse = dinosaurResource.getDinosaurByName(A_NAME);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  @Test
  public void
      givenAWeightChangeRequest_whenCreateChangeDinosaurWeightAction_thenShouldAssembleRequestToDeltaWeight() {
    dinosaurResource.createChangeDinosaurWeightAction(A_WEIGHT_CHANGE_REQUEST, A_NAME);

    verify(deltaWeightAssembler).toDomain(A_WEIGHT_CHANGE_REQUEST);
  }

  @Test
  public void
      givenADinosaurNameAndAWeightChangeRequest_whenCreateChangeDinosaurWeightAction_thenShouldCreateChangeDinosaurWeightAction() {
    DeltaWeight deltaWeight = new DeltaWeight(A_WEIGHT_CHANGE_REQUEST.deltaWeight);

    when(deltaWeightAssembler.toDomain(A_WEIGHT_CHANGE_REQUEST)).thenReturn(deltaWeight);

    dinosaurResource.createChangeDinosaurWeightAction(A_WEIGHT_CHANGE_REQUEST, A_NAME);

    verify(dinosaurUseCase).createChangeDinosaurWeightAction(A_NAME, deltaWeight);
  }

  @Test
  public void
      givenADinosaurNameAndAWeightChangeRequest_whenCreateChangeDinosaurWeightAction_thenShouldReturnStatusCode200() {
    Response expectedResponse = Response.ok().entity(A_DINOSAUR_RESPONSE).build();
    Response actualResponse =
        dinosaurResource.createChangeDinosaurWeightAction(A_WEIGHT_CHANGE_REQUEST, A_NAME);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  private void givenDinosaurs() {
    when(dinosaurUseCase.getAllDinosaurs()).thenReturn(DINOSAURS);
  }

  private void givenADinosaurResponse() {
    when(dinosaurAssembler.toResponse(A_DINOSAUR)).thenReturn(A_DINOSAUR_RESPONSE);
  }

  private void givenADinosaur() {
    when(dinosaurUseCase.getDinosaurByName(A_NAME)).thenReturn(A_DINOSAUR);
  }
}
