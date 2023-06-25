import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function IngredientsView(){

    const[ingredients, setIngredients] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/warehouse/ingredients", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setIngredients(result);
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
            <a class="active" href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView"> Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>

        </div>
        <div className='wrapper'>
            <h1>View All Ingredients</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                {ingredients.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.ingredientName}</td>
                            <td>{val.quantity}</td>
                            <td>
                                <button onClick={(e) => {
                                    e.preventDefault()

                                    localStorage.setItem('ingredientId', val.id)

                                    window.location.href="/OneIngredientView"

                                }}>View</button>
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>
        <div class="wrapper">
            {/*<button onClick={navigateToAddNew}>Add New Ingredient</button>*/}
        </div>
        </body>
    )
}
export default IngredientsView;