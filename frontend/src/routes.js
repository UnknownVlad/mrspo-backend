import {LOGIN_ROUTE, REGISTRATION_ROUTE} from "./utils/consts";
import Login from "./pages/LoginPage/Login";


export const publicRoutes = [
    {
        path: LOGIN_ROUTE,
        Component: Login,
    },
    {
        path: REGISTRATION_ROUTE,
        Component: Login,
    },
]