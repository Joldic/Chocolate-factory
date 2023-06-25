import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function IngredientRequests(){

    const[products, setProducts] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/ingredientRequests/getLatest", {
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
            <a href="/InProductionOrders">Orders in production</a>
            <a className="active" href="/IngredientRequests">Ingredient requests</a>

        </div>
        <div className='wrapper'>
            <h1>Ingredient Requests</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Ingredient</th>
                    <th>Quantity</th>
                    <th>Creation Date</th>
                    <th>Delivery Deadline Date</th>
                    <th></th>
                </tr>
                {products.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.ingredientDTO.name}</td>
                            <td>{val.quantity}</td>
                            <td>{val.creationDate}</td>
                            <td>{val.deliveryDeadlineDate}</td>
                            
                        </tr>
                    )
                })}
            </table>
        </div>
        </body>
    )
}
export default IngredientRequests;