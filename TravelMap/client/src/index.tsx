import React from "react";
import ReactDOM from "react-dom";

import "./styles.css";

import CountriesMap from "./components/CountriesMap";

function App() {
  return (
    <div>
      <CountriesMap />
    </div>
  );
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
