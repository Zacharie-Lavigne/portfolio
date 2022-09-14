import React from "react";
import { Divider } from "primereact/divider";
import { Accordion, AccordionTab } from "primereact/accordion";
import VisitedCountry from "../../entities/VisitedCountry";

type VisitedRegionsAccordionProps = {
  selectedCountry: VisitedCountry;
};

const VisitedRegionsAccordion = ({
  selectedCountry,
}: VisitedRegionsAccordionProps) => {
  return (
    <div>
      <Divider align="center">
        <div>Endroits visités</div>
      </Divider>
      <Accordion>
        {selectedCountry.visitedRegions.map((region) => (
          <AccordionTab header={region.name}>
            <div>{region.description}</div>
            {region.blogArticles.length > 0 && (
              <div>
                <Divider align="center">
                  <div>Articles de blogue</div>
                </Divider>
                {region.blogArticles.map((article) => (
                  <div>
                    <a href={article.url}>{article.title}</a>
                  </div>
                ))}
              </div>
            )}
          </AccordionTab>
        ))}
      </Accordion>
    </div>
  );
};

export default VisitedRegionsAccordion;
