import { useState, useContext} from "react";
import {fetchPropertyOwner, updatePropertyOwner, deletePropertyOwner} from '../api/index';
import { AuthContext } from "../contexts/AuthContext";
import SearchContainer from "./SearchContainer";
import ResponseContainer from "./ResponseContainer";
import UpdateModal from "./UpdateModal";

const UserManagement = ({children}) => {
    const [searchCriteria, setSearchCriteria] = useState({
        searchTin: '',
        searchEmail: '',
        searchUsername: '',
        isSubmitting: false
        });
        
    const [response, setResponse] = useState([]);
    const [isUpdateModalOpen, setUpdateModalOpen] = useState(false);

    const {authData} = useContext(AuthContext);

    const handleOnChange = (e) => {
        setSearchCriteria((state) => ({...state, [e.target.id] : e.target.value}));
    };

    const handleSubmit = async (e) =>{
        e.preventDefault();
        setSearchCriteria({...searchCriteria, isSubmitting: true});

        try{
          const result = await fetchPropertyOwner(authData.token, 
            searchCriteria.searchTin || null, 
            searchCriteria.searchEmail || null,
            searchCriteria.searchUsername || null
          );
          setResponse({ data: result.data, success: true, error: null })
        }catch (error) {
          setResponse({ data: null, success: false, error: error.message });
        } finally {
          setSearchCriteria({...searchCriteria, isSubmitting: false});
        }
    }

    const handleUpdateClick = () => {
      setUpdateModalOpen(true);
    };

    const handleCloseModal = () => {
        setUpdateModalOpen(false);
    };

    const handleUpdate = async (userTin, updateData) => {
      try{
        const result = await updatePropertyOwner(authData.token, userTin, updateData);
        setResponse({ data: result.data, success: true, error: null })
      }catch (error) {
        setResponse({ data: null, success: false, error: error.message });
      } finally {
        setSearchCriteria({...searchCriteria, isSubmitting: false});
      }
      setUpdateModalOpen(false);
    };
  
    const handleDelete = async (userTin) => {
      console.log(userTin);
      try{
        const result = await deletePropertyOwner(authData.token, userTin);
        setResponse({ data: result.data, success: true, error: null })
      }catch (error) {
        setResponse({ data: null, success: false, error: error.message });
      } finally {
        setSearchCriteria({...searchCriteria, isSubmitting: false});
      }
    };
    
    return (
      <>
        <SearchContainer 
          searchCriteria={searchCriteria}
          handleOnChange={handleOnChange}
          handleSubmit={handleSubmit}  
        >
          {children}
        </SearchContainer>
        <ResponseContainer 
          response={response}
          handleUpdate={handleUpdateClick}
          handleDelete={() => handleDelete(response.data.tin)}
        />
        {response.success && response.data && (
          <UpdateModal
              open={isUpdateModalOpen}
              handleClose={handleCloseModal}
              handleUpdate={handleUpdate}
              userTin={response.data.tin}
          />
        )}
      </>
    );
};

export default UserManagement;