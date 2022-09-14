import React, { useEffect, useState } from "react";
import { ComposableMap, Geographies, ZoomableGroup } from "react-simple-maps";
import CountryMapConfig from "../entities/CountryMapConfig";
import VisitedCountry from "../entities/VisitedCountry";
import CountryDetailsPopup from "./CountryDetails/CountryDetailsPopup";
import RegionGeography from "./RegionGeography";

const geoUrl =
  "https://raw.githubusercontent.com/deldersveld/topojson/master/world-countries.json";

const CountriesMap = () => {
  const [open, setOpen] = useState(false);
  const [selectedCountryMapName, setSelectedCountryMapName] = useState<
    string | undefined
  >(undefined);
  const [visitedCountriesMapConfigs, setVisitedCountriesMapConfigs] =
    useState<CountryMapConfig[]>();

  useEffect(() => {
    fetch("http://localhost:4000/graphql", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        query: `
          {
            countries {
              mapConfig {
                mapName
                mapColor
                rotate
                scale
                mapData
              }
            }
          }
      `,
      }),
    })
      .then((res) => res.json())
      .then((result) => {
        setVisitedCountriesMapConfigs(
          result.data.countries.map(
            (country: VisitedCountry) => country.mapConfig
          )
        );
      });
  }, []);

  const closeModal = () => setOpen(false);

  const selectCountry = (countryMapName: string) => {
    setSelectedCountryMapName(countryMapName);
    setOpen(true);
  };

  return (
    <div>
      {visitedCountriesMapConfigs && (
        <ComposableMap>
          <ZoomableGroup center={[38, 43]} zoom={3}>
            <Geographies geography={geoUrl}>
              {({ geographies }) =>
                geographies.map((geo) => (
                  <RegionGeography
                    key={geo.rsmKey}
                    geo={geo}
                    onRegionClick={(countryMapName) => {
                      selectCountry(countryMapName);
                    }}
                    currentRegion={visitedCountriesMapConfigs.find(
                      (mapConfig) => mapConfig.mapName === geo.properties.name
                    )}
                  />
                ))
              }
            </Geographies>
          </ZoomableGroup>
        </ComposableMap>
      )}
      {selectedCountryMapName && (
        <CountryDetailsPopup
          open={open}
          onCloseModal={closeModal}
          selectedCountryName={selectedCountryMapName}
        />
      )}
    </div>
  );
};

export default CountriesMap;
