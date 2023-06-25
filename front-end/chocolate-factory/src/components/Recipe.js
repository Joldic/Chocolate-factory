import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function Recipe(){

    const[drivers, setDrivers] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('productId')

    useEffect(() => {
        fetch("http://localhost:8081/api/products/recipe/" + id, {
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
        window.location.href = "/AddIngredient"
    }

    return(

        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView"> Products Review</a>
            <a className="active" href="/Recipe"> Recipe Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className='wrapper'>
            <h1>Recipe</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                {drivers.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.name}</td>
                            <td>{val.quantity}</td>
                            <td>
                                <button onClick={(e) => {
                                    e.preventDefault()

                                    //localStorage.setItem('ingredientId', val.id)

                                    //window.location.href="/OneIngredientView"

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
export default Recipe;