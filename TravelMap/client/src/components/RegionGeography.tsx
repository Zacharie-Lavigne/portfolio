import React from "react";
import { Geography } from "react-simple-maps";
import CountryMapConfig from "../entities/CountryMapConfig";
import RegionMapConfig from "../entities/RegionMapConfig";

type RegionGeographyProps = {
  geo: any;
  currentRegion?: CountryMapConfig | RegionMapConfig;
  hoverable?: boolean;
  onRegionClick?: (selectedRegionName: string) => void;
};

const RegionGeography = ({
  geo,
  currentRegion,
  hoverable = true,
  onRegionClick,
}: RegionGeographyProps) => {
  if (currentRegion) {
    return (
      <Geography
        geography={geo}
        style={
          hoverable
            ? {
                default: {
                  fill: currentRegion.mapColor,
                  outline: "none",
                  opacity: 0.5,
                },
                hover: {
                  fill: currentRegion.mapColor,
                  outline: "none",
                },
              }
            : {
                default: {
                  fill: currentRegion.mapColor,
                  outline: "none",
                },
                hover: {
                  fill: currentRegion.mapColor,
                  outline: "none",
                },
              }
        }
        onClick={
          onRegionClick ? () => onRegionClick(currentRegion.mapName) : undefined
        }
      />
    );
  } else {
    return (
      <Geography
        key={geo.rsmKey}
        geography={geo}
        style={{
          default: {
            fill: "#D6D6DA",
            outline: "none",
          },
          hover: {
            fill: "#D6D6DA",
            outline: "none",
          },
        }}
      />
    );
  }
};

export default RegionGeography;
