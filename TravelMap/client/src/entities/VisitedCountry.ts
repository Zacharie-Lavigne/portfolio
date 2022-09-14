import CountryMapConfig from "./CountryMapConfig";
import VisitedRegion from "./VisitedRegion";

type VisitedCountry = {
  name: string;
  description: string;
  mapConfig: CountryMapConfig;
  visitedRegions: VisitedRegion[];
};

export default VisitedCountry;
