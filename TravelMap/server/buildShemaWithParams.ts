import { buildSchema } from "graphql";

type buildSchemaParams = {
  typeSchemas: string[];
  querySchemas: string[];
};

const buildSchemaWithParams = ({
  typeSchemas,
  querySchemas,
}: buildSchemaParams) => {
  let schemaString = ``;
  typeSchemas.forEach((schema) => (schemaString += schema));
  schemaString += `type Query {`;
  querySchemas.forEach((schema) => (schemaString += schema));
  schemaString += `}`;

  return buildSchema(schemaString);
};

export default buildSchemaWithParams;
