import express from "express";
import cors from "cors";
import { graphqlHTTP } from "express-graphql";
import BlogArticleSchema from "./typeSchemas/BlogArticleSchema";
import VisitedRegionSchema from "./typeSchemas/VisitedRegionSchema";
import VisitedCountrySchema from "./typeSchemas/VisitedCountrySchema";
import RegionMapConfigSchema from "./typeSchemas/RegionMapConfigSchema";
import CountryMapConfigSchema from "./typeSchemas/CountryMapConfigSchema";
import buildSchemaWithParams from "./buildShemaWithParams";
import {
  countriesQuery,
  countriesQuerySchema,
  countryByMapNameQuery,
  countryByMapNameQuerySchema,
} from "./querries/CountryQuerries";

// Construct a schema, using GraphQL schema language
var schema = buildSchemaWithParams({
  typeSchemas: [
    BlogArticleSchema,
    VisitedRegionSchema,
    VisitedCountrySchema,
    RegionMapConfigSchema,
    CountryMapConfigSchema,
  ],
  querySchemas: [countriesQuerySchema, countryByMapNameQuerySchema],
});

// The root provides a resolver function for each API endpoint
var root = {
  countries: () => countriesQuery(),
  countryByMapName: (args: { mapName: string }) => countryByMapNameQuery(args),
};

var app = express();
app.use(cors({ origin: "*" }));
app.use(
  "/graphql",
  graphqlHTTP({
    schema: schema,
    rootValue: root,
    graphiql: true,
  })
);
app.listen(4000);
console.log("Running a GraphQL API server at http://localhost:4000/graphql");
