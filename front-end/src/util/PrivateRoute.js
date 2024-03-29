import React from 'react';
import {useLocalStorage} from "../hooks/useLocalStorage";
import {Navigate} from "react-router-dom";

const PrivateRoute = ({children}) => {

    const jwt = localStorage.getItem("jwt");

    return jwt ? children : <Navigate to="/login"/>;
};

export default PrivateRoute;