import React, {createContext, useState, useEffect} from "react";
import axios from 'axios';
import {API_LOGIN} from '/src/api';
import useTokenExpiration from "../hooks/useTokenExpiration";

const AuthContext = createContext();

const AuthProvider = ({children}) => {
    const [authData, setAuthData] = useState(null);
    const [loading, setLoading] = useState(true);

    const logout = () => {
        setAuthData(null);
        localStorage.removeItem('authData');
    };
    
    useTokenExpiration(authData, logout);

    useEffect(()=>{
        const localData = localStorage.getItem('authData');
        if(localData){
            setAuthData(JSON.parse(localData));
        }
        setLoading(false);
    }, []);

    const login = async (userData) => {
        try{
            const response = await axios.post(API_LOGIN, 
                userData,
                {
                    headers: { 'Content-Type': 'application/json'}
                }
            );
            
            if(response.status === 200){
                localStorage.setItem('authData', JSON.stringify(response.data));
                const hasAdminRole = isAdmin(response.data)
                setAuthData(response.data); // Update the state here
                return {
                    success: true,
                    isAdmin: hasAdminRole
                };
            }
        } catch (error) {
            return {
                success: false,
                error: error.message
            };
        }
    };

    const isAdmin = (authData) => {
        return authData && authData.authorities && authData.authorities.some(obj => obj.authority === 'ROLE_ADMIN');
    };

    const isUser = (authData) => {
        return authData && authData.authorities && authData.authorities.some(obj => obj.authority === 'ROLE_USER');
    };

    return (
        <AuthContext.Provider value={{authData, loading, isAdmin, isUser, login, logout}} >
            {children}
        </AuthContext.Provider>
    );

};

export {AuthContext, AuthProvider};

