import { useState } from "react";
import './styles/AddNewForm.css';
import React, { useRef } from 'react';
import { useEffect} from 'react'


function AddNewContract(){
    const [selectedOption1, setSelectedOption1] = useState('');
    const [selectedOption2, setSelectedOption2] = useState('');
    const radioButtonRef1 = useRef(null);
    const radioButtonRef2 = useRef(null);
    // TODO
    // var id = 0;
    // var price = 0;
    // var ingredientName = "";
    // var contractPart = {id, price, ingredientName}


    const [checkboxes, setCheckboxes] = useState({option1: false});

    const [terminationDate, setTerminationDate] = useState('');
    const [purposeOfTheAgreement, setPurposeOfTheAgreement] = useState('');
    const [scopeOfWork, setScopeOfWork] = useState('');
    const [terminationClause, setTerminationClause] = useState('');
    const [termsAndConditions, setTermsAndConditions] = useState('');
    const [confidentialityAndNonDisclosure, setConfidentialityAndNonDisclosure] = useState('');

    const [contractPartDTOs, setContractPartDTOs] = useState([]);
    var contractPartDTOList = []
    var contractPartDTOList2 = []
    var i = 0;

    const[companieStocks, setCompanieStocks] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    useEffect(() => {
        fetch("http://localhost:8081/api/companyStocks/findAllByCompanyId/" + localStorage.getItem('companyIdToCreateContract'), {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setCompanieStocks(result);
            console.log(companieStocks)
        });
    }, []);

    const handleCheckboxChange = (event) => {
        const { name, checked } = event.target;
        setCheckboxes({ ...checkboxes, [name]: checked });
        console.log(checked)
      };

    const handleOptionChange1 = (event) => {
        setSelectedOption1(event.target.value);
        setTermsAndConditions(radioButtonRef1.current.checked)
      };

      const handleOptionChange2 = (event) => {
        setSelectedOption2(event.target.value);
        setConfidentialityAndNonDisclosure(radioButtonRef2.current.checked)

      };
    
    const handleSubmit = (e) => {
        e.preventDefault()

        var test = JSON.parse(localStorage.getItem('testToken'));

        //                                 for (let j = 0; j < contractPartDTOList.length; j++) {
        //                                         console.log("FOR ID: " + contractPartDTOList.id)
        //                                     };        

        // setContractPartDTOs(contractPartDTOList)


        console.log("TU" + contractPartDTOs)



        const newContract = {terminationDate, purposeOfTheAgreement, scopeOfWork, terminationClause, termsAndConditions, confidentialityAndNonDisclosure, contractPartDTOList}

        console.log(newContract)

    
        fetch("http://localhost:8081/api/contracts/create/" + localStorage.getItem('companyIdToCreateContract'), {
                headers : {
                    'Content-Type':'application/json',
                    'Accept': 'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "POST",
                body : JSON.stringify(newContract)
        }).then(()=>{
                alert("New contract added")
                // window.location.reload()
        }).catch((err)=>{
                console.log(err)
                alert("Something went wrong!")
                // window.location.reload();
        });
        
    }
    
    return(
        <body>
            <div class="topnav">
                <a href="/SupplyManagerHome">Home</a>
                <a class="active" href="/contracts">Contracts</a>
                <a href="/companies">Companies</a>
            </div>
            <h1>Add New Contract</h1>
        <div className="wrapper">
        <table>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Select</th>
                        <th></th>
                    </tr>
                    {companieStocks.map((val, key) => {
                        return(
                            <tr key={key}>
                                <td>{val.ingredientName}</td>
                                <td>{val.price}</td>
                                <td>
                                <input type="checkbox" onChange={(e) => {
                                        e.preventDefault()

                                        const { name, checked } = e.target;
                                        setCheckboxes({ ...checkboxes, [name]: checked });
                                        console.log(checked)
                                        console.log("ID " + val.id)

                                        contractPartDTOList.push({
                                            id : val.id,
                                            price : val.price,
                                            ingredientName : val.ingredientName
                                        })

                                        console.log(contractPartDTOList)

                                        // setContractPartDTOs(contractPartDTOList)

                                        // console.log("TUU" +contractPartDTOs)

                                        // contractPartDTOList2[i] = contractPartDTOList
                                        // console.log(i)
                                        // i = i + 1

                                        // console.log(userId);
                                        // var orderedProducts=[];
                                        // for (let i = 0; i < cartItems.length; i++) {
                                        //     orderedProducts.push({
                                        //     productId: cartItems[i].id,
                                        //     quantity: cartItems[i].qty
                                        //     });

                                        // i = i + 1

                                        // TODO
                                        // contractPart.id = val.id
                                        // contractPart.price = val.price
                                        // contractPart.ingredientName = val.ingredientName

                                        // console.log("JJJJ" + contractPart.id)

                                        // localStorage.setItem('contractIdDetails', val.id)

                                        // window.location.href="/contractsDetails"
                                        
                                    }}/>
                                </td>
                            </tr>
                        )
                    })}
                </table>
            </div>
            <br/>
            <div>
            <form onSubmit={handleSubmit}>
                <fieldset>
                    <label>
                        <p>Termination Date</p>
                        <input id="terminationDate" name="terminationDate" onChange={(e)=>setTerminationDate(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Purpose Of The Agreement</p>
                        <input id="purposeOfTheAgreement" name="purposeOfTheAgreement" onChange={(e)=>setPurposeOfTheAgreement(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Scope Of Work</p>
                        <input id="scopeOfWork" name="scopeOfWork" onChange={(e)=>setScopeOfWork(e.target.value)}/>
                    </label>
                </fieldset>
                <fieldset>
                    <label>
                        <p>Termination Clause</p>
                        <input id="terminationClause" name="terminationClause" onChange={(e)=>setTerminationClause(e.target.value)}/>
                    </label>
                </fieldset>
                <label>
                <p>Terms And Conditions</p>
                <input type="radio" value="option1" checked={selectedOption1 === 'option1'} onChange={handleOptionChange1} ref={radioButtonRef1}/>
                </label>
                <br/>
                <label>
                <p>Confidentiality And NonDisclosure</p>
                <input type="radio" value="option2" checked={selectedOption2 === 'option2'} onChange={handleOptionChange2} ref={radioButtonRef2}/>
                </label>
                <br/>
            <button type="submit">Submit</button>
            </form>
        </div>
        </body>
    )
}

export default AddNewContract;