const CountryMapConfigSchema = `
  type CountryMapConfig {
    mapName: String!
    mapColor: String!
    rotate: [Float!]!
    scale: Float!
    mapData: String!
  }
`;

export default CountryMapConfigSchema;
