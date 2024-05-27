import { useState, useEffect } from "react";
import PropertiesList from "./PropertiesList";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import PropertyPost from "./PropertyPost";
import { useNavigate } from "react-router-dom";

const PropertiesListContainer = () => {
  const [properties, setProperties] = useState([]);
  const [isLoading, setLoading] = useState(false);
  const [error, setError] = useState('');


  useEffect(() => {
    fetch(`http://localhost:8080/api/owner/1/property`)
      .then((res) => res.json())
      .then((data) => {
        setProperties(data);
      });
  }, []);

  return (
    <>
    <PropertiesList
      properties={properties}
    
    />
    <Button color="primary">
      <Link to="/addProp">Add new property</Link>
      </Button>
    
  
  </>
  );
};

export default PropertiesListContainer;
