import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function ViewAllOrders(){

    const[orders, setOrders] = useState([])

    const[tours, setTours] = useState([])

    const[plan, setPlan] = useState([])

    const [engagements, setEngagements] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/orders/getCompletedOrders", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setOrders(result);
            console.log(result);

            if(result.length == 0){
                document.getElementById('completedOrdersTable').hidden = true;
                document.getElementById('messageTable').hidden = false;
            }
        });
    }, []);

    useEffect(()=>{
        fetch("http://localhost:8081/api/tours/getPlanForTomorrow", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setPlan(result);
            console.log(result);

            if(result.length!=0){
                document.getElementById('planMessageTable').hidden=false;
            }else{
                document.getElementById('toHide').hidden=false;
            }
        });
    }, []);


    const handleClick = (e) => {
        e.preventDefault()

        document.getElementById('toHide').hidden=true;
        document.getElementById('toShow').hidden=false;

            fetch("http://localhost:8081/api/tours/createPlan", {
                headers : {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "GET"
            }).then(res=>res.json()).then((result)=>{
                setTours(result);
                console.log(result);
            }).catch((err) =>{
                console.log(err);
            });
    }

    const handleAcceptPlan = (e) => {
        e.preventDefault()

        fetch("http://localhost:8081/api/tours/acceptPlan", {
                headers : {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "POST",
                body : JSON.stringify(tours) 
        }).then(() => {
            alert("Transportation plan is successfuly created!");
            window.location.href='/ViewPlanForTomorrow';
        }).catch((err) => {
            console.log(err);
            alert("Something went wrong!");
        });
    }

    return(
        <body>
            <div class="topnav">
                <a href="/DeliveryManagerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a className='active' href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div> 
            <div className='wrapper'>
                <h1>Completed Orders</h1>
            </div>
            <div className='wrapper'>
                    <table className='orderTable' id='completedOrdersTable'>
                            <tr>
                                <th>Address</th>
                                <th>City</th>
                                <th>Date</th>
                                <th>Total price</th>
                                <th>Total weight</th>
                                <th></th>
                            </tr>
                            {orders.map((val, key) => {
                                if(val.priority){
                                    return(
                                        <tr key={key} className='rowColor'>
                                            <td>{val.address}</td>
                                            <td>{val.city}</td>
                                            <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                                            <td>{val.totalPrice}</td>
                                            <td>{val.totalWeight}</td>
                                            <td>
                                                <button onClick={(e) => {
                                                    e.preventDefault()
                                                    localStorage.setItem('orderId', val.id)
                                                    window.location.href="/OneOrderView"
                                                }}>View</button>
                                            </td>
                                        </tr>
                                    )
                                }else{
                                    return(
                                        <tr key={key}>
                                            <td>{val.address}</td>
                                            <td>{val.city}</td>
                                            <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                                            <td>{val.totalPrice}</td>
                                            <td>{val.totalWeight}</td>
                                            <td>
                                                <button onClick={(e) => {
                                                    e.preventDefault()
                                                    localStorage.setItem('orderId', val.id)
                                                    window.location.href="/OneOrderView"
                                                }}>View</button>
                                            </td>
                                        </tr>
                                    )
                                }
                            })}
                    </table>
                    <table id='messageTable' hidden='true'>
                            <tr>
                                <p><strong>There are no completed orders at the moment!</strong></p>
                            </tr>
                    </table>
            </div>
            <div className='wrapper'>
                <h1>Transportation plan</h1>
            </div>
            <div className='wrapper'>
                <table id='toHide' hidden='true'>
                    <tr>
                    <p>Does not exist delivery plan for tomorrow.</p>
                    <button onClick={handleClick}>Create plan</button>
                    </tr>
                </table>
                <table id='toShow' hidden='true'>
                    <tr>
                        <th>City</th>
                        <th>Date</th>
                        <th>Orders</th>
                        <th>Engagement</th>
                        <th></th>
                    </tr>
                    {tours.map((val, key) => {
                        return(
                            <tr key={key}>
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
                                                <p>Total price: {val2.totalPrice}</p>
                                                <p>Total weight: {val2.totalWeight}</p>
                                                <p>User: {val2.userDTO.firstName+' '+val2.userDTO.lastName}</p>
                                            </div>
                                        )
                                    })}
                                </td>
                                <td>
                                    <div id={'Driver'+key}>
                                    {val.engagementsDTO.map((val3, key3) =>{
                                        return(
                                            <div>
                                                <p><strong>Driver:</strong></p>
                                                <p>Name: {val3.driverDTO.firstName+' '+val3.driverDTO.lastName}</p>
                                                <p><strong>Truck: </strong>{val3.truckDTO.name}</p>
                                                <p>Registration number: {val3.truckDTO.registrationNumber}</p>
                                            </div>
                                        )
                                    })}
                                    </div>
                                    <div id={'Driver'+key+'show'}  hidden = 'true'>
                                        <select onChange={
                                            (e) => {
                                                e.preventDefault();
                                                var engs = []
                                                var eng = {"id" : e.target.value}                                               
                                                engs.push(eng)   
                                                val.engagementsDTO = engs;                                            
                                            }
                                            }>
                                            <option value="">---Choose driver and truck---</option>
                                        {engagements.map((val4, key4) => {
                                            return(
                                                <option value={val4.id}>{'Driver: '+val4.driverDTO.firstName+' '+val4.driverDTO.lastName+', Truck: '+val4.truckDTO.name+'( '+val4.truckDTO.registrationNumber+' )'}</option>
                                            )
                                        })}
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <button onClick={
                                        (e) => {
                                        e.preventDefault();

                                        document.getElementById('Driver'+key).hidden = true;

                                        fetch("http://localhost:8081/api/engagements/", {
                                            headers : {
                                                'Content-Type': 'application/json',
                                                'Accept': 'application/json',
                                                Authorization: `Bearer ${test.accessToken}`,
                                            },
                                            method : "GET"
                                        }).then(res=>res.json()).then((result)=>{
                                            setEngagements(result);
                                            console.log(result);
                                        }).catch((err)=>{
                                            console.log(err);
                                        });

                                        document.getElementById('Driver'+key+'show').hidden = false;
                                        }
                                    }>Change</button>
                                </td>
                            </tr>
                        )
                    })}
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><button onClick={handleAcceptPlan}>Accept</button></td>
                    </tr>
                </table>
                <table id='planMessageTable' hidden='true'>
                    <tr>
                        <p>Transportation plan for tomorrow has already been created.</p>
                    </tr>
                    <tr>
                        <button onClick={(e) => {
                            e.preventDefault();
                            window.location.href='/ViewPlanForToday'
                        }}>View plan for today</button>
                        <button onClick={(e)=> {e.preventDefault();
                                                window.location.href='/ViewPlanForTomorrow';
                                                }}>View plan for tomorrow</button>
                    </tr>
                </table>
            </div>
        </body>
    )
}

export default ViewAllOrders;