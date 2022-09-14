package ca.ulaval.glo4002.game.breeding.useCases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.actions.domain.BreedDinosaursAction;
import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryBreedingClient;
import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class BreedingUseCaseTest {
  private final DinosaurHerdRepository dinosaurHerdRepository = mock(DinosaurHerdRepository.class);
  private final GameRepository gameRepository = mock(GameRepository.class);
  private final ActionFactory actionFactory = mock(ActionFactory.class);
  private final VeterinaryBreedingClient breeder = mock(VeterinaryBreedingClient.class);
  private final DinosaurFactory dinosaurFactory = mock(DinosaurFactory.class);

  private final BreedingUseCase breedingUseCase =
      new BreedingUseCase(
          actionFactory, dinosaurHerdRepository, breeder, dinosaurFactory, gameRepository);

  private final String AN_EXISTING_DINOSAUR_NAME = "Melasse";
  private final String A_FATHER_NAME = "Yvon";
  private final String A_MOTHER_NAME = "Yvonne";

  private final BreedingAttempt A_BREEDING_ATTEMPT =
      new BreedingAttempt(AN_EXISTING_DINOSAUR_NAME, A_FATHER_NAME, A_MOTHER_NAME);

  private final Dinosaur A_DINOSAUR =
      new DinosaurBuilder().withName(AN_EXISTING_DINOSAUR_NAME).build();
  private final Dinosaur A_FATHER_DINOSAUR =
      new DinosaurBuilder().withName(A_FATHER_NAME).withGender(Gender.MALE).build();
  private final Dinosaur A_MOTHER_DINOSAUR =
      new DinosaurBuilder().withName(A_MOTHER_NAME).withGender(Gender.FEMALE).build();

  @Test
  public void
      givenABreedingAttemptWithADinosaurNameThatAlreadyExists_whenCreatingBreedDinosaurAction_thenShouldThrowInvalidDinosaurNameException() {
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_DINOSAUR, A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR));

    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);

    assertThrows(
        InvalidDinosaurNameException.class,
        () -> breedingUseCase.createBreedDinosaurAction(A_BREEDING_ATTEMPT));
  }

  @Test
  public void
      givenABreedingAttempt_whenCreatingBreedDinosaurAction_thenShouldGetFatherDinosaur() {
    DinosaurHerd dinosaurHerd = spy(new DinosaurHerd(List.of(A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR)));
    Game game = new Game();
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);
    when(gameRepository.getGame()).thenReturn(game);


    breedingUseCase.createBreedDinosaurAction(A_BREEDING_ATTEMPT);

    verify(dinosaurHerd).getByName(A_FATHER_NAME);
  }

  @Test
  public void
      givenABreedingAttempt_whenCreatingBreedDinosaurAction_thenShouldGetMotherDinosaur() {
    DinosaurHerd dinosaurHerd = spy(new DinosaurHerd(List.of(A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR)));
    Game game = new Game();
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);
    when(gameRepository.getGame()).thenReturn(game);


    breedingUseCase.createBreedDinosaurAction(A_BREEDING_ATTEMPT);

    verify(dinosaurHerd).getByName(A_MOTHER_NAME);
  }

  @Test
  public void
      givenABreedingAttemptWithAFemaleFather_whenCreatingBreedDinosaurAction_thenShouldThrowInvalidFatherException() {
    BreedingAttempt breedingAttempt =
        new BreedingAttempt(AN_EXISTING_DINOSAUR_NAME, A_MOTHER_NAME, A_MOTHER_NAME);
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_MOTHER_DINOSAUR, A_MOTHER_DINOSAUR));

    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);

    assertThrows(
        InvalidFatherException.class,
        () -> breedingUseCase.createBreedDinosaurAction(breedingAttempt));
  }

  @Test
  public void
      givenABreedingAttemptWithAMaleMother_whenCreatingBreedDinosaurAction_thenShouldThrowInvalidMotherException() {
    BreedingAttempt breedingAttempt =
        new BreedingAttempt(AN_EXISTING_DINOSAUR_NAME, A_FATHER_NAME, A_FATHER_NAME);
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_FATHER_DINOSAUR, A_FATHER_DINOSAUR));

    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);

    assertThrows(
        InvalidMotherException.class,
        () -> breedingUseCase.createBreedDinosaurAction(breedingAttempt));
  }

  @Test
  public void
      givenAValidBreedingAttempt_whenCreatingBreedDinosaurAction_thenShouldCreateBreedDinosaursAction() {
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR));
    Game game = new Game();
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);
    when(gameRepository.getGame()).thenReturn(game);

    breedingUseCase.createBreedDinosaurAction(A_BREEDING_ATTEMPT);

    verify(actionFactory)
        .createBreedDinosaursAction(
            any(BreedingFamilyInformation.class),
            any(Breeder.class),
            any(DinosaurHerd.class),
            any(DinosaurFactory.class));
  }

  @Test
  public void
      givenAValidBreedingAttempt_whenCreatingBreedDinosaurAction_thenShouldAddActionToGame() {
    String aBabyName = "Cesar";
    BreedingFamilyInformation breedingFamilyInformation =
        new BreedingFamilyInformation(aBabyName, A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR);
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_FATHER_DINOSAUR, A_MOTHER_DINOSAUR));
    Game game = spy(new Game());

    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(dinosaurHerd);
    when(gameRepository.getGame()).thenReturn(game);

    BreedDinosaursAction breedDinosaurAction =
        new BreedDinosaursAction(breedingFamilyInformation, breeder, dinosaurHerd, dinosaurFactory);

    when(actionFactory.createBreedDinosaursAction(
            any(BreedingFamilyInformation.class),
            any(Breeder.class),
            any(DinosaurHerd.class),
            any(DinosaurFactory.class)))
        .thenReturn(breedDinosaurAction);

    breedingUseCase.createBreedDinosaurAction(A_BREEDING_ATTEMPT);

    verify(game).addAction(breedDinosaurAction);
  }
}
