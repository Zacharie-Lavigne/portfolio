type RegionMapConfig = {
  mapName: string;
  mapColor: string;
};

const RegionMapConfigSchema = `
  type RegionMapConfig {
    mapName: String!
    mapColor: String!
  }
`;

export default RegionMapConfig;
