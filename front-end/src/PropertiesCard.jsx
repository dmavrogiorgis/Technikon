import { Button } from "@mui/material";
import DeleteButton from "./DeleteProperty";

const PropertiesCard = ({ pin, address, yearConstruction, type }) => (
  <div className="bg-gray-200 p-4 rounded">
    <h3 className="text-lg font-bold text-gray-800">{pin}</h3>
    <h3 className="text-lg font-bold text-gray-800">{address}</h3>
    <h3 className="text-lg font-bold text-gray-800">{yearConstruction}</h3>
    <h3 className="text-lg font-bold text-gray-800">{type}</h3>
    <img src = "\img\pexels-curtis-adams-1694007-3555615.jpg"></img>
    <Button color="primary">Delete Property
        <DeleteButton itemId={itemId} />
    </Button>

    <Button color="primary">
    <Link to="/updateProp">Update property</Link>
    </Button>
  </div>
);

export default PropertiesCard;
