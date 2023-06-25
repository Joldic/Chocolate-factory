import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import './styles/Signup.css'
import { Container } from '@mui/system';
import {Paper, Button} from '@mui/material'

export default function RegularUserHomepage() {
  const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
  const[password, setPassword] = useState('')
  const[firstName, setFirstName] = useState('')
  const[lastName, setLastName] = useState('')
  const[email, setEmail] = useState('')
  const[username, setUsername] = useState('')

  const handleClick = (e) =>{
    window.location.href = '/UserProfileUpdate';

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
      <h1>Homepage</h1>
      <br></br>
      <Button variant="contained" color="secondary" onClick={handleClick}>
          Update profile
        </Button>
    </Box>
  );
}