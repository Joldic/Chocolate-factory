import { useEffect, useRef, useState } from "react";
import './styles/AddNewForm.css'

function ViewOneTruck(){

    const[truck, setTruck] = useState([])
    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('truckId')

    useEffect(() => {
        fetch("http://localhost:8081/api/trucks/"+id, {
            headers : {
                'Content-Type':'application/json',
                'Accept':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setTruck(result);
        }).catch((err)=>{
            console.log(err);
        });
    }, []);

    const ref = useRef(null)

    const [name, setName] = useState('');
    const [registrationNumber, setRegistrationNumber] = useState('');
    const [stringCapacity, setCapacity] = useState('');
    var select = document.getElementById("driveability");

    const handleUpdate = (e) =>{
        e.preventDefault()

        var capacity;
        if(stringCapacity == ""){
            capacity = truck.capacity
        }else{
            capacity = parseFloat(stringCapacity)
        }

        var driveability = select.options[select.selectedIndex].value;
        const updatedTruck = {name, registrationNumber, capacity, driveability}


        fetch("http://localhost:8081/api/trucks/update/"+id, {
                headers : {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "PUT",
                body : JSON.stringify(updatedTruck)
        }).then(()=>{
                alert("Selected truck updated.")
                window.location.reload()
        }).catch((err)=>{
                console.log(err)
                alert("Something went wrong!")
                window.location.reload();
        });
    
    }

    const handleDelete = (e) =>{
        e.preventDefault()

        
        fetch("http://localhost:8081/api/trucks/delete/"+id, {
                headers : {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "DELETE"
        }).then(()=>{
                alert("Selected truck is successfuly deleted.")
                window.location.href="/TrucksView";
        }).catch((err)=>{
                console.log(err)
                alert("Something went wrong!")
                window.location.reload();
        });
        
    }

    return(
        <body>
        <div class="topnav">
            <a href="/DeliveryManagerHome">Home</a>
            <a href="/DriversView">Drivers Review</a>
            <a class="active" href="/TrucksView">Trucks Review</a>
            <a href="/OrdersView">Completed Orders Review</a>
            <a href="/DeliveredOrdersView">Delivered Orders Review</a>
            <a href="/EngagementsView">Engagements Review</a>
            <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
            <a href="/ViewPlanForToday">Plan For Today Review</a>
            <a href="/">Logout</a>
        </div>
        <div className="wrapper">
            <form onSubmit={handleUpdate}>
            <h1>Selected Truck</h1>
                <fieldset>
                    <label>
                        <p>Name</p>
                        <input id="name" name="name" ref={ref} defaultValue={truck.name} onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Registration Number</p>
                        <input id="registrationNumber" name="registrationNumber" ref={ref} defaultValue={truck.registrationNumber} onChange={(e)=>setRegistrationNumber(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Capacity</p>
                        <input id="capacity" name="capacity" type="number" step={0.1} ref={ref} defaultValue={truck.capacity} onChange={(e)=>setCapacity(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Driveability</p>
                        <select id="driveability">
                            <option value={true}>True</option>
                            <option value={false}>False</option>
                        </select>
                    </label>
                </fieldset>
            <button type="submit">Update</button>
            <button onClick={handleDelete}>Delete</button>
            </form>
        </div>
        </body>
    )
}

export default ViewOneTruck;