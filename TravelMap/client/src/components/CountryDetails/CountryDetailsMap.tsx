import React, { useState, useEffect } from "react";
import { ProgressSpinner } from "primereact/progressspinner";
import { ComposableMap, Geographies, ZoomableGroup } from "react-simple-maps";
import RegionGeography from "../RegionGeography";
import VisitedCountry from "../../entities/VisitedCountry";

type CountryDetailsMapProps = {
  selectedCountry: VisitedCountry;
};

const CountryDetailsMap = ({ selectedCountry }: CountryDetailsMapProps) => {
  const [geography, setGeography] = useState<any>(undefined);
  useEffect(() => {
    fetch("./" + selectedCountry.mapConfig.mapData, {
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    }).then((res) =>
      res.json().then((data) => {
        setGeography(data);
      })
    );
  }, []);

  return geography ? (
    <ComposableMap
      projection="geoAzimuthalEqualArea"
      style={{ width: "100%", height: "100%" }}
      projectionConfig={{
        rotate: selectedCountry.mapConfig.rotate,
        scale: selectedCountry.mapConfig.scale,
      }}
    >
      <ZoomableGroup>
        <Geographies geography={geography}>
          {({ geographies }) =>
            geographies.map((geo) => (
              <RegionGeography
                key={geo.rsmKey}
                geo={geo}
                hoverable={false}
                currentRegion={
                  selectedCountry.visitedRegions.find(
                    (region) => region.mapConfig.mapName === geo.properties.name
                  )?.mapConfig
                }
              />
            ))
          }
        </Geographies>
      </ZoomableGroup>
    </ComposableMap>
  ) : (
    <ProgressSpinner />
  );
};

export default CountryDetailsMap;
