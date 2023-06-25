import { useEffect, useRef, useState } from 'react';
import './styles/AddNewForm.css'

function ViewOneDriver(){

    const[driver, setDriver] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('driverId')

    useEffect(() => {
        fetch("http://localhost:8081/api/drivers/"+id, {
            headers : {
                'Content-Type':'application/json',
                'Accept':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"GET"
        }).then(res=>res.json()).then((result)=>{
            console.log(result);
            setDriver(result);
        });
    }, []);

    const ref = useRef(null)

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [adress, setAdress] = useState('');
    const [jmbg, setJMBG] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');

    const handleUpdate = (e) =>{
        e.preventDefault()

        const updatedDriver = {firstName, lastName, phoneNumber, adress, jmbg, username, email};
        
        fetch("http://localhost:8081/api/drivers/update/"+id, {
                headers : {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "PUT",
                body : JSON.stringify(updatedDriver)
        }).then(()=>{
                console.log("Selected driver updated.")
                alert("Selected driver updated.")
                window.location.reload()
        }).catch((err)=>{
                console.log(err)
                alert("Something went wrong!")
                window.location.reload();
        });
        

    }

    const handleDelete = (e) =>{
        e.preventDefault()

    
        fetch("http://localhost:8081/api/drivers/delete/"+id, {
                headers : {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "DELETE"
        }).then(()=>{
                console.log("Selected driver is successfuly deleted.")
                alert("Selected driver is successfuly deleted.")
                window.location.href="/DriversView";
        }).catch((err)=>{
                console.log(err);
                alert("Something went wrong!")
             window.location.reload();
        });
        
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
            <div className="wrapper">
          <form onSubmit={handleUpdate}>
          <h1>Selected Driver</h1>
            <fieldset>
                <label>
                    <p>First Name</p>
                    <input id="firstName" name="firstName" ref={ref} defaultValue={driver.firstName} onChange={(e)=>setFirstName(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Last Name</p>
                    <input id="lastName" name="lastName" ref={ref} defaultValue={driver.lastName} onChange={(e)=>setLastName(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Username</p>
                    <input id="username" name="username" ref={ref} defaultValue={driver.username} onChange={(e)=>setUsername(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Email</p>
                    <input id="email" name="email" ref={ref} defaultValue={driver.email} onChange={(e)=>setEmail(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Phone Number</p>
                    <input id="phoneNumber" name="phoneNumber" ref={ref} defaultValue={driver.phoneNumber} onChange={(e)=>setPhoneNumber(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Address</p>
                    <input id="adress" name="adress" ref={ref} defaultValue={driver.adress} onChange={(e)=>setAdress(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>JMBG</p>
                    <input id="jmbg" name="jmbg" ref={ref} defaultValue={driver.jmbg} onChange={(e)=>setJMBG(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Category</p>
                </label>
            </fieldset>
            <button type="submit">Update</button>
            <button onClick={handleDelete}>Delete</button>
            </form>
        </div>
        </body>
    )

}
export default ViewOneDriver;