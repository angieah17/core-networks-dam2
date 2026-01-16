import axios from "axios";

// https://jsonplaceholder.typicode.com
// Uso
// apiUsers.get('/users');


export const apiUsers = axios.create(
    {
        baseURL: 'https://jsonplaceholder.typicode.com',
        timeout: 5000, //tiempo de espera
        headers: {
            "Content-Type": 'application/json'
        }
    }



)

// https://fakestoreapi.com/products/1
// Uso
// apiProducts.get('/products');

export const apiProducts = axios.create(
    {
        baseURL: 'https://fakestoreapi.com',
        timeout: 5000,
        headers:{
            "Content-Type": 'application/json'
        }
    }
)

export const apiPicsum = axios.create(
    {
        baseURL: 'https://picsum.photos',
        timeout: 5000,
        headers:{
            "Content-Type": 'application/json'
        }
    }
)

export const apiCliente = axios.create(
    {
        baseURL: 'https://693fee14993d68afba6a3d59.mockapi.io/cliente',
        timeout: 5000,
        headers:{
            "Content-Type": 'application/json'
        }
    }
)

export const apliClienteSpring = axios.create(
    {
        baseURL: 'http://localhost:8080/cliente/rest/api',
        timeout: 5000,
        headers:{
            "Content-Type": 'application/json'
        }
    }
)

export const apiUsuario = axios.create(
    {
        baseURL: 'https://69411a32686bc3ca8165a154.mockapi.io/customer',
        timeout: 5000,
        headers:{
            "Content-Type": 'application/json'
        }
    }
)
