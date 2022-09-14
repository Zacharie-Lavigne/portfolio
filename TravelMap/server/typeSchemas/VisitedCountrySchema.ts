const VisitedCountrySchema = `
  type VisitedCountry {
    name: String!
    description: String!
    mapConfig: CountryMapConfig!
    visitedRegions: [VisitedRegion!]!
  }
`;

export default VisitedCountrySchema;
