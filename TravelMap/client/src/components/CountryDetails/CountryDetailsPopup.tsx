import React, { useState, useEffect } from "react";
import { Dialog } from "primereact/dialog";
import { Divider } from "primereact/divider";
import CountryDetailsMap from "./CountryDetailsMap";
import VisitedRegionsAccordion from "./VisitedRegionsAccordion";
import "./country-details.css";
import "primeicons/primeicons.css";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primereact/resources/primereact.css";
import VisitedCountry from "../../entities/VisitedCountry";

type CountryDetailsPopupProps = {
  open: boolean;
  onCloseModal: () => void;
  selectedCountryName: string;
};

const CountryDetailsPopup = ({
  open,
  onCloseModal,
  selectedCountryName,
}: CountryDetailsPopupProps) => {
  const [selectedCountry, setSelectedCountry] = useState<VisitedCountry>();

  useEffect(() => {
    fetch("http://localhost:4000/graphql", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        query: `
        query CountryByMapName($mapName: String!){
          countryByMapName(mapName: $mapName) {
            name
            description
            mapConfig {
              mapName
              mapColor
              rotate
              scale
              mapData
            }
            visitedRegions{
              name
              description
              mapConfig{
                mapName
                mapColor
              }
              blogArticles {
                title
                url
              }
            }
          }
        }
      `,
        variables: { mapName: selectedCountryName },
      }),
    })
      .then((res) => res.json())
      .then((result) => {
        console.log("12345:", selectedCountryName);
        setSelectedCountry(result.data.countryByMapName);
      });
  }, [selectedCountryName]);

  const closeModal = () => {
    console.log("Iciii");
    setSelectedCountry(undefined);
    onCloseModal();
  };

  return selectedCountry ? (
    <div>
      <Dialog
        header={selectedCountry.name}
        visible={open}
        style={{ width: "50vw" }}
        onHide={() => {
          closeModal();
          setSelectedCountry(undefined);
        }}
      >
        <div className="country-details-content-container">
          {selectedCountry.description}
          <Divider align="center">
            <div>Carte</div>
          </Divider>
          <div className="country-details-map-container">
            <CountryDetailsMap selectedCountry={selectedCountry} />
          </div>
          <div style={{ width: "100%" }}>
            <VisitedRegionsAccordion selectedCountry={selectedCountry} />
          </div>
        </div>
      </Dialog>
    </div>
  ) : (
    <div>
      <Dialog visible={open} style={{ width: "50vw" }} onHide={closeModal}>
        <div className="country-details-content-container">
          SQUELETTE
          <Divider align="center">
            <div>Carte</div>
          </Divider>
          <div className="country-details-map-container">
            SQUELETTE DE CARTE
          </div>
          <div style={{ width: "100%" }}>SQUELETTE D'ACCORDEON</div>
        </div>
      </Dialog>
    </div>
  );
};

export default CountryDetailsPopup;
