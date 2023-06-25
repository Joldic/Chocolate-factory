import { useEffect, useRef, useState } from 'react';
import './styles/AddNewForm.css'

function OneIngredientView(){

    const[machine, setMachine] = useState([])
    const ref = useRef(null)

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [quantity, setQuantity] = useState('');
    const [expiringDate, setExpiringDate] = useState('');

    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('ingredientId')

    useEffect(() => {
        fetch("http://localhost:8081/api/ingredients/getIngredient/"+id, {
            headers : {
                'Content-Type':'application/json',
                'Accept':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"GET"
        }).then(res=>res.json()).then((result)=>{
            setMachine(result);
            setName(result.name);
            setQuantity(result.quantity);
        });
    }, []);



    const handleUpdate = (e) =>{
        e.preventDefault()

        const updatedMachine = {id, name , quantity};


        fetch("http://localhost:8081/api/ingredients/updateIngredient", {
            headers : {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "PUT",
            body : JSON.stringify(updatedMachine)
        }).then(()=>{
            console.log("Selected machine updated.")
            alert("Selected machine updated.")
            window.location.reload()
        }).catch((err)=>{
            console.log(err)
            alert("Something went wrong!")
            window.location.reload();
        });


    }

    const handleDelete = (e) =>{
        e.preventDefault()


        fetch("http://localhost:8081/api/ingredients/deleteIngredient/"+id, {
            headers : {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "DELETE"
        }).then(()=>{
            console.log("Selected ingredient is successfuly deleted.")
            alert("Selected ingredient is successfuly deleted.")
            window.location.href="/IngredientsView.js";
        }).catch((err)=>{
            console.log(err);
            alert("Something went wrong!")
            window.location.reload();
        });

    }

    return(
        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a class="active"  href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView">Products Review</a>
        </div>
        <div className="wrapper">
            <form onSubmit={handleUpdate}>
                <h1>Selected Ingredient</h1>
                <fieldset>
                    <label>
                        <p>Name</p>
                        <input id="name" name="name" ref={ref} defaultValue={machine.name} onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Quantity</p>
                        <input id="quantity" name="quantity" ref={ref} defaultValue={machine.quantity} onChange={(e)=>setQuantity(e.target.value)}/>
                    </label>
                </fieldset>
                <button type="submit">Update</button>
                <button onClick={handleDelete}>Delete</button>
            </form>
        </div>
        </body>
    )

}
export default OneIngredientView;