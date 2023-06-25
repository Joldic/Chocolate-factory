import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function ViewAllTrucks(){

    const[trucks, setTrucks] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/trucks/findAll", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setTrucks(result);
            console.log(trucks)
        });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        window.location.href = "/TruckCreate"
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
            <div className='wrapper'>
                <h1>
                    View All Trucks
                </h1>
            </div>
            <div className='wrapper'>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Registration Number</th>
                        <th></th>
                    </tr>
                    {trucks.map((val, key) => {
                        return(
                            <tr key={key}>
                                <td>{val.name}</td>
                                <td>{val.registrationNumber}</td>
                                <td>
                                    <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('truckId', val.id)

                                        window.location.href="/OneTruckView"
                                        
                                    }}>View</button>
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <div class="wrapper">
                <button onClick={navigateToAddNew}>Add New Truck</button>
            </div>
        </body>
    )

}
export default ViewAllTrucks;