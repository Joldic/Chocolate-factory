import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import './styles/Signup.css'
import { Container } from '@mui/system';
import {Paper, Button} from '@mui/material'

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function ManagerRegistration() {
  const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
  const[password, setPassword] = useState('')
  const[firstName, setFirstName] = useState('')
  const[lastName, setLastName] = useState('')
  const [userRole, setRole] = React.useState('');
  const[email, setEmail] = useState('')
  const[username, setUsername] = useState('')

  const handleChange = (event) => {
    setRole(event.target.value);
  };

  const handleClick = (e) =>{
    e.preventDefault()
    const new_user = {password, firstName, lastName, email,username, userRole}

    console.log(new_user);
    fetch("http://localhost:8081/api/users/createManager/" + localStorage.getItem('system_admin_id'),{ 
    method:"POST",
    headers:{"Content-Type":"application/json"},
    body:JSON.stringify(new_user)

  }).then(() =>{
    console.log("New user added")
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
        <h1>Add manager</h1>
        <Paper elevation={3} style={paperStyle}>

        <TextField id="standard-basic" label="username" variant="standard" fullWidth 
          value={username}
          onChange = {(e) =>setUsername(e.target.value)}
          />

          <TextField id="standard-basic" label="email" variant="standard" fullWidth 
          value={email}
          onChange = {(e) =>setEmail(e.target.value)}
          />
          
            <TextField id="standard-basic" label="password" variant="standard" fullWidth 
          value={password}
          onChange = {(e) =>setPassword(e.target.value)}
          />


            <TextField id="standard-basic" label="firstname" variant="standard" fullWidth 
          value={firstName}
          onChange = {(e) =>setFirstName(e.target.value)}
          />

          <TextField id="standard-basic" label="lastName" variant="standard" fullWidth 
                value={lastName}
                onChange = {(e) =>setLastName(e.target.value)}
          />

            <FormControl fullWidth>

            <InputLabel id="demo-simple-select-label">Role</InputLabel>
            <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={userRole}
            label="Role"
            onChange={handleChange}
            >
            <MenuItem value={"SaleManager"}>SaleManager</MenuItem>
            <MenuItem value={"ProductionManager"}>ProductionManager</MenuItem>
            <MenuItem value={"DeliveryManager"}>DeliveryManager</MenuItem>
            <MenuItem value={"SupplyManager"}>SupplyManager</MenuItem>
            </Select>
            </FormControl>
      
        <Button variant="contained" color="secondary" onClick={handleClick}>
          Submit
        </Button>

        </Paper>

      </Container>
    </Box>
  );
}