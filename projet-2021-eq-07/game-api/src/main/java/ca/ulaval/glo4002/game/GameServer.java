package ca.ulaval.glo4002.game;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryHttpClient;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.BreedingResultAssembler;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.VeterinaryBreedingAssembler;
import ca.ulaval.glo4002.game.breeding.rest.BreedingResource;
import ca.ulaval.glo4002.game.breeding.rest.assemblers.BreedingAssembler;
import ca.ulaval.glo4002.game.breeding.useCases.BreedingUseCase;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.combat.rest.SumoCombatResource;
import ca.ulaval.glo4002.game.config.SpringMainConfig;
import ca.ulaval.glo4002.game.dinosaurs.domain.BabyDinosaurGrower;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFamilyHandler;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFeeder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurResource;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DeltaWeightAssembler;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.dinosaurs.useCases.DinosaurUseCase;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.flow.rest.ResetResource;
import ca.ulaval.glo4002.game.flow.rest.TurnResource;
import ca.ulaval.glo4002.game.flow.useCases.PlayTurnActionUseCase;
import ca.ulaval.glo4002.game.flow.useCases.PlayTurnDinosaurUseCase;
import ca.ulaval.glo4002.game.flow.useCases.PlayTurnResourcesUseCase;
import ca.ulaval.glo4002.game.flow.useCases.PlayTurnUseCase;
import ca.ulaval.glo4002.game.flow.useCases.ResetUseCase;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.rest.ResourcesResource;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesQuantitiesAssembler;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesReportAssembler;
import ca.ulaval.glo4002.game.resources.useCases.ResourcesUseCase;
import ca.ulaval.glo4002.game.rest.communication.BadRequestExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.communication.BreedingExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.communication.InvalidDinosaurAttributeExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.communication.InvalidDinosaurWeightExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.communication.NonExistingNameExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.communication.SumoCombatExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.factories.BadRequestExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.factories.BreedingExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.factories.InvalidDinosaurAttributeExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.factories.InvalidDinosaurWeightExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.factories.NonExistingNameExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.factories.SumoCombatExceptionResponseFactory;
import ca.ulaval.glo4002.game.rest.mappers.BadRequestExceptionMapper;
import ca.ulaval.glo4002.game.rest.mappers.BreedingExceptionMapper;
import ca.ulaval.glo4002.game.rest.mappers.InvalidDinosaurAttributeExceptionMapper;
import ca.ulaval.glo4002.game.rest.mappers.InvalidDinosaurWeightExceptionMapper;
import ca.ulaval.glo4002.game.rest.mappers.NonExistingNameExceptionMapper;
import ca.ulaval.glo4002.game.rest.mappers.SumoCombatExceptionMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GameServer implements Runnable {

  private static final int PORT = 8181;

  public static void main(String[] args) {
    new GameServer().run();
  }

  public void run() {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringMainConfig.class);

    repositoriesContext(context);
    assemblersContext(context);
    factoriesContext(context);
    useCasesContext(context);
    domainContext(context);
    httpServicesContext(context);

    BreedingResource breedingResource = context.getBean(BreedingResource.class);
    SumoCombatResource sumoCombatResource = context.getBean(SumoCombatResource.class);
    TurnResource turnResource = context.getBean(TurnResource.class);
    ResetResource resetResource = context.getBean(ResetResource.class);
    DinosaurResource dinosaurResource = context.getBean(DinosaurResource.class);
    ResourcesResource resourcesResource = context.getBean(ResourcesResource.class);
    BadRequestExceptionMapper badRequestExceptionMapper =
        context.getBean(BadRequestExceptionMapper.class);
    NonExistingNameExceptionMapper nonExistingNameExceptionMapper =
        context.getBean(NonExistingNameExceptionMapper.class);
    InvalidDinosaurAttributeExceptionMapper invalidDinosaurAttributeExceptionMapper =
        context.getBean(InvalidDinosaurAttributeExceptionMapper.class);
    BreedingExceptionMapper breedingExceptionMapper =
        context.getBean(BreedingExceptionMapper.class);
    SumoCombatExceptionMapper sumoCombatExceptionMapper =
        context.getBean(SumoCombatExceptionMapper.class);
    InvalidDinosaurWeightExceptionMapper dinosaurWeightExceptionMapper =
        context.getBean(InvalidDinosaurWeightExceptionMapper.class);

    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
    ResourceConfig packageConfig =
        new ResourceConfig()
            .packages("ca.ulaval.glo4002.game")
            .register(dinosaurResource)
            .register(turnResource)
            .register(resetResource)
            .register(resourcesResource)
            .register(breedingResource)
            .register(sumoCombatResource)
            .register(badRequestExceptionMapper)
            .register(nonExistingNameExceptionMapper)
            .register(invalidDinosaurAttributeExceptionMapper)
            .register(breedingExceptionMapper)
            .register(sumoCombatExceptionMapper)
            .register(dinosaurWeightExceptionMapper);

    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, "/*");

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (server.isRunning()) {
        server.destroy();
      }
    }
  }

  private void repositoriesContext(ApplicationContext context) {
    context.getBean(DinosaurHerdRepository.class);
    context.getBean(PantryRepository.class);
    context.getBean(GameRepository.class);
  }

  private void assemblersContext(ApplicationContext context) {
    context.getBean(BreedingAssembler.class);
    context.getBean(BreedingResultAssembler.class);
    context.getBean(DinosaurAssembler.class);
    context.getBean(FoodResourcesQuantitiesAssembler.class);
    context.getBean(FoodResourcesReportAssembler.class);
    context.getBean(VeterinaryBreedingAssembler.class);
    context.getBean(DeltaWeightAssembler.class);
    context.getBean(BadRequestExceptionCommunicator.class);
    context.getBean(BreedingExceptionCommunicator.class);
    context.getBean(InvalidDinosaurAttributeExceptionCommunicator.class);
    context.getBean(NonExistingNameExceptionCommunicator.class);
    context.getBean(SumoCombatExceptionCommunicator.class);
    context.getBean(InvalidDinosaurWeightExceptionCommunicator.class);
  }

  private void factoriesContext(ApplicationContext context) {
    context.getBean(ActionFactory.class);
    context.getBean(DinosaurFactory.class);
    context.getBean(BadRequestExceptionResponseFactory.class);
    context.getBean(BreedingExceptionResponseFactory.class);
    context.getBean(InvalidDinosaurAttributeExceptionResponseFactory.class);
    context.getBean(NonExistingNameExceptionResponseFactory.class);
    context.getBean(SumoCombatExceptionResponseFactory.class);
    context.getBean(InvalidDinosaurWeightExceptionResponseFactory.class);
  }

  private void useCasesContext(ApplicationContext context) {
    context.getBean(BreedingUseCase.class);
    context.getBean(DinosaurUseCase.class);
    context.getBean(PlayTurnUseCase.class);
    context.getBean(PlayTurnActionUseCase.class);
    context.getBean(PlayTurnResourcesUseCase.class);
    context.getBean(PlayTurnDinosaurUseCase.class);
    context.getBean(ResetUseCase.class);
    context.getBean(ResourcesUseCase.class);
  }

  private void domainContext(ApplicationContext context) {
    context.getBean(DinosaurFeeder.class);
    context.getBean(DinosaurFamilyHandler.class);
    context.getBean(BabyDinosaurGrower.class);
    context.getBean(DinosaurOrganizer.class);
    context.getBean(SumoCombatOrganizer.class);
  }

  private void httpServicesContext(ApplicationContext context) {
    context.getBean(VeterinaryHttpClient.class);
  }
}
