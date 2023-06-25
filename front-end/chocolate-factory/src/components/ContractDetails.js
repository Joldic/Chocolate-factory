import { TextField } from '@mui/material';
import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'
import Modal from "react-modal";



function ContractsDetails(){

    const[contracts, setContracts] = useState([])
    const[showModalDelete, setShowModalDelete] = useState(false)
    const[showModalRenew, setShowModalRenew] = useState(false)
    const [terminationReason, setTerminationReason] = useState("");
    const [id, setId] = useState("");
    const [terminationDate, setTerminationDate] = useState("");



    function handleOpenModalDelete(){
        setShowModalDelete(true);
    }

    function handleCloseModalDelete(){
        setShowModalDelete(false);
    }

    function handleOpenModalRenew(){
        setShowModalRenew(true);
    }

    function handleCloseModalRenew(){
        setShowModalRenew(false);
    }

    var test = JSON.parse(localStorage.getItem('testToken'))
    var termsAndConditions = "Not check";
    var confidentialityAndNonDisclosure = "Not check";

    useEffect(() => {
        fetch("http://localhost:8081/api/contracts/findAllByCompanyId/" + localStorage.getItem('contractIdDetails'), {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setContracts(result);
            console.log(contracts)
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
                    Contracts
                </h1>
            </div>
            <div className='wrapper'>
                <table>
                    <tr>
                        <th>Execution Date</th>
                        <th>Termination Date</th>
                        <th>Purpose Of The Agreement</th>
                        <th>Scope Of Work</th>
                        <th>Termination Clause</th>
                        <th>Terms And Conditions</th>
                        <th>Confidentiality And NonDisclosure</th>
                        <th></th>
                    </tr>
                    {contracts.map((val, key) => {
                        if(val.termsAndConditions) {
                            termsAndConditions = "Check"
                        }
                        if(val.confidentialityAndNonDisclosure){
                            confidentialityAndNonDisclosure = "Check"
                        }
                        return(
                            <tr key={key}>
                                <td>{val.executionDate}</td>
                                <td>{val.terminationDate}</td>
                                <td>{val.purposeOfTheAgreement}</td>
                                <td>{val.scopeOfWork}</td>
                                <td>{val.terminationClause}</td>
                                <td>{termsAndConditions}</td>
                                <td>{confidentialityAndNonDisclosure}</td>
                                <table style={{width: '100%'}}>
                                    <tr>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th></th>
                                    </tr>
                                    {val.contractPartDTOs.map((val1, key1) => {
                                        return(
                                            <tr key1={key1}>
                                                <td>{val1.ingredientName}</td>
                                                <td>{val1.price}</td>
                                            </tr>
                                        )
                                    })}
                                </table>
                                <td>
                                <div>
                                        <button onClick={(e) => {
                                                    e.preventDefault()
                                                    
                                                    setShowModalRenew(true)

                                                    localStorage.setItem('contractIdToRenew', val.id)
                                                    setId(val.id)
                                        
                                            }}>Renew</button>
                                        <Modal id="modalRenew" isOpen={showModalRenew} onRequestClose={handleCloseModalRenew} style={{overlay: { backgroundColor: "rgba(0, 0, 0, 0.5)",},  content: {backgroundColor: "white",borderRadius: "5px",        padding: "20px",maxWidth: "500px", maxHeight: "200px", margin: "auto",},}}>
                                            <TextField label = 'Termination Date' style={{width: '500px', height: '50px'}} onChange={(e)=>setTerminationDate(e.target.value)}></TextField>
                                            <button onClick={handleCloseModalRenew}>Close</button>
                                            <button onClick={(e) => {
                                                    e.preventDefault()
                                                    
                                                    let contractToRenew = {id, terminationDate}

                                                    fetch("http://localhost:8081/api/contracts/contractRenewal",{
                                                        headers : {
                                                            'Content-Type': 'application/json',
                                                            'Accept': 'application/json',
                                                            Authorization: `Bearer ${test.accessToken}`,
                                                        },
                                                        method : "PUT",
                                                        body:JSON.stringify(contractToRenew)
                                                    }).then(res=>res.json()).then((result)=>{
                                                        console.log(contractToRenew)
                                                    });

                                                    setShowModalRenew(false)

                                                    window.location.href="/contractsDetails"
                                        
                                            }}>Set</button>
                                        </Modal>
                                </div>
                                </td>
                                <td>
                                <div>
                                        <button onClick={(e) => {
                                                    e.preventDefault()
                                                    setShowModalDelete(true)
                                                    localStorage.setItem('contractIdToTerminate', val.id)

                                                    setId(val.id)


                                        
                                            }}>Terminate</button>
                                        <Modal id = "modalDelete" isOpen={showModalDelete} onRequestClose={handleCloseModalDelete} style={{overlay: { backgroundColor: "rgba(0, 0, 0, 0.5)",},  content: {backgroundColor: "white",borderRadius: "5px",        padding: "20px",maxWidth: "500px", maxHeight: "200px", margin: "auto",},}}>
                                            <TextField label = 'Termination reason' style={{width: '500px', height: '50px'}} onChange={(e)=>setTerminationReason(e.target.value)}></TextField>
                                            <button onClick={handleCloseModalDelete}>Close</button>
                                            <button onClick={(e) => {
                                                    e.preventDefault()
                                                    
                                                    let contractToDelete = {id, terminationReason}
                                                    console.log(contractToDelete)

                                                    fetch("http://localhost:8081/api/contracts/delete",{
                                                        headers : {
                                                            'Content-Type': 'application/json',
                                                            'Accept': 'application/json',
                                                            Authorization: `Bearer ${test.accessToken}`,
                                                        },
                                                        method : "PUT",
                                                        body:JSON.stringify(contractToDelete)
                                                    }).then(res=>res.json()).then((result)=>{
                                                        console.log(contractToDelete)
                                                    });

                                                    setShowModalDelete(false)

                                                    window.location.href="/contractsDetails"
                                        
                                            }}>Set</button>
                                        </Modal>
                                </div>
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
        </body>
    )

}
export default ContractsDetails;
