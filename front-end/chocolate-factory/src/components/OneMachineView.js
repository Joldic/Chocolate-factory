import { useEffect, useRef, useState } from 'react';
import './styles/AddNewForm.css'

function OneMachineView(){

    const[machine, setMachine] = useState([])
    const ref = useRef(null)

    const [name, setName] = useState('');
    const [inputQuantity, setInputQuantity] = useState('');
    const [state, setState] = useState('');
    const [workingDays, setWorkingDays] = useState('');
    const [type, setType] = useState('');

    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('machineId')

    useEffect(() => {
        fetch("http://localhost:8081/api/machines/getMachine/"+id, {
            headers : {
                'Content-Type':'application/json',
                'Accept':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"GET"
        }).then(res=>res.json()).then((result)=>{
            setMachine(result);
            setName(result.name);
            setInputQuantity(result.inputQuantity);
            setState(result.state);
            setWorkingDays(result.workingDays);
            setType(result.type);
        });
    }, []);



    const handleUpdate = (e) =>{
        e.preventDefault()

        const updatedMachine = {id, name, inputQuantity, state, workingDays, type};


        fetch("http://localhost:8081/api/machines/updateMachine", {
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


        fetch("http://localhost:8081/api/machines/deleteMachine/"+id, {
            headers : {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "DELETE"
        }).then(()=>{
            console.log("Selected machine is successfuly deleted.")
            alert("Selected machine is successfuly deleted.")
            window.location.href="/MachinesView";
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
            <a class="active" href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView"> Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>

        </div>
        <div className="wrapper">
            <form onSubmit={handleUpdate}>
                <h1>Selected Machine</h1>
                <fieldset>
                    <label>
                        <p>First Name</p>
                        <input id="name" name="name" ref={ref} defaultValue={machine.name} onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Last Name</p>
                        <input id="inputQuantity" name="inputQuantity" ref={ref} defaultValue={machine.inputQuantity} onChange={(e)=>setInputQuantity(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>State</p>
                        <input id="state" name="state" ref={ref} defaultValue={machine.state} onChange={(e)=>setState(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>State</p>
                        <input id="type" name="type" ref={ref} defaultValue={machine.type} onChange={(e)=>setType(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Working Days</p>
                        <input id="workingDays" name="workingDays" ref={ref} defaultValue={machine.workingDays} onChange={(e)=>setWorkingDays(e.target.value)}/>
                    </label>
                </fieldset>
                <button type="submit">Update</button>
                <button onClick={handleDelete}>Delete</button>
            </form>
        </div>
        </body>
    )

}
export default OneMachineView;