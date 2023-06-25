import { useEffect, useState } from "react";
import './styles/AddNewForm.css';

function AddIngredient(){
    const [name, setName] = useState('');
   // const [quantity, setQuantity] = useState('');


    const handleSubmit = (e) =>{
        e.preventDefault()

        var test = JSON.parse(localStorage.getItem('testToken'));
        const newIngredient = {name}

        fetch("http://localhost:8081/api/ingredients/createNewIngredient",{
            headers: {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"POST",
            body:JSON.stringify(newIngredient)
        }).then(()=>{
            console.log("New ingredient added")
            alert("New ingredient added")
            window.location.reload()
        }).catch((err)=>{
            console.log(err)
            alert("Something went wrong!")
            window.location.reload();
        });
    }

    return(
        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a  href="/MachinesView">Machines Review</a>
            <a class="active" href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className="wrapper">
            <form onSubmit={handleSubmit}>
                <h1>Add New Ingredient</h1>
                <fieldset>
                    <label>
                        <p>Name</p>
                        <input id="name" name = "name" onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <button type="submit">Submit</button>
            </form>
        </div>
        </body>
    )
}

export default AddIngredient;