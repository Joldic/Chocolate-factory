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

export default function UserProfileUpdate() {
  const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
  var [user, setUser] = useState([])
  const[password, setPassword] = useState('')
  const[firstName, setFirstName] = useState('')
  const[lastName, setLastName] = useState('')
  const [userRole, setRole] = React.useState('');
  const[email, setEmail] = useState('')
  const[username, setUsername] = useState('')

  useEffect(() =>{
    var test = JSON.parse(localStorage.getItem('testToken'))
    fetch("http://localhost:8081/api/users/findOne/" + localStorage.getItem('regular_user_id'), {
      method:"GET",
      headers:{
        "Content-Type":"application/json",
        'Accept': 'application/json',
            }

    })
    .then(res => res.json())
    .then((result) =>
    {
        setUser(result);
        setRole(result.userRole);
    }
    )
  },[])


  const handleClick = (e) =>{
    e.preventDefault()
    var admin = user;
    admin.firstName = firstName;
    admin.email = email;
    admin.lastName = lastName;
    admin.password = password;
    admin.username = username;
    admin.userRole = userRole
    console.log(admin);

    console.log(admin);
    fetch("http://localhost:8081/api/users/update/" + localStorage.getItem('regular_user_id'),{ 
    method:"PUT",
    headers:{
        "Content-Type":"application/json",
        'Accept': 'application/json',
    },
    body:JSON.stringify(admin)

  }).then(() =>{
    console.log("User updated")
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
        <h1>Update your profile</h1>
        <Paper elevation={3} style={paperStyle}>

        <TextField id="standard-basic" label="username" variant="standard" fullWidth 
          placeholder={user.username}
          onChange = {(e) =>setUsername(e.target.value)}
          />

          <TextField id="standard-basic" label="email" variant="standard" fullWidth 
          placeholder={user.email}
          onChange = {(e) =>setEmail(e.target.value)}
          />
          
            <TextField id="standard-basic" label="password" variant="standard" fullWidth 
          placeholder={user.password}
          onChange = {(e) =>setPassword(e.target.value)}
          />


            <TextField id="standard-basic" label="firstname" variant="standard" fullWidth 
          placeholder={user.firstName}
          onChange = {(e) =>setFirstName(e.target.value)}
          />

          
        <TextField id="standard-basic" variant="standard" label="lastName" fullWidth 
        placeholder={user.lastName}
        onChange = {(e) => setLastName(e.target.value)}
        />

        <TextField id="standard-basic" variant="standard" label="userRole" fullWidth aria-readonly
        placeholder={user.userRole}
        InputProps={{
            readOnly: true,
          }}
        />
      
        <Button variant="contained" color="secondary" onClick={handleClick}>
          Submit
        </Button>

        </Paper>

      </Container>
    </Box>
  );
}