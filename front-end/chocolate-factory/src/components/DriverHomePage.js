import { useEffect, useRef, useState } from "react";
import './styles/AddNewForm.css'
import jsPDF from 'jspdf';

function ViewPlanForTodayForDriver(){

    const[tours, setTours] = useState([])
    var orders = []
        
    var test = JSON.parse(localStorage.getItem('testToken'))
    var username = localStorage.getItem('user_userName')


    useEffect(() => {
        fetch("http://localhost:8081/api/tours/getPlanForTodayForDriver/"+username, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setTours(result);
            console.log(result);

            if(result.length!=0){
                document.getElementById('planTable').hidden=false;
                document.getElementById('orderTable').hidden=false;
            }else{
                document.getElementById('messageTable').hidden=false;
            }
        });
    }, []);

    const handleChange = (e) => {
        e.preventDefault()
    
        console.log(orders)

        fetch("http://localhost:8081/api/orders/changeOrderStatus", {
            headers : {
                'Content-Type':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "PUT",
            mode: 'cors',
            body : JSON.stringify(orders)
        }).then(() => {
            alert("Status changed!")
            window.location.reload()
        });

    }

    return(
        <body>
            <div class="topnav">
                <a className='active' href="/DriverHomePage">Plan For Today</a>
                <a href="/DriverTomorrowPlan">Plan For Tomorrow</a>
                <a href="/">Logout</a>
            </div>
            <div className='wrapper'>
                <h1>
                    Transportation plan for today
                </h1>
            </div>
            <div className="wrapper">
                <table id='messageTable' hidden='true'>
                    <tr>
                        <p><strong>Does not exist delivery plan for today.</strong></p>
                    </tr>
                </table>
                <table id='planTable' hidden='true'>
                    <tr>
                        <th>City</th>
                        <th>Date</th>
                        <th>Driver</th>
                        <th>Truck</th>
                    </tr>
                    {tours.map((val, key) => {
                        return(
                            <tr>
                                <td>{val.city}</td>
                                <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                                <td>
                                    {val.engagementsDTO.map((val2, key2) =>{
                                        return(
                                            <div>
                                                <p><strong>Driver:</strong></p>
                                                <p>Name: {val2.driverDTO.firstName+' '+val2.driverDTO.lastName}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                                <td>
                                    {val.engagementsDTO.map((val3, key3) =>{
                                        return(
                                            <div>
                                                <p><strong>Truck: </strong>{val3.truckDTO.name}</p>
                                                <p>Registration number: {val3.truckDTO.registrationNumber}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <div className="wrapper">
                <table id="orderTable" hidden = 'true'> 
                    <caption><strong>Orders</strong></caption>
                    <tr>
                        <th></th>
                    </tr>
                    {tours.map((val4, key4)=>{
                        return(
                            <tr>
                                <table>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    {val4.ordersDTO.map((val5, key5) => {
                                        if(val5.status === "Ready_For_Delivery"){
                                            return(
                                                <tr>
                                                   <td>
                                                       <p>Address: {val5.address}</p>
                                                       <p>City: {val5.city}</p>
                                                       <p>Date: {val5.date[2]+'.'+val5.date[1]+'.'+val5.date[0]}</p>
                                                       <p>Total price: {val5.totalPrice}</p>
                                                       <p>Total weight: {val5.totalWeight}</p>
                                                       <p>Status: {val5.status}</p>
                                                   </td>
                                                   <td>
                                                       <div>
                                                           <select name="status" id="orderStatus" onChange={(e) => {
                                                               e.preventDefault();
                                                               val5.status = e.target.value;
                                                               console.log(val5.status);
                                                               orders.push(val5);
                                                               console.log(orders);
                                                           }}>
                                                               <option value={""}>---Select new status---</option>
                                                               <option value={"In_Transport"}>In_Transport</option>
                                                           </select>
                                                       </div>
                                                   </td>
                                                   <td>
                                                       <button onClick={(e) => {
                                                            e.preventDefault();
                                                            localStorage.setItem("orderId", val5.id);
                                                            window.location.href="/OrderPDF";
                                                       }}>Generate report</button>
                                                   </td>
                                                </tr>   
                                            )
                                        }else if(val5.status === "In_Transport"){
                                            return(
                                                <tr>
                                                   <td>
                                                       <p>Address: {val5.address}</p>
                                                       <p>City: {val5.city}</p>
                                                       <p>Date: {val5.date[2]+'.'+val5.date[1]+'.'+val5.date[0]}</p>
                                                       <p>Total price: {val5.totalPrice}</p>
                                                       <p>Total weight: {val5.totalWeight}</p>
                                                       <p>Status: {val5.status}</p>
                                                   </td>
                                                   <td>
                                                       <div>
                                                           <select name="status" id="orderStatus" onChange={(e) => {
                                                               e.preventDefault();
                                                               val5.status = e.target.value;
                                                               console.log(val5.status);
                                                               orders.push(val5);
                                                               console.log(orders);
                                                           }}>
                                                               <option value={""}>---Select new status---</option>
                                                               <option value={"Delivered"}>Delivered</option>
                                                               <option value={"Returned"}>Returned</option>
                                                           </select>
                                                       </div>
                                                   </td>
                                                   <td>
                                                        <button onClick={(e) => {
                                                            e.preventDefault();
                                                            localStorage.setItem("orderId", val5.id);
                                                            window.location.href="/OrderPDF";
                                                        }}>Generate report</button>
                                                   </td>
                                                </tr>   
                                            )
                                        }else{
                                            return(
                                                <tr>
                                                   <td>
                                                       <p>Address: {val5.address}</p>
                                                       <p>City: {val5.city}</p>
                                                       <p>Date: {val5.date[2]+'.'+val5.date[1]+'.'+val5.date[0]}</p>
                                                       <p>Total price: {val5.totalPrice}</p>
                                                       <p>Total weight: {val5.totalWeight}</p>
                                                       <p>Status: {val5.status}</p>
                                                   </td>
                                                   <td>
                                                        <button onClick={(e) => {
                                                            e.preventDefault();
                                                            localStorage.setItem("orderId", val5.id);
                                                            window.location.href="/OrderPDF";
                                                        }}>Generate report pdf</button>
                                                   </td>
                                                </tr>   
                                            )
                                        }
                                    
                                    })}
                                </table>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <div className="wrapper">
                    <button onClick={handleChange}>Change</button>
            </div>
        </body>
    )
}

export default ViewPlanForTodayForDriver;

