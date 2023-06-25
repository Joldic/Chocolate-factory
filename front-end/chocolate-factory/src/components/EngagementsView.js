import './styles/AddNewForm.css'
import { useState, useEffect } from 'react'

function ViewEngagements(){

    const[engagements, setEngagements] = useState([])
    
    useEffect(() => {

        var test = JSON.parse(localStorage.getItem('testToken'))
        
        fetch("http://localhost:8081/api/engagements/getAll", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setEngagements(result);
        }).catch((err)=>{
            console.log(err);
        });

    }, []);

    return(
        <body>
            <div class="topnav">
                <a href="/DeliveryMangerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a class="active" href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div>
            <div className='wrapper'>
                <h1>
                    Engagements
                </h1>
            </div>
            <div className='wrapper'>
                <table>
                    <tr>
                        <th>Driver</th>
                        <th>Truck</th>
                        <th>Engagement start date</th>
                        <th>Engagement end date</th>
                    </tr>
                    {engagements.map((val, key) => {

                        var enddate = "__.__.____";
                        if(val.endDate != null){
                            enddate = val.endDate[2]+'.'+val.endDate[1]+'.'+val.endDate[0];
                        }

                        return(
                            <tr key={key}>
                                <td>
                                    <div>
                                        <p>First name: {val.driverDTO.firstName}</p>
                                        <p>Last name: {val.driverDTO.lastName}</p>
                                        <p>Phone number: {val.driverDTO.phoneNumber}</p>
                                        <p>Category: 
                                            {val.driverDTO.categoriesDTO.map((val2, key2)=>{
                                                return(
                                                    <p>{val2.categoryMark}</p>
                                                )
                                            })}    
                                        </p>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <p>Name: {val.truckDTO.name}</p>
                                        <p>Registration number: {val.truckDTO.registrationNumber}</p>
                                        <p>Capacity: {val.truckDTO.capacity}t</p>
                                    </div>
                                </td>
                                <td>{val.startDate[2]+'.'+val.startDate[1]+'.'+val.startDate[0]}</td>
                                <td>{enddate}</td>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <div className='wrapper'>
                    <button onClick={
                        (e)=>{
                            e.preventDefault()
                            window.location.href='/EngagementCreate'
                        }
                    }>Create New Engagement</button>
            </div>
        </body>
    )

}
export default ViewEngagements;