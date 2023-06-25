import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function ProductsView(){

    const[drivers, setDrivers] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/products/findAll", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setDrivers(result);
        }).catch((err) =>{
            console.log(err);
        });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        //window.location.href = "/AddIngredient"
    }

    return(

        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a  href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a class="active" href="/ProductsView"> Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className='wrapper'>
            <h1>View All Products</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Name</th>
                    <th></th>
                </tr>
                {drivers.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.productName}</td>
                            <td>
                                <button onClick={(e) => {
                                    e.preventDefault()

                                    localStorage.setItem('productId', val.id)

                                    window.location.href="/Recipe"

                                }}>View</button>
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>
        <div class="wrapper">
            <button onClick={navigateToAddNew}>Add New Product</button>
        </div>
        </body>
    )
}
export default ProductsView;