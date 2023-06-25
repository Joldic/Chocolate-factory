import { useEffect, useState } from "react";
import './styles/AddNewForm.css';

function AddMachine(){
    const [name, setName] = useState('');
    const [inputQuantity, setInputQuantity] = useState('');
    const [state, setState] = useState('');
    const [workingDays, setWorkingDays] = useState('');
    const [type, setType] = useState('')

    const handleSubmit = (e) =>{
        e.preventDefault()

        var test = JSON.parse(localStorage.getItem('testToken'));
        const newMachine = {name, inputQuantity, state, workingDays, type}

        fetch("http://localhost:8081/api/machines/createNewMachine",{
            headers: {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"POST",
            body:JSON.stringify(newMachine)
        }).then(()=>{
            console.log("New machine added")
            alert("New machine added")
            window.location.href="/MachinesView";
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
            <a class="active" href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView">Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className="wrapper">
            <form onSubmit={handleSubmit}>
                <h1>Add New Machine</h1>
                <fieldset>
                    <label>
                        <p>Name</p>
                        <input id="name" name = "name" onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Input Quantity</p>
                        <input id="inputQuantity" name="inputQuantity" onChange={(e)=>setInputQuantity(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>State</p>
                        <input id="state" name="state" onChange={(e)=>setState(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Type</p>
                        <input id="type" name="type" onChange={(e)=>setType(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Working days</p>
                        <input id="workingDays" name="workingDays" onChange={(e)=>setWorkingDays(e.target.value)}/>
                    </label>
                </fieldset>
                <button type="submit">Submit</button>
            </form>
        </div>
        </body>
    )
}

export default AddMachine;