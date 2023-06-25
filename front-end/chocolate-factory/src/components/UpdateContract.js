import React, { useEffect, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import './styles/Signup.css'
import { Container } from '@mui/system';
import {Paper, Button} from '@mui/material'

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function UpdateContract() {
  const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
  var [contract, setContract] = useState([])
  const[terminationDate, setTerminationDate] = useState('')

  useEffect(() =>{
    var test = JSON.parse(localStorage.getItem('testToken'))
    fetch("http://localhost:8081/api/contracts/findOneById/" + localStorage.getItem('contractIdToUpdate'), {
      method:"GET",
      headers:{
        "Content-Type":"application/json",
        'Accept': 'application/json',
        Authorization: `Bearer ${test.accessToken}`,
            }

    })
    .then(res => res.json())
    .then((result) =>
    {
        setContract(result);
    }
    )
  },[])


  const handleClick = (e) =>{
    e.preventDefault()
    var admin = contract;
    contract.terminationDate = terminationDate;
    console.log(admin);
    var test = JSON.parse(localStorage.getItem('testToken'))


    console.log(admin);
    fetch("http://localhost:8081/api/contracts/contractRenewal/" + localStorage.getItem('contractIdToUpdate'),{ 
    method:"PUT",
    headers:{
        "Content-Type":"application/json",
        'Accept': 'application/json',
        Authorization: `Bearer ${test.accessToken}`,
    },
    body:JSON.stringify(admin)

  }).then(() =>{
    console.log("Contract updated")
  })
}
  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <Container>
        <h1>Update Contract</h1>
        <Paper elevation={3} style={paperStyle}>

        <TextField id="standard-basic" label="terminationDate" variant="standard" fullWidth 
          placeholder={contract.terminationDate}
          onChange = {(e) =>setTerminationDate(e.target.value)}
          />
      
        <Button variant="contained" color="secondary" onClick={handleClick}>
          Submit
        </Button>

        </Paper>

      </Container>
    </Box>
  );
}