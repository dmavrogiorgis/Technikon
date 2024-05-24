import { jwtDecode } from 'jwt-decode';
import { useEffect } from 'react';

const useTokenExpiration = (authData, logout) => {
  useEffect(() => {
    if (authData && authData.token) {
      const { exp } = jwtDecode(authData.token);
      const now = Date.now() / 1000;

      if (exp < now) {
        logout();
      }
    }
  }, [authData, logout]);
};

export default useTokenExpiration;