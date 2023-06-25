import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function ViewAllDrivers(){

    const[drivers, setDrivers] = useState([])
    const[driverId, setDriverId] = useState('');
    const[month, setMonth] = useState('');

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/drivers/findAll", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setDrivers(result);
        }).catch((err) =>{
            console.log(err);
        });
    }, []);
   
    const navigateToAddNew = (e) => {
        e.preventDefault()
        window.location.href = "/DriverCreate"
    }

    const handleGenerate = (e) => {
        e.preventDefault();
        document.getElementById('driversTable').hidden=true;
        document.getElementById('reportTable').hidden=false;

        document.getElementById('buttons').style.display = 'none';
    }

    return(

        <body>
            <div class="topnav">
                <a href="/DeliveryManagerHome">Home</a>
                <a class="active" href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div>  
            <div className='wrapper'>
                <h1>View All Drivers</h1>
            </div>          
            <div className='wrapper'>
                <table id='driversTable'>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                        <th>JMBG</th>
                        <th></th>                      
                    </tr>
                    {drivers.map((val, key) => {
                        return(
                            <tr key={key} >
                                <td>{val.firstName}</td>
                                <td>{val.lastName}</td>
                                <td>{val.adress}</td>
                                <td>{val.phoneNumber}</td>
                                <td>{val.jmbg}</td>
                                <td>
                                    <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('driverId', val.id)

                                        window.location.href="/OneDriverView"
                                        
                                    }}>View</button>
                                </td>
                            </tr>
                        )
                    })}
                </table>
                <div id='reportTable' hidden='true'>
                        <fieldset>
                            <p><strong>Driver: </strong></p>
                            <select onChange={(e) => {setDriverId(e.target.value)}}>
                                <option value="">---Choose driver</option>
                                {drivers.map((val, key) => {
                                    return(
                                        <option value={val.id}>{val.firstName+' '+val.lastName+' ('+val.jmbg+')'}</option>
                                    )
                                })}
                            </select>
                        </fieldset>
                        <fieldset>
                        <p><strong>Month: </strong></p>
                        
                        <select onChange={(e) => {setMonth(e.target.value)}}>
                            <option value="">---Choose month</option>
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
                        </select>
                        </fieldset>
                        <button onClick={
                            (e) => {
                                e.preventDefault();
                                console.log(driverId);
                                console.log(month);
                                localStorage.setItem('driverId', driverId);
                                localStorage.setItem('month', month);
                                window.location.href='/DriverPDF'
                            }
                        }>Generate</button>    
                </div>
            </div>
            <div id='buttons' class="wrapper">
                <button id='addButton' onClick={navigateToAddNew}>Add New Driver</button>
                <button id='pdfButton' onClick={handleGenerate}>Generate report pdf</button>
            </div>
        </body>
    )
}
export default ViewAllDrivers;     