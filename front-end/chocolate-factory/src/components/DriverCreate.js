import { useEffect, useState } from "react";
import './styles/AddNewForm.css';

function Create(){
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [adress, setAdress] = useState('');
    const [jmbg, setJMBG] = useState('');
    const [categories, setCategories] = useState([]);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    var test = JSON.parse(localStorage.getItem('testToken'));
    useEffect(() => {
        fetch("http://localhost:8081/api/driverCategory/", {
            headers : {
                'Content-Type':'application/json',
                'Accept':'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method:"GET"
        }).then(res=>res.json()).then((result)=>{
            console.log(result);
            setCategories(result);
        });
    }, []);

    const handleSubmit = (e) =>{
        e.preventDefault()

        const newDriver = {firstName, lastName, email, phoneNumber, adress, jmbg, username, password};

        fetch("http://localhost:8081/api/drivers/create",{
                headers: {
                    'Content-Type':'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method:"POST",
                body:JSON.stringify(newDriver)
        }).then(()=>{
                console.log("New driver added")
                alert("New driver added")
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
          <form onSubmit={handleSubmit}>
          <h1>Add New Driver</h1>
            <fieldset>
                <label>
                    <p>First Name</p>
                    <input id="firstName" name="firstName" onChange={(e)=>setFirstName(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Last Name</p>
                    <input id="lastName" name="lastName" onChange={(e)=>setLastName(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Email</p>
                    <input id="email" name="email" onChange={(e)=>setEmail(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Username</p>
                    <input id="username" name="username" onChange={(e)=>setUsername(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Password</p>
                    <input type="password" id="password" name="password" onChange={(e)=>setPassword(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Phone Number</p>
                    <input id="phoneNumber" name="phoneNumber" onChange={(e)=>setPhoneNumber(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Adress</p>
                    <input id="adress" name="adress" onChange={(e)=>setAdress(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>JMBG</p>
                    <input id="jmbg" name="jmbg" onChange={(e)=>setJMBG(e.target.value)}/>
                </label>
            </fieldset>
            <button type="submit">Submit</button>
            </form>
        </div>
        </body>
    )
}

export default Create;