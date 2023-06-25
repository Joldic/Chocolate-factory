import { useState } from "react";
import './styles/AddNewForm.css';

function Create(){
    const [name, setName] = useState('');
    const [registrationNumber, setRegistrationNumber] = useState('');
    const [stringCapacity, setCapacity] = useState('');
    
    const handleSubmit = (e) => {
        e.preventDefault()

        var test = JSON.parse(localStorage.getItem('testToken'));

        var capacity = parseFloat(stringCapacity)
        var select = document.getElementById("driveability");
        var driveability = select.options[select.selectedIndex].value;
        const newTruck = {name, registrationNumber, capacity, driveability}

    
        fetch("http://localhost:8081/api/trucks/create", {
                headers : {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "POST",
                body : JSON.stringify(newTruck)
        }).then(()=>{
                alert("New truck added")
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
            <a href="/DeliveryManagerHome">Home</a>
            <a href="/DriversView">Drivers Review</a>
            <a class="active" href="/TrucksView">Trucks Review</a>
            <a href="OrdersView">Completed Orders Review</a>
            <a href="/DeliveredOrdersView">Delivered Orders Review</a>
            <a href="/EngagementsView">Engagements Review</a>
            <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
            <a href="/ViewPlanForToday">Plan For Today Review</a>
            <a href="/">Logout</a>
        </div>
        <div className="wrapper">
            <form onSubmit={handleSubmit}>
            <h1>Add New Truck</h1>
                <fieldset>
                    <label>
                        <p>Name</p>
                        <input id="name" name="name" onChange={(e)=>setName(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Registration Number</p>
                        <input id="registrationNumber" name="registrationNumber" onChange={(e)=>setRegistrationNumber(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Capacity</p>
                        <input id="capacity" name="capacity" type="number" step={0.1} onChange={(e)=>setCapacity(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Driveability</p>
                        <select id="driveability">
                            <option value="">--Please choose an option</option>
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </label>
                </fieldset>
            <button type="submit">Submit</button>
            </form>
        </div>
        </body>
    )
}

export default Create;