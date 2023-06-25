import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function DeleteContract(){

    var test = JSON.parse(localStorage.getItem('testToken'))

    var contractToDelete = localStorage.getItem('contractIdToDelete')


    useEffect(() => {
        fetch("http://localhost:8081/api/contracts/delete/" + contractToDelete, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "DELETE"
        }).catch((err) =>{
            console.log(err);
        });
    }, []);

    window.location.href="/contracts"

    
}
export default DeleteContract;     