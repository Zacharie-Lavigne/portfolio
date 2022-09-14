import BlogArticle from "./BlogArticle";
import RegionMapConfig from "./RegionMapConfig";

type VisitedRegion = {
  name: string;
  description: string;
  blogArticles: BlogArticle[];
  mapConfig: RegionMapConfig;
};

export default VisitedRegion;
