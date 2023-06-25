import { useEffect, useState } from 'react'
import './styles/AddNewForm.css'

function ViewPlanForTomorrow(){

    const[tours, setTours] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/tours/getPlanForTomorrow", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setTours(result);
            console.log(result);

            if(result.length!==0){
                document.getElementById('planTable').hidden=false;
            }else{
                document.getElementById('messageTable').hidden=false;
            }
        });
    }, []);

    return(
        <body>
            <div class="topnav">
                <a href="/DeliveryManagerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a className='active' href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div> 
            <div className='wrapper'>
                <h1>
                    Transportation plan for tomorrow
                </h1>
            </div>
            <div className='wrapper'>
                <table id='messageTable' hidden='true'>
                    <tr>
                        <p><strong>Does not exist delivery plan for tomorrow.</strong></p>
                    </tr>
                    <tr>
                        <p>Create plan -{">"} <button onClick={(e) => {
                            e.preventDefault();
                            window.location.href='/OrdersView'
                        }}>Go to create</button> </p>
                    </tr>
                </table>
                <table id='planTable' hidden='true'>
                    <tr>
                        <th>City</th>
                        <th>Date</th>
                        <th>Orders</th>
                        <th>Driver</th>
                        <th>Truck</th>
                    </tr>
                    {tours.map((val, key) => {
                        return(
                            <tr>
                                <td>{val.city}</td>
                                <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                                <td>
                                    {val.ordersDTO.map((val2, key2) => {
                                        return(
                                            <div>
                                                <p><strong>Order: </strong></p>
                                                <p>Address: {val2.address}</p>
                                                <p>City: {val2.city}</p>
                                                <p>Date: {val2.date[2]+'.'+val2.date[1]+'.'+val2.date[0]}</p>
                                                <p>Status : {val2.status}</p>
                                                <p>Total price: {val2.totalPrice}</p>
                                                <p>Total weight: {val2.totalWeight}</p>
                                                <p>User: {val2.userDTO.firstName+' '+val2.userDTO.lastName}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                                <td>
                                    {val.engagementsDTO.map((val3, key3) =>{
                                        return(
                                            <div>
                                                <p><strong>Driver:</strong></p>
                                                <p>Name: {val3.driverDTO.firstName+' '+val3.driverDTO.lastName}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                                <td>
                                    {val.engagementsDTO.map((val4, key4) =>{
                                        return(
                                            <div>
                                                <p><strong>Truck: </strong>{val4.truckDTO.name}</p>
                                                <p>Registration number: {val4.truckDTO.registrationNumber}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                            </tr>
                        )
                    })}
                    </table>
            </div>
        </body>
    )
}

export default ViewPlanForTomorrow;