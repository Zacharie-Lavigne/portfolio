import visitedCountries from "../entities/VisitedCountries";

const countriesQuerySchema = `countries: [VisitedCountry]`;
const countriesQuery = () => {
  return visitedCountries;
};

const countryByMapNameQuerySchema = `countryByMapName(mapName : String!): VisitedCountry`;
const countryByMapNameQuery = (args: { mapName: string }) => {
  return visitedCountries.find(
    (country) => country.mapConfig.mapName === args.mapName
  );
};

export {
  countriesQuery,
  countriesQuerySchema,
  countryByMapNameQuery,
  countryByMapNameQuerySchema,
};
