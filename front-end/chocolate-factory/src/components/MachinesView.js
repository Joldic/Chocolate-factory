import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function MachinesView(){

    const[machines, setMachines] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/machines/getAllMachines", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setMachines(result);
        }).catch((err) =>{
            console.log(err);
        });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        window.location.href = "/AddMachine"
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
        <div className='wrapper'>
            <h1>View All Machines</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Input Quantity</th>
                    <th>State</th>
                    <th>Type</th>
                    <th>Procesed units per hour</th>
                    <th></th>
                </tr>
                {machines.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.name}</td>
                            <td>{val.inputQuantity}</td>
                            <td>{val.state}</td>
                            <td>{val.type}</td>
                            <td>{val.workingDays}</td>
                            <td>
                                <button onClick={(e) => {
                                    e.preventDefault()

                                    localStorage.setItem('machineId', val.id)

                                    window.location.href="/OneMachineView"

                                }}>View</button>
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>
        <div class="wrapper">
            <button onClick={navigateToAddNew}>Add New Machine</button>
        </div>
        </body>
    )
}
export default MachinesView;