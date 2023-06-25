import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function InProductionOrders(){

    const[products, setProducts] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/orders/getInProductionOrdersPM", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setProducts(result);
        }).catch((err) =>{
            console.log(err);
        });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        window.location.href = "/AddIngredient"
    }

    return(

        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView">Products Review</a>
            <a className="active" href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className='wrapper'>
            <h1>View All Production Requests</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Address</th>
                    <th>City</th>
                    <th>Date</th>
                    <th>Products</th>
                    <th></th>
                </tr>
                {products.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.address}</td>
                            <td>{val.city}</td>
                            <td>{val.deliveryDate}</td>
                            <td>
                                {
                                    val.orderedProductsDTO.map((val2, key2)=>{

                                        return(
                                            <div>
                                                <p>Name: <strong>{val2.productName} </strong></p>
                                                <p>Quantity:<strong> {val2.quantity} </strong></p>
                                            </div>
                                        )

                                    })}
                            </td>
                            <td>
                                <button onClick={(e) => {
                                    e.preventDefault()

                                    localStorage.setItem('productRequestId', val.id)

                                    window.location.href="/OneProductionRequestReview"

                                }}>View</button>
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>
        </body>
    )
}
export default InProductionOrders;