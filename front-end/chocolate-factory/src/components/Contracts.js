import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'


function ContractsWithCompanies(){

    const[companies, setCompanies] = useState([])

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

    return(
        <body>
            <div class="topnav">
                <a href="/SupplyManagerHome">Home</a>
                <a class="active" href="/contracts">Contracts</a>
                <a href="/companies">Companies</a>
            </div>
            <div className='wrapper'>
                <h1>
                    All Created Contract
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
                                    <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('contractIdDetails', val.id)

                                        window.location.href="/contractsDetails"
                                        
                                    }}>Details</button>
                                </td>
                                <td>
                                    <button onClick={(e) => {
                                        e.preventDefault()

                                        localStorage.setItem('companyIdToCreateContract', val.id)

                                        window.location.href="/addNewContract"
                                        
                                    }}>Create new</button>
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
        </body>
    )

}
export default ContractsWithCompanies;