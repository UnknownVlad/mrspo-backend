import {BOOK_EDIT, LOGIN_ROUTE, REGISTRATION_ROUTE, USER_ROUTE} from "./utils/consts";
import Login from "./pages/LoginPage/Login";
import {UserPage} from "./pages/UserPage/UserPage";
import {BookEditPage} from "./pages/BookEditPage/BookEditPage";

export const privateRoutes = [
    {
        path: USER_ROUTE,
        Component: UserPage,
    },
    {
        path: BOOK_EDIT,
        Component: BookEditPage,
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