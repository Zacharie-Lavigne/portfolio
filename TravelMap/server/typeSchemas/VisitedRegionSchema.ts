const VisitedRegionSchema = `
  type VisitedRegion {
    name: String!
    description: String!
    blogArticles: [BlogArticle!]!
    mapConfig: RegionMapConfig!
  }
`

export default VisitedRegionSchema