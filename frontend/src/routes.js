import {LOGIN_ROUTE, REGISTRATION_ROUTE, USER_ROUTE} from "./utils/consts";
import Login from "./pages/LoginPage/Login";
import {UserPage} from "./pages/UserPage/UserPage";

export const privateRoutes = [
    {
        path: USER_ROUTE,
        Component: UserPage,
    }
];


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