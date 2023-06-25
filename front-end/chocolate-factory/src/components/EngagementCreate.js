import './styles/AddNewForm.css'
import { useState, useEffect } from 'react'

function EngagementCreate(){

    const [drivers, setDrivers] = useState([])
    const [trucks, setTrucks] = useState([])
    const [driverDTO, setSelectedDriver] = useState([]);
    const [truckDTO, setSelectedTruck] = useState([]);

    const [id, setId] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [address, setAddress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [jmbg, setJmbg] = useState('');

    var test = JSON.parse(localStorage.getItem('testToken'));

    useEffect(() => {
        fetch("http://localhost:8081/api/drivers/findAvailable", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setDrivers(result);

            if(result.length === 0){
                document.getElementById('driversTable').hidden=true;
                document.getElementById('driversTableMessage').hidden=false;
            }
        }).catch((err) =>{
            console.log(err);
        });
    }, []);

    const handleCreate = (e) => {
        e.preventDefault();
        console.log(driverDTO)
        console.log(truckDTO)

        const newEngagement = {driverDTO, truckDTO}

        fetch("http://localhost:8081/api/engagements/create",{
                headers: {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method:"POST",
                body:JSON.stringify(newEngagement)
        }).then(res=>res.json()).then((result) => {
                console.log("New engagement added")
                alert("New engagement added")
                window.location.href='/EngagementsView'
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
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a class="active" href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div>
            <div className='wrapper'>
                <h1>Create new engagement</h1>
            </div>   
            <div className='wrapper'>
                <table id='driversTable'>
                    <caption><strong>Available drivers</strong></caption>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                        <th>JMBG</th>
                        <th>Category</th>
                    </tr>
                    {drivers.map((val, key) => {

                        return(
                            <tr key={key} onClick={()=>{
                                    setId(val.id);
                                    setFirstName(val.firstName);
                                    setLastName(val.lastName);
                                    setAddress(val.address);
                                    setPhoneNumber(val.phoneNumber);
                                    setJmbg(val.jmbg);
                                    const driver = {id, firstName, lastName, address, phoneNumber, jmbg};
                                    setSelectedDriver(driver);

                                    fetch("http://localhost:8081/api/trucks/findAvailable/"+val.id, {
                                        headers : {
                                            'Content-Type': 'application/json',
                                            'Accept': 'application/json',
                                        Authorization: `Bearer ${test.accessToken}`,
                                    },
                                        method : "GET"
                                    }).then(res=>res.json()).then((result)=>{
                                        setTrucks(result);

                                        if(result.length === 0){
                                            document.getElementById('trucksTable').hidden=true;
                                            document.getElementById('trucksTableMessage').hidden=false;
                                        }else{
                                            document.getElementById('trucksTable').hidden=false;
                                            document.getElementById('trucksTableMessage').hidden=true;
                                        }
                                    }).catch((err) =>{
                                        console.log(err);
                                    });
                                    }
                                } className={"clickable-row ".concat(driverDTO.id === val.id ? "selected" : "")}>
                                <td>{val.firstName}</td>
                                <td>{val.lastName}</td>
                                <td>{val.adress}</td>
                                <td>{val.phoneNumber}</td>
                                <td>{val.jmbg}</td>
                                <td>
                                    {val.categoriesDTO.map((val2, key2) => {
                                        return(
                                            <p>{val2.categoryMark}</p>
                                        )
                                    })}
                                </td>
                            </tr>
                        )
                    })}
                </table>
                <table id='driversTableMessage' hidden='true'>
                    <p>There are no available drivers.</p>
                    <p><strong>It is impossible to create new engagement!</strong></p>
                </table>
            </div>
            <div className='wrapper'>
            <table id='trucksTable' hidden='true'>
                    <caption><strong>Available trucks</strong></caption>
                    <tr>
                        <th>Name</th>
                        <th>Registration Number</th>
                        <th>Capacity</th>
                    </tr>
                    {trucks.map((val, key) => {

                        return(
                            <tr key={key} onClick={()=>setSelectedTruck(val)} className={"clickable-row ".concat(truckDTO.id === val.id ? "selected" : "")}>
                                <td>{val.name}</td>
                                <td>{val.registrationNumber}</td>
                                <td>{val.capacity}</td>
                            </tr>
                        )
                    })}
                </table>
                <table id='trucksTableMessage' hidden='true'>
                    <p>There are no available trucks.</p>
                    <p><strong>It is impossible to create new engagement!</strong></p>
                </table>
            </div>
            <div className='wrapper'>
                <button onClick={handleCreate}>Create</button>
            </div>
        </body>
    )
}

export default EngagementCreate;