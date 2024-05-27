import React from 'react';
import axios from 'axios';

const DeleteButton = ({ itemId }) => {
  const handleDelete = async () => {
    try {
      
      await axios.delete(`http://localhost:8080/api/owner/3/property/${itemId}`);
      console.log('Item deleted successfully!');
      
    } catch (error) {
      console.error('Error deleting item:', error);
    }
  };

  return (
    <button onClick={handleDelete}>Delete Property</button>
  );
};

export default DeleteButton;