import axios from "axios";

const API_BASE_URL = "http://localhost:8080";
const API_OWNER_URL = "/api/owner"

export const API_LOGIN = `${API_BASE_URL}/auth/login`;
export const API_SIGNUP = `${API_BASE_URL}/auth/signup`;

export const API_OWNER_SEARCH = `${API_BASE_URL}${API_OWNER_URL}/search`;
export const API_OWNER_UPDATE = `${API_BASE_URL}${API_OWNER_URL}/update`;
export const API_OWNER_DELETE = `${API_BASE_URL}${API_OWNER_URL}/delete`;

export const fetchPropertyOwner = async (token, tin, email, username) => {
  return axios.get(API_OWNER_SEARCH, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    params: {
      tin: tin,
      email: email,
      username: username,
    }
  });
};

export const updatePropertyOwner = async (token, tin, updateData) => {
  return axios.put(`${API_OWNER_UPDATE}/${tin}`, updateData, {
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
};

export const deletePropertyOwner = async (token, tin) => {
  return axios.delete(`${API_OWNER_DELETE}/${tin}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
};