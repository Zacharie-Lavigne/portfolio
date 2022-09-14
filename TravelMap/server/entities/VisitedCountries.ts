import VisitedCountry from "./VisitedCountry";

const visitedCountries: VisitedCountry[] = [
  {
    name: "France",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    mapConfig: {
      mapName: "France",
      mapColor: "#0000FF",
      rotate: [-4.0, -46.2, 0],
      scale: 3400,
      mapData: "fr-departements.json",
    },
    visitedRegions: [
      {
        name: "Paris",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [
          {
            title: "Titre d'article",
            url: "https://",
          },
        ],
        mapConfig: {
          mapName: "Paris",
          mapColor: "red",
        },
      },
    ],
  },
  {
    name: "Autriche",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    mapConfig: {
      mapName: "Austria",
      mapColor: "#FFF0F5",
      rotate: [-13.5, -47.5, 0],
      scale: 5400,
      mapData: "aust-regions.json",
    },
    visitedRegions: [
      {
        name: "Vienne",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [
          {
            title: "Titre d'article",
            url: "https://",
          },
          {
            title: "Titre d'article",
            url: "https://",
          },
        ],
        mapConfig: {
          mapName: "Wien",
          mapColor: "red",
        },
      },
    ],
  },
  {
    name: "Hongrie",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    mapConfig: {
      mapName: "Hungary",
      mapColor: "#014421",
      rotate: [-19.5, -46.5, 0],
      scale: 5400,
      mapData: "hun-regions.json",
    },
    visitedRegions: [
      {
        name: "Budapest",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [
          {
            title: "Titre d'article",
            url: "https://",
          },
        ],
        mapConfig: {
          mapName: "Budapest",
          mapColor: "red",
        },
      },
      {
        name: "Tokaj",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [
          {
            title: "Titre d'article",
            url: "https://",
          },
        ],
        mapConfig: {
          mapName: "Borsod-Abaúj-Zemplén",
          mapColor: "red",
        },
      },
      {
        name: "Eger",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [
          {
            title: "Titre d'article",
            url: "https://",
          },
        ],
        mapConfig: {
          mapName: "Eger",
          mapColor: "red",
        },
      },
      {
        name: "Siofok",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        blogArticles: [],
        mapConfig: {
          mapName: "Somogy",
          mapColor: "red",
        },
      },
    ],
  },
];

export default visitedCountries;
