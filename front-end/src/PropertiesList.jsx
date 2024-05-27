import PropertiesCard from "./PropertiesCard";

const PropertiesList = ({ properties, title }) => (
  <div>
    <h1 className="text-2xl font-bold text-gray-800">{title}</h1>
    <hr className="my-3" />
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 py-4">
      {Boolean(properties.length) &&
        properties.map((property) => (
          <PropertiesCard key={property.id} {...property} />
        ))}
    </div>
  </div>
);

export default PropertiesList;
