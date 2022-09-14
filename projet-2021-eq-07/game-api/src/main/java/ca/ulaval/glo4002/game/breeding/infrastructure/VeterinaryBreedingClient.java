package ca.ulaval.glo4002.game.breeding.infrastructure;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.BreedingResultAssembler;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.VeterinaryBreedingAssembler;
import io.vavr.control.Option;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryBreedingClient implements Breeder {
  private final VeterinaryHttpClient httpClient;
  private final BreedingResultAssembler breedingResultAssembler;
  private final VeterinaryBreedingAssembler veterinaryBreedingAssembler;

  public VeterinaryBreedingClient(
      BreedingResultAssembler breedingResultAssembler,
      VeterinaryBreedingAssembler veterinaryBreedingAssembler,
      VeterinaryHttpClient httpClient) {
    this.breedingResultAssembler = breedingResultAssembler;
    this.httpClient = httpClient;
    this.veterinaryBreedingAssembler = veterinaryBreedingAssembler;
  }

  @Override
  public Option<BreedingResult> breedDinosaurs(
      BreedingFamilyInformation breedingFamilyInformation) {
    VeterinaryBreedingDto veterinaryBreedingDto =
        this.veterinaryBreedingAssembler.toDto(breedingFamilyInformation);

    try {
      Option<VeterinaryBreedingResponse> veterinaryBreedingResponse =
          this.httpClient.breedDinosaurs(veterinaryBreedingDto);

      if (veterinaryBreedingResponse.isDefined()) {
        return Option.of(
            this.breedingResultAssembler.toDomain(
                breedingFamilyInformation.getBabyName(), veterinaryBreedingResponse.get()));
      }
      return Option.none();
    } catch (Exception ignored) {
      return Option.none();
    }
  }
}
