import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'
import Modal from "react-modal";

import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import {Paper, Button} from '@mui/material'

function Companies(){

    const[companies, setCompanies] = useState([])
    const[showModal, setShowModal] = useState(false);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [city, setCity] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    function handleOpenModal(){
        setShowModal(true);
    }

    function handleCloseModal(){
        setShowModal(false);
    }

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/companies/findAll", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setCompanies(result);
            console.log(companies)
        });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        // window.location.href = "/TruckCreate"
    }

    const handleSubmit = (e) =>{
        e.preventDefault()
        let company = {name, email, city, address, phoneNumber}
        console.log(company)
        // fetch("http://localhost:8081/auth/login", {
        //   headers : { 
        //     'Content-Type': 'application/json',
        //     'Accept': 'application/json',
        //   },
        //   method:"POST",
        //   body:JSON.stringify(user),
        // }).then(res => res.json()).then((result)=>
        // {
        //   setToken(result);
    
        //   let testToken = {accessToken : "", expiresIn : 0}
    
        //   testToken.accessToken = result.accessToken;
        //   testToken.expiresIn = result.expiresIn;
    
        //   localStorage.setItem('testToken', JSON.stringify(testToken));
        //   localStorage.setItem('user_userName', username);
    
        //   console.log(JSON.parse(localStorage.getItem('testToken')));

    
        // }
        // )
      };

    return(
        <body>
            <div class="topnav">
                <a href="/SupplyManagerHome">Home</a>
                <a href="/contracts">Contracts</a>
                <a class="active" href="/companies">Companies</a>
            </div>
            <div className='wrapper'>
                <h1>
                    All Companies
                </h1>
            </div>
            <div className='wrapper'>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>City</th>
                        <th>Address</th>
                        <th>Phone number</th>
                        <th></th>
                    </tr>
                    {companies.map((val, key) => {
                        return(
                            <tr key={key}>
                                <td>{val.name}</td>
                                <td>{val.email}</td>
                                <td>{val.city}</td>
                                <td>{val.address}</td>
                                <td>{val.phoneNumber}</td>
                                <td>
                                    <div>
                                        <button onClick={handleOpenModal} >Open Modal</button>
                                        <Modal isOpen={showModal} onRequestClose={handleCloseModal} style={{overlay: { backgroundColor: "rgba(0, 0, 0, 0.5)",},  content: {backgroundColor: "white",borderRadius: "5px",    padding: "20px",maxWidth: "500px",margin: "auto",},}}>
                                            <h2>Update Company</h2>
                                            <form onSubmit={handleSubmit}>
                                                <label>Name:
                                                    <input type="text" onChange={(e)=>setName(e.target.value)}/>
                                                </label>
                                                <br />
                                                <br />
                                                <label>Email:
                                                    <input type="text" onChange={(e)=>setEmail(e.target.value)}/>
                                                </label>
                                                <br />
                                                <br />
                                                <label>City:
                                                    <input type="text" onChange={(e)=>setCity(e.target.value)}/>
                                                </label>
                                                <br />
                                                <br />
                                                <label>Address:
                                                    <input type="text" onChange={(e)=>setAddress(e.target.value)}/>
                                                </label>
                                                <br />
                                                <br />
                                                <label>Phone number:
                                                    <input type="text" onChange={(e)=>setPhoneNumber(e.target.value)}/>
                                                </label>
                                                <br />
                                                <br />
                                                <button type="submit">Submit</button>
                                            </form>
                                            <button onClick={handleCloseModal}>Close</button>
                                        </Modal>
                                    </div>




                                    { <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('companyIdToUpdate', val.id)

                                        // window.location.href="/OneTruckView"
                                        
                                    }}>Update</button>}
                                </td>
                                <td>
                                    <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('companyIdToDelete', val.id)

                                        // window.location.href="/OneTruckView"
                                        
                                    }}>Delete</button>
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <div class="wrapper">
                <button onClick={navigateToAddNew}>Add New Company</button>
            </div>
        </body>
    )

}
export default Companies;
