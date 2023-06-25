import { useEffect, useState } from "react";
import './styles/AddNewForm.css'

function DeliveredOrdersView(){

    const[orders, setOrders] = useState([])
    const[month, setMonth] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    const handleChoose = () => {
        fetch("http://localhost:8081/api/orders/getDeliveredOrders/"+month, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setOrders(result);              
            console.log(result);

            if(result.length === 0){
                document.getElementById('messageTable').hidden=false;
                document.getElementById('ordersTable').hidden=true;
            }else{
                document.getElementById('messageTable').hidden=true;
                document.getElementById('ordersTable').hidden=false;
            }            
        });
    }

    return(
        <body>
            <div class="topnav">
                <a href="/DeliveryManagerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a className='active' href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div> 
            <div className="wrapper" id="chooseDiv">
                    <fieldset>
                    <p><strong>Delivered orders for month</strong></p>
                    <select onChange={(e) => {setMonth(e.target.value)}}>
                            <option value="">---Select month</option>
                            <option value="JANUARY">January 2023</option>
                            <option value="FEBRUARY">February 2023</option>
                            <option value="MARCH">March 2023</option>
                            <option value="APRIL">April 2023</option>
                            <option value="MAY">May 2023</option>
                            <option value="JUNE">June 2023</option>
                            <option value="JULY">July 2023</option>
                            <option value="AUGUST">August 2023</option>
                            <option value="SEPTEMBER">September 2023</option>
                            <option value="OCTOBER">October 2023</option>
                            <option value="NOVEMBER">November 2023</option>
                            <option value="DECEMER">December 2023</option>
                    </select><br/>
                    <button onClick={handleChoose}>Select</button>
                    </fieldset>
                </div>
            <div className="wrapper">
                <table id="messageTable" hidden='true'>
                    <tr>
                        <p><strong>Does not delivered orders for selected month.</strong></p>
                    </tr>
                </table>
                <table id="ordersTable" hidden='true'>
                    <tr>
                        <th>Adderss</th>
                        <th>City</th>
                        <th>Date</th>
                        <th>Total price</th>
                        <th>Total weight</th>
                        <th>Status</th>
                        <th>User</th>
                        <th>Tour</th>
                    </tr>
                    {orders.map((val, key) => {
                        return(
                            <tr>
                                <td>{val.address}</td>
                                <td>{val.city}</td>
                                <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                                <td>{val.totalPrice}</td>
                                <td>{val.totalWeight}</td>
                                <td>{val.status}</td>
                                <td>
                                    <div>
                                        <p>First name: {val.userDTO.firstName}</p>
                                        <p>Last name: {val.userDTO.lastName}</p>
                                        <p>Username: {val.userDTO.username}</p>
                                        <p>Email: {val.userDTO.email}</p>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <p>City: {val.tourDTO.city}</p>
                                        <p>Date: {val.tourDTO.date[2]+'.'+val.tourDTO.date[1]+'.'+val.tourDTO.date[0]}</p>
                                        {val.tourDTO.engagementsDTO.map((val2, key2) => {
                                            return(
                                                <div>
                                                    <p>Driver: {val2.driverDTO.firstName+' '+val2.driverDTO.lastName}</p>
                                                    <p>Truck: {val2.truckDTO.name+', '+val2.truckDTO.registrationNumber}</p>
                                                </div>
                                            )
                                        })}
                                    </div>
                                </td>
                            </tr>
                        )
                    })}
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <button onClick={
                                (e) => {
                                    e.preventDefault();
                                    localStorage.setItem('month', month);
                                    window.location.href="/ToursReport";
                                }
                            }>Generate report pdf</button>
                        </td>
                    </tr>
                </table>
            </div>
        </body>
    )

}
export default DeliveredOrdersView;